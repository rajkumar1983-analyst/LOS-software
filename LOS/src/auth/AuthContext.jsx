import { createContext, useEffect, useRef, useState } from "react";
import keycloak from "./keyCloak";

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [authenticated, setAuthenticated] = useState(false);
  const [roles, setRoles] = useState([]);
  const initialized = useRef(false);

  useEffect(() => {
    if (initialized.current) return;
    initialized.current = true;

    keycloak.init({
      onLoad: "login-required",
      checkLoginIframe: false,
    }).then((auth) => {
      setAuthenticated(auth);

      if (auth) {
        const clientRoles =
          keycloak.tokenParsed?.resource_access?.["los-frontend"]?.roles || [];
        setRoles(clientRoles);

        const refresh = setInterval(() => {
          keycloak.updateToken(60).then((refreshed) => {
            if (refreshed) {
              const next =
                keycloak.tokenParsed?.resource_access?.["los-frontend"]?.roles || [];
              setRoles(next);
            }
          }).catch(() => keycloak.login());
        }, 30000);

        return () => clearInterval(refresh);
      }
    });
  }, []);

  const logout = () => keycloak.logout();

  const hasPermission = (p) => roles.includes(p);
  const hasAny = (perms) => perms.some((p) => roles.includes(p));
  const hasAll = (perms) => perms.every((p) => roles.includes(p));

  return (
    <AuthContext.Provider
      value={{ keycloak, authenticated, roles, logout, hasPermission, hasAny, hasAll }}
    >
      {children}
    </AuthContext.Provider>
  );
};
