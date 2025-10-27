/**
 * SECTION:
 * Imports
 */

// NOTE: React Router Imports
import { Routes, Route } from 'react-router-dom';

// NOTE: Project-based Imports
import Login from '../pages/Login';
import Dashboard from '../pages/Dashboard';
import Claims from '../pages/Claims';
import ClaimDetails from '../pages/ClaimDetails';
import NotFound from '../pages/NotFound';

/**
 * SECTION:
 * Application routes
 */
const AppRoutes = () => {
    return (
        <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/claims" element={<Claims />} />
            <Route path="/claim_details" element={<ClaimDetails />} />

            {/* Handler for all other pages */}
            <Route path="*" element={<NotFound />} />
        </Routes>
    );
};

export default AppRoutes;