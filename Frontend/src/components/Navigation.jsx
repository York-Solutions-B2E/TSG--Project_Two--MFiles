import { Link } from "react-router";

function Navigation() {
    return (
        <simple-nav>
            <span>CUSTOMER NAME</span>
            <span><Link to="/" className = "simple-link">Sign Out</Link></span>
        </simple-nav>
    );
};

export default Navigation;