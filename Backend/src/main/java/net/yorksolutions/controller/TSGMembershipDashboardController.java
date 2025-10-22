package net.yorksolutions.controller;

import net.yorksolutions.dto.ClaimPOJO;
import net.yorksolutions.dto.DashboardPOJO;
import net.yorksolutions.entity.*;
import net.yorksolutions.enumeration.ProjectStatus;
import net.yorksolutions.service.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "${client.url}")
public class TSGMembershipDashboardController {
    /**
     * SECTION:
     * Declare necessary variables
     */
    private final ProjectStatus currentProjectStatus = ProjectStatus.DEVELOPMENT;
    private final TSGUserService tsGUserService;
    private final TSGMemberService tsGMemberService;
    private final TSGEnrollmentService tsGEnrollmentService;
    private final TSGPlanService tsGPlanService;
    private final TSGAccumulatorService tsGAccumulatorService;
    private final TSGClaimService tsgClaimService;

    /**
     * SECTION:
     * Required-Args Constructor
     * NOTE: All necessary 'Services' are included
     */
    public TSGMembershipDashboardController(
            TSGUserService tsGUserService, TSGMemberService tsGMemberService,
            TSGEnrollmentService tsGEnrollmentService, TSGPlanService tsGPlanService,
            TSGAccumulatorService tsGAccumulatorService, TSGClaimService tsgClaimService
    ) {
        this.tsGUserService = tsGUserService;
        this.tsGMemberService = tsGMemberService;
        this.tsGEnrollmentService = tsGEnrollmentService;
        this.tsGPlanService = tsGPlanService;
        this.tsGAccumulatorService = tsGAccumulatorService;
        this.tsgClaimService = tsgClaimService;
    }

    /**
     * PT02 - Handler for Displaying Member Dashboard
     * <p>
     * End-point: localhost:8080/api/dashboard
     * <p>
     * There is NO local login/logout end-point as that is handled between
     * React and Google OAuth.
     * <p>
     * This end-point MUST validate the JWT access token(s) (issuer, audience, JWKs)
     * @return Active Plan, Accumulator(s) and Recent Claims
     */
    @GetMapping("/api/dashboard")
    public ResponseEntity<DashboardPOJO> readMemberDashboard() {
        // DESC: Create the DTO / POJO for all Dashboard data
        DashboardPOJO returnedObject = new DashboardPOJO();

        // TODO: I expect OAuth to provide the 'email' through the ID Token
        // ... as such the logic would normally need to collect that, however
        // ... we only have one user so the logic will always validate to my
        // ... email address

        // DESC: Handler for Development vs Production
        if (currentProjectStatus.equals(ProjectStatus.DEVELOPMENT)) {
            String oAuthReturnedEmail = "m.kefiles@gmail.com";

            // DESC: Get User ID (by Email -- Rec'd from OAuth)
            TSGUser tsgUser = tsGUserService.findUserByEmail(oAuthReturnedEmail);

            if (tsgUser != null) {
                // DESC: Get Member ID and Member Name
                TSGMember member = tsGMemberService.findByUserId(tsgUser);
                UUID memberId = member.getId();
                String memberName = member.getFirstName() + " " + member.getLastName();

                // DESC: Get 'active' Enrollment (for Plan ID and Enrollment ID)
                // NOTE: This is necessary to extract the applicable Plan information
                TSGEnrollment enrollment = tsGEnrollmentService.findByMemberIdAndActive(member, true);
                UUID activeEnrollmentId = enrollment.getId();
                UUID activeEnrollmentPlanId = enrollment.getPlanId().getId();

                // DESC: Get Plan Details (type, network and year)
                TSGPlan plan = tsGPlanService.findById(activeEnrollmentPlanId);
                String planName = plan.getName();
                String planNetworkName = plan.getNetworkName();
                Integer planYear = plan.getPlanYear();

                // DESC: Get Accumulators (both Deductible and OOP Max)
                TSGAccumulator deductibleAccumulator = tsGAccumulatorService
                        .findByEnrollmentIdAndType(
                                enrollment,
                                TSGAccumulator.TSGAccumulatorType.valueOf("DEDUCTIBLE")
                        );
                TSGAccumulator oopMaxAccumulator = tsGAccumulatorService
                        .findByEnrollmentIdAndType(
                                enrollment,
                                TSGAccumulator.TSGAccumulatorType.valueOf("OOP_MAX")
                        );
                BigDecimal deductibleAccumulatorLimitAmount = deductibleAccumulator.getLimitAmount();
                BigDecimal deductibleAccumulatorUsedAmount = deductibleAccumulator.getUsedAmount();
                BigDecimal oopMaxAccumulatorLimitAmount = oopMaxAccumulator.getLimitAmount();
                BigDecimal oopMaxAccumulatorUsedAmount = oopMaxAccumulator.getUsedAmount();

                // DESC: Get Claims (need details for (x5))
                List<TSGClaim> allClaimsByMember = tsgClaimService.findByMemberIdOrderByServiceStartDateAsc(member);
                List<ClaimPOJO> topFiveClaims = new ArrayList<ClaimPOJO>();
                if (allClaimsByMember.size() > 5) {
                    for (int i = 0; i < 5; i++) {
                        topFiveClaims.add(
                                new ClaimPOJO(
                                        allClaimsByMember.get(i).getClaimNumber(),
                                        allClaimsByMember.get(i).getStatus().name(),
                                        allClaimsByMember.get(i).getTotalMemberResponsibility()
                                )
                        );
                    }
                } else {
                    for (TSGClaim claim : allClaimsByMember) {
                        topFiveClaims.add(
                                new ClaimPOJO(
                                        claim.getClaimNumber(),
                                        claim.getStatus().name(),
                                        claim.getTotalMemberResponsibility()
                                )
                        );
                    }
                }

                // DESC: Populate the DashboardPOJO DTO
                returnedObject.setMemberName(memberName);
                returnedObject.setPlanName(planName);
                returnedObject.setPlanNetwork(planNetworkName);
                returnedObject.setPlanYear(planYear);
                returnedObject.setAccumulatorDeductibleType("DEDUCTIBLE");
                returnedObject.setAccumulatorDeductibleLimitAmount(deductibleAccumulatorLimitAmount);
                returnedObject.setAccumulatorDeductibleUsedAmount(deductibleAccumulatorUsedAmount);
                returnedObject.setAccumulatorOopMaxType("OOP_MAX");
                returnedObject.setAccumulatorOopMaxLimitAmount(oopMaxAccumulatorLimitAmount);
                returnedObject.setAccumulatorOopMaxUsedAmount(oopMaxAccumulatorUsedAmount);
                returnedObject.setClaims(topFiveClaims);

                // DESC: Add the Users Name to the HTTP Header
                // NOTE: By doing this, displaying the users name is not dependent
                // ... upon the JSON payload
                HttpHeaders responseHeaders = new HttpHeaders();
                responseHeaders.add("Content-Type", "application/json");
                responseHeaders.add("Users-Name", memberName);

                return ResponseEntity
                        .status(200)
                        .headers(responseHeaders)
                        .body(returnedObject);
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
