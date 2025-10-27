import { useDocumentTitle } from "../hooks/useDocumentTitle";
import ListOfClaims from "../features/claims/ListOfClaims";

function Claims() {
    // DESC: Custom Hook to update <title>
    useDocumentTitle('iMedical | Claims')

    return (
        <main>
            <h1>Claims</h1>
            <ListOfClaims />
        </main>
    );
};

export default Claims;