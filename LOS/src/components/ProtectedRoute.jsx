import { useContext } from "react";
import { Navigate } from "react-router-dom";
import { Box, CircularProgress } from "@mui/material";
import { AuthContext } from "../auth/AuthContext";

const ProtectedRoute = ({ children, requires }) => {
  const { authenticated, roles } = useContext(AuthContext);

  if (!authenticated) {
    return (
      <Box
        sx={{
          minHeight: "100vh",
          display: "flex",
          alignItems: "center",
          justifyContent: "center"
        }}
      >
        <CircularProgress />
      </Box>
    );
  }

  const needed = requires ? (Array.isArray(requires) ? requires : [requires]) : [];

  if (needed.length && !needed.some((p) => roles.includes(p))) {
    if (roles.includes("APPLICATION_ALL_LIST_VIEW")) return <Navigate to="/banker" replace />;
    if (roles.includes("APPLIED_LIST_VIEW")) return <Navigate to="/" replace />;
    return (
      <Box sx={{ p: 4 }}>
        You do not have access to this page.
      </Box>
    );
  }

  return children;
};

export default ProtectedRoute;
