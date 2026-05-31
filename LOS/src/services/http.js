import keycloak from "../auth/keyCloak";

export const authFetch = async (url, options = {}) => {
  try {
    await keycloak.updateToken(30);
  } catch {
    keycloak.login();
    throw new Error("Session expired");
  }

  const headers = {
    ...(options.headers || {}),
    Authorization: `Bearer ${keycloak.token}`,
  };
  if (options.body && !headers["Content-Type"]) {
    headers["Content-Type"] = "application/json";
  }

  return fetch(url, { ...options, headers });
};
