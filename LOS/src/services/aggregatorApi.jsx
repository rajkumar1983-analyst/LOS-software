import { API_CONFIG } from "../config/apiConfig";
import { authFetch } from "./http";

export const getLoanSummary = async () => {
  const url = `${API_CONFIG.AGGREGATOR}/dashboard/loans`;
  const response = await authFetch(url);

  if (!response.ok) {
    const body = await response.text().catch(() => "");
    throw new Error(`GET ${url} failed: ${response.status} ${response.statusText} ${body}`);
  }

  return response.json();
};
