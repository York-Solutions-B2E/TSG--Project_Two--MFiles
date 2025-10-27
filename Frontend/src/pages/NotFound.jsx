import { useDocumentTitle } from "../hooks/useDocumentTitle";

function NotFound() {
    // DESC: Custom Hook to update <title>
    useDocumentTitle('iMedical | Not Found')

    // DESC: Get path of current URL
    const currentPath = window.location.pathname;

    return (
        <main>
            <error-page>
                <div className="logo">
                    <img src="../../public/logo--iMedical_ERROR.png" alt="iMedical Colored Logo" />
                </div>
                <div className="error-code">
                    <b>404.</b> That's an error.
                </div>
                <div className="output-message">
                    <p>The requested URL {currentPath} was not found on this server.</p>
                    <p>That's all we know.</p>
                </div>
                <div className="google-robot">
                    <img src="../../public/img--Error_Robot.png" alt="404 Error Robot" />
                </div>
            </error-page>
        </main>
    );
};

export default NotFound;