/**
 * SECTION:
 * Imports
 */
// NOTE: Custom Hook Imports
import { useDocumentTitle } from "../hooks/useDocumentTitle";

// NOTE: HTML/Component Imports
import GoogleLogin from "../features/login/GoogleLogin";

function Login() {
    // DESC: Custom Hook to update <title>
    useDocumentTitle('iMedical | Login')

    return (
        <main>
            <h1>Login</h1>
            <GoogleLogin />
        </main>
    );
};

export default Login;