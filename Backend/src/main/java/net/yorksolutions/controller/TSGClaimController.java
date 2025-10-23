package net.yorksolutions.controller;

import net.yorksolutions.dto.ClaimAndClaimLinePOJO;
import net.yorksolutions.dto.ClaimLinePOJO;
import net.yorksolutions.dto.ClaimListPOJO;
import net.yorksolutions.dto.PaginatedClaimListPOJO;
import net.yorksolutions.entity.*;
import net.yorksolutions.enumeration.ProjectStatus;
import net.yorksolutions.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "${client.url}")
public class TSGClaimController {
    /**
     * SECTION:
     * Declare necessary variables
     */
    private final ProjectStatus currentProjectStatus = ProjectStatus.DEVELOPMENT;
    private final TSGUserService tsGUserService;
    private final TSGMemberService tsGMemberService;
    private final TSGClaimService tsGClaimService;
    private final TSGProviderService tSGProviderService;
    private final TSGClaimLineService tsGClaimLineService;

    /**
     * SECTION:
     * Required-Args Constructor
     * NOTE: All necessary 'Services' are included
     */
    public TSGClaimController(
            TSGUserService tsGUserService, TSGMemberService tsGMemberService,
            TSGClaimService tsGClaimService, TSGProviderService tSGProviderService,
            TSGClaimLineService tsGClaimLineService
    ) {
        this.tsGUserService = tsGUserService;
        this.tsGMemberService = tsGMemberService;
        this.tsGClaimService = tsGClaimService;
        this.tSGProviderService = tSGProviderService;
        this.tsGClaimLineService = tsGClaimLineService;
    }

    /**
     * PT03 - Handler for Displaying Claims
     * <p>
     * End-point: localhost:8080/api/claims
     * <p>
     * There is NO local login/logout end-point as that is handled between
     * React and Google OAuth.
     * <p>
     * Default: This will default to being filtered by a Status of 'Processed' with
     * a sorting by 'Received Date'
     * <p>
     * Note: This will return empty (when no results exist)
     * <p>
     * This end-point MUST validate the JWT access token(s) (issuer, audience, JWKs)
     * @return Claims (paginated; as filtered by start-date, end-date, etc.)
     */
    @PostMapping("/api/claims")
    public ResponseEntity<Page<ClaimListPOJO>> readClaims(
            @RequestBody PaginatedClaimListPOJO paginatedClaimListPOJO
    ) {
        // TODO: I expect OAuth to provide the 'email' through the ID Token
        // ... as such the logic would normally need to collect that, however
        // ... we only have one user so the logic will always validate to my
        // ... email address
        if (currentProjectStatus.equals(ProjectStatus.DEVELOPMENT)) {
            String oAuthReturnedEmail = "m.kefiles@gmail.com";

            // DESC: Get User ID (by Email -- Rec'd from OAuth)
            TSGUser tsgUser = tsGUserService.findUserByEmail(oAuthReturnedEmail);

            if (tsgUser != null) {
                // DESC: Get Member ID and Member Name
                TSGMember member = tsGMemberService.findByUserId(tsgUser);
                UUID memberId = member.getId();
                String memberName = member.getFirstName() + " " + member.getLastName();

                // DESC: Parse RequestBody data to variables
                String claimNumber = (paginatedClaimListPOJO.getClaimNumber().isEmpty())
                        ? null : paginatedClaimListPOJO.getClaimNumber();

                LocalDate serviceStartDate = (paginatedClaimListPOJO.getServiceStartDate() == null)
                        ? null : paginatedClaimListPOJO.getServiceStartDate();

                LocalDate serviceEndDate = (paginatedClaimListPOJO.getServiceEndDate() == null)
                        ? null : paginatedClaimListPOJO.getServiceEndDate();

                String name = (paginatedClaimListPOJO.getName().isEmpty())
                        ? null : "%" + paginatedClaimListPOJO.getName() + "%";

                List<String> statusList = (paginatedClaimListPOJO.getStatusList().isEmpty())
                        ? Arrays.asList(new String[]{"PROCESSED"}) : paginatedClaimListPOJO.getStatusList();

                // DESC: Create Pageable instance and variables to handle chunking data
                // NOTE: The Integers are defaulted to the first page (pageNumber of 0)
                // ... and a ten-result page (pageSize of 10) as per the Project Outline
                Integer pageNumber = (paginatedClaimListPOJO.getPageNumber() == null)
                        ? 0 : paginatedClaimListPOJO.getPageNumber();
                Integer pageSize = (paginatedClaimListPOJO.getPageSize() == null)
                        ? 10 : paginatedClaimListPOJO.getPageSize();
                Pageable pageable = PageRequest.of(pageNumber, pageSize);

                // DESC: Return all Claims that match the filtered criteria
                Page<ClaimListPOJO> claimListOutputPaginated = tsGClaimService.findClaimsPaginated(
                        memberId, statusList, serviceStartDate,
                        serviceEndDate, name, claimNumber, pageable
                );

                // DESC: Add the Users Name to the HTTP Header
                // NOTE: By doing this, displaying the users name is not dependent
                // ... upon the JSON payload
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.add("Content-Type", "application/json");
                responseHeaders.add("Users-Name", memberName);

                // NOTE: JSON output will contain a NULL statusList value that,
                // ... in my opinion, does not do any harm so I am leaving it as-is
                // ... in lieu of transferring data from one Object to another
                return ResponseEntity
                        .status(200)
                        .headers(responseHeaders)
                        .body(claimListOutputPaginated);
            } else {
                // DESC: Return "Unauthorized" with an empty body
                return ResponseEntity
                        .status(401)
                        .build();
            }
        } else {
            // TODO: Implement functionality for other Users/Members
            // FIXME: Update the returned value
            // DESC: Return "Unauthorized" with an empty body
            return ResponseEntity
                    .status(401)
                    .build();
        }
    }

