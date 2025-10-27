/**
 * SECTION:
 * Imports
 */

// NOTE: React Router Imports
import { BrowserRouter } from "react-router-dom";

// NOTE: Project-based Imports
import TopBar from './components/TopBar';
import AppRoutes from "./router/routes";
import Footer from './components/Footer';

/**
 * SECTION:
 * Main application build-out
 */
function App() {
    return (
        <BrowserRouter>
            {/* NOTE: Site-wide TopBar-component */}
            <TopBar />

            {/* NOTE: The React Router routing-component */}
            <AppRoutes />

            {/* NOTE: Site-wide Footer-component */}
            <Footer />
        </BrowserRouter>
    );
};

export default App;
