import { useDocumentTitle } from "../hooks/useDocumentTitle";

function ClaimDetails() {
    // DESC: Custom Hook to update <title>
    useDocumentTitle('iMedical | Claim Details')

    return (
        <main>
            <h1>Claim Details</h1>
        </main>
    );
};

export default ClaimDetails;