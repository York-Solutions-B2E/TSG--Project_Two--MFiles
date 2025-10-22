package net.yorksolutions.controller;

import net.yorksolutions.dto.ClaimListPOJO;
import net.yorksolutions.dto.PaginatedClaimListPOJO;
import net.yorksolutions.entity.TSGMember;
import net.yorksolutions.entity.TSGUser;
import net.yorksolutions.enumeration.ProjectStatus;
import net.yorksolutions.service.TSGClaimService;
import net.yorksolutions.service.TSGMemberService;
import net.yorksolutions.service.TSGUserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    /**
     * SECTION:
     * Required-Args Constructor
     * NOTE: All necessary 'Services' are included
     */
    public TSGClaimController(
            TSGUserService tsGUserService, TSGMemberService tsGMemberService,
            TSGClaimService tsGClaimService
    ) {
        this.tsGUserService = tsGUserService;
        this.tsGMemberService = tsGMemberService;
        this.tsGClaimService = tsGClaimService;
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
    public ResponseEntity<List<ClaimListPOJO>> readClaims(@RequestBody PaginatedClaimListPOJO paginatedClaimListPOJO) {
        // TODO: Will need to obtain/pass the Member ID to this... along
        // TODO: Handle Pagination (defaults of size: 10; max: 25)
        // TODO: To 'filter' by page and size (?)

        System.out.println("PAGE SIZE:" + paginatedClaimListPOJO.getPageSize());
        System.out.println("PAGE NUMBER:" + paginatedClaimListPOJO.getPageNumber());

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


                List<ClaimListPOJO> claimListOutput = tsGClaimService.findClaims(
                        statusList, serviceStartDate, serviceEndDate, name, claimNumber
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
                        .body(claimListOutput);
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
     * @param claimNumber the ID provided by the URL
     * @return Claim Details (incl. Lines and Status History)
     */
    @GetMapping("/api/claims/{claimNumber}")
    public String readClaimDetails(@PathVariable("claimNumber") String claimNumber) {
        // TODO - COMPLETE ME
        return "Claim number " + claimNumber;
    }
}