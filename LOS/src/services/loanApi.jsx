import { API_CONFIG } from "../config/apiConfig";
import { authFetch } from "./http";

export const getAllLoans = async () => {
  const response = await authFetch(`${API_CONFIG.LOAN}/api/loans`);
  if (!response.ok) throw new Error("Failed to load loans");
  return response.json();
};

export const getMyLoans = async () => {
  const response = await authFetch(`${API_CONFIG.LOAN}/api/loans/mine`);
  if (!response.ok) throw new Error("Failed to load your loans");
  return response.json();
};

export const getLoanById = async (id) => {
  const response = await authFetch(`${API_CONFIG.LOAN}/api/loans/${id}`);
  if (!response.ok) throw new Error("Failed to load loan");
  return response.json();
};

export const createLoan = async (payload) => {
  const response = await authFetch(`${API_CONFIG.LOAN}/api/loans`, {
    method: "POST",
    body: JSON.stringify(payload)
  });

  if (!response.ok) {
    throw new Error("Loan creation failed");
  }

  return response.json();
};
