import { Routes, Route } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import CreateApplication from "./pages/CreateApplication";
import ViewApplication from "./pages/ViewApplication";
import ProtectedRoute from "./components/ProtectedRoute";
import BankerDashboard from "./pages/BankerDashboard";

function App() {

  return (
    <>
      <Routes>
        <Route
          path="/"
          element={
            <ProtectedRoute requires="APPLIED_LIST_VIEW">
              <Dashboard />
            </ProtectedRoute>
          }
        />
        <Route
          path="/create"
          element={
            <ProtectedRoute requires="CREATE_APPLICATION">
              <CreateApplication />
            </ProtectedRoute>
          }
        />
        <Route
          path="/applications/:id"
          element={
            <ProtectedRoute requires={["APPLIED_LIST_VIEW", "APPLICATION_ALL_LIST_VIEW"]}>
              <ViewApplication />
            </ProtectedRoute>
          }
        />
        <Route
          path="/banker"
          element={
            <ProtectedRoute requires="APPLICATION_ALL_LIST_VIEW">
              <BankerDashboard />
            </ProtectedRoute>
          }
        />
        <Route
          path="/banker/review/:id"
          element={
            <ProtectedRoute requires="APPLICATION_ALL_LIST_VIEW">
              <ViewApplication />
            </ProtectedRoute>
          }
        />
      </Routes>
    </>
  );
}

export default App;
