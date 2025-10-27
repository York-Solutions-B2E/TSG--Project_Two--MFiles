import { useNavigate } from "react-router-dom";

function GoogleLogin() {
    // DESC: Get the useNavigate function
    const navigate = useNavigate();
    
    // DESC: Redirect user to dashboard
    const redirectUser = () => {
        navigate("/dashboard");
    };

    return (
        <google-login>
            <button onClick={ redirectUser } >Continue with Google</button>
        </google-login>
    );
};

export default GoogleLogin;