    /**
     * PT04 - Handler for Displaying Claim Details
     * <p>
     * End-point: localhost:8080/api/claims/{claimNumber}
     * <p>
     * There is NO local login/logout end-point as that is handled between
     * React and Google OAuth.
     * <p>
     * This end-point MUST validate the JWT access token(s) (issuer, audience, JWKs)
     * @param claimNumber the ID provided by the URL (ex. C-10300)
     * @return Claim Details (incl. Lines and Status History)
     */
    @GetMapping("/api/claims/{claimNumber}")
    public ResponseEntity<ClaimAndClaimLinePOJO> readClaimDetails(
            @PathVariable("claimNumber") String claimNumber
    ) {
        // TODO: I expect OAuth to provide the 'email' through the ID Token
        // ... as such the logic would normally need to collect that, however
        // ... we only have one user so the logic will always validate to my
        // ... email address
        if (currentProjectStatus.equals(ProjectStatus.DEVELOPMENT)) {
            String oAuthReturnedEmail = "m.kefiles@gmail.com";

            // DESC: Get User ID (by Email -- Rec'd from OAuth)
            TSGUser tsgUser = tsGUserService.findUserByEmail(oAuthReturnedEmail);

            if (tsgUser != null) {
                // DESC: Get Member ID and Member Name
                TSGMember member = tsGMemberService.findByUserId(tsgUser);
                UUID memberId = member.getId();
                String memberName = member.getFirstName() + " " + member.getLastName();

                // DESC: Convert URL Claim Number to actual Claim Number
                // NOTE: Claim numbers are stored in the database like
                // ... "#C-10300", however a Hash-Tag is a reserved
                // ... character for URLs so it is being pre-pended here
                claimNumber = "#" + claimNumber;

                // DESC: Instantiate empty ClaimAndClaimLinePOJO
                ClaimAndClaimLinePOJO claimAndClaimLinePOJO = new ClaimAndClaimLinePOJO();

                // DESC: Query Claim-table based on Claim Number and Member ID
                TSGClaim tsgClaim = tsGClaimService.findByClaimNumberAndMemberId(claimNumber, member);

                // DESC: Update ClaimAndClaimLinePOJO for Claim-table data
                claimAndClaimLinePOJO.setClaimNumber(tsgClaim.getClaimNumber());
                claimAndClaimLinePOJO.setServiceStartDate(tsgClaim.getServiceStartDate());
                claimAndClaimLinePOJO.setServiceEndDate(tsgClaim.getServiceEndDate());
                claimAndClaimLinePOJO.setStatus(tsgClaim.getStatus().toString());
                claimAndClaimLinePOJO.setReceivedDate(tsgClaim.getReceivedDate());
                claimAndClaimLinePOJO.setUpdatedAt(tsgClaim.getUpdatedAt());
                claimAndClaimLinePOJO.setTotalBilled(tsgClaim.getTotalBilled());
                claimAndClaimLinePOJO.setTotalAllowed(tsgClaim.getTotalAllowed());
                claimAndClaimLinePOJO.setTotalPlanPaid(tsgClaim.getTotalPlanPaid());
                claimAndClaimLinePOJO.setTotalMemberResponsibility(tsgClaim.getTotalMemberResponsibility());

                // DESC: Using Provider ID, assoc. w/ Claim, get Provider Name
                TSGProvider tsgProvider = tSGProviderService.findById(tsgClaim.getProviderId().getId());

                // DESC: Update ClaimAndClaimLinePOJO for Provider-table data
                claimAndClaimLinePOJO.setProviderName(tsgProvider.getName());

                // DESC: Query ClaimLine-table based on ID from Claim
                List<TSGClaimLine> listOfClaimLines = tsGClaimLineService.findByClaimId(tsgClaim);

                // DESC: Loop over ClaimLine query results and add to POJO
                List<ClaimLinePOJO> tempClaimLinePOJOList = new ArrayList<ClaimLinePOJO>();
                for (TSGClaimLine claimLine : listOfClaimLines) {
                    // DESC: Extract values from current TSGClaimLine
                    String cC = claimLine.getCptCode();
                    String d = claimLine.getDescription();
                    BigDecimal bA = claimLine.getBilledAmount();
                    BigDecimal aA =  claimLine.getAllowedAmount();
                    BigDecimal dA = claimLine.getDeductibleAmount();
                    BigDecimal copA = claimLine.getCopayApplied();
                    BigDecimal coiA = claimLine.getCoinsuranceApplied();
                    BigDecimal mR = claimLine.getMemberResponsibility();

                    // DESC: Add all values, as one ClaimLinePOJO, to the List
                    tempClaimLinePOJOList.add(
                            new  ClaimLinePOJO(cC, d, bA, aA, dA, copA, coiA, mR)
                    );
                }

                // DESC: Every ClaimLinePOJO is added to ClaimAndClaimLinePOJO
                claimAndClaimLinePOJO.setClaimLines(tempClaimLinePOJOList);

                // DESC: Add the Users Name to the HTTP Header
                // NOTE: By doing this, displaying the users name is not dependent
                // ... upon the JSON payload
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.add("Content-Type", "application/json");
                responseHeaders.add("Users-Name", memberName);

                // NOTE: JSON output will contain a NULL statusList value that,
                // ... in my opinion, does not do any harm so I am leaving it as-is
                // ... in lieu of transferring data from one Object to another
                return ResponseEntity
                        .status(200)
                        .headers(responseHeaders)
                        .body(claimAndClaimLinePOJO);
            } else {
                // DESC: Return "Unauthorized" with an empty body
                return ResponseEntity
                        .status(401)
                        .build();
            }
        } else {
            // TODO: Implement functionality for other Users/Members
            // FIXME: Update the returned value
            // DESC: Return "Unauthorized" with an empty body
            return ResponseEntity
                    .status(401)
                    .build();
        }
    }
}