import { Link } from "react-router";
import Navigation from "../../components/Navigation";
import dashboardData from "./ex_dashboard.json";

function MemberDashboard() {

    const tempData = dashboardData;
    const statusConverter = (status) => {
        switch (status) {
            case "SUBMITTED":
                return "Submitted"
            case "DENIED":
                return "Denied";
            case "IN_REVIEW":
                return "In Review";
            case "PAID":
                return "Paid";
            case "PROCESSED":
                return "Processed";
            default:
                return "TBD"
        }
    }

    return (
        <member-dashboard>
            <Navigation />
            <policy-information>
                <policy-active-plan>
                    <p>Active Plan</p>
                    <ul>
                        <li>{ tempData.planName }</li>
                        <li>Network: { tempData.planNetwork }</li>
                        <li>Coverage: { tempData.planYear }</li>
                    </ul>
                </policy-active-plan>
                <policy-accumulators>
                    <p>Accumulators</p>
                    <ul>
                        <li>
                            Deductible: ${ tempData.accumulatorDeductibleUsedAmount}  / ${ tempData.accumulatorDeductibleLimitAmount }
                        </li>
                        <li>
                            OOP Max: ${ tempData.accumulatorOopMaxUsedAmount } / ${ tempData.accumulatorOopMaxLimitAmount }
                        </li>
                    </ul>
                </policy-accumulators>
                <policy-recent-claims>
                    <p>Recent Claims</p>
                    <ul>
                        { tempData.claims.map( (claim) => (
                            <li key={ claim.claimNumber }>
                                { claim.claimNumber } -
                                { statusConverter(claim.claimStatus) } -
                                ${ claim.claimTotalMemberResponsibility.toLocaleString('en-US') }
                            </li>
                        )) }
                    </ul>
                </policy-recent-claims>
            </policy-information>
            <lower-navigation>
                <span>
                    <Link to="/claims" className="simple-link">
                        View All Claims
                    </Link>
                </span>
            </lower-navigation>
        </member-dashboard>
    );
};

export default MemberDashboard;
