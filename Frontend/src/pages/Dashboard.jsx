import { useDocumentTitle } from "../hooks/useDocumentTitle";
import MemberDashboard from "../features/dashboard/MemberDashboard";

function Dashboard() {
    // DESC: Custom Hook to update <title>
    useDocumentTitle('iMedical | Dashboard')

    return (
        <main>
            <h1>Member Dashboard</h1>
            <MemberDashboard />
        </main>
    );
};

export default Dashboard;