import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
  url: "http://localhost:9090",
  realm: "los-realm",
  clientId: "los-frontend"
});

export default keycloak;