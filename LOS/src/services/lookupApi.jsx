import { API_CONFIG } from "../config/apiConfig";
import { authFetch } from "./http";

export const fetchLookup = async (type) => {
  const response = await authFetch(`${API_CONFIG.CUSTOMER}/api/lookups/${type}`);

  if (!response.ok) {
    const text = await response.text();
    console.error("Lookup API error:", text);
    throw new Error(`Failed lookup: ${type}`);
  }

  return response.json();
};
