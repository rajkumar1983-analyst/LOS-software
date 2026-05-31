import { API_CONFIG } from "../config/apiConfig";
import { authFetch } from "./http";

export const createCustomer = async (payload) => {
  const response = await authFetch(`${API_CONFIG.CUSTOMER}/api/customers`, {
    method: "POST",
    body: JSON.stringify(payload)
  });

  if (!response.ok) {
    throw new Error("Customer creation failed");
  }
  return response.json();
};

export const getCustomerById = async (id) => {
  const response = await authFetch(`${API_CONFIG.CUSTOMER}/api/customers/${id}`);
  return response.json();
};
