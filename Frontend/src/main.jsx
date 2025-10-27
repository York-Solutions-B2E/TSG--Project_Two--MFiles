/**
 * SECTION:
 * Pending Functionality
 */
// TODO: Complete form-functionality on Claims
// TODO: Build dynamic Claim Details URLs
// TODO: Pull in Data from API
// TODO: Pull users full name from HTTP Header (display on (x3) member pages)
// TODO: Format dates as MM/DD/YYYY
// TODO: ReadMe w/ Setup/Run instructions, OAuth config., directions for Google

/**
 * SECTION:
 * Out-of-Scope Functionality
 */
// TODO: OAuth (involves Spring)
// TODO: Protected routes that re-direct unauthorized users to OAuth login
// TODO: Sign-out clears tokens and returns to OAuth login

/**
 * SECTION:
 * Imports
 * - React is a UI library
 * - React DOM allows React to interact with the DOM
 * - React Router handles the routing
 */
// NOTE: React Imports
import { StrictMode } from "react";

// NOTE: React DOM Imports
import ReactDOM from "react-dom/client";

// NOTE: Project-based Imports
import "./styles/normalize.css"
import "./styles/global.css";
import App from './App';

/**
 * SECTION:
 * Main application build-out
 * - StrictMode is a Dev-Tool that helps Devs identify problems
 */
const root = document.getElementById("root");

ReactDOM.createRoot(root).render(
    <StrictMode>
            <App />
    </StrictMode>,
);