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

// Public self-service registration: no auth token yet, so a plain fetch (not authFetch).
export const registerCustomer = async (payload) => {
  const response = await fetch(`${API_CONFIG.CUSTOMER}/api/customers/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload)
  });

  if (!response.ok) {
    const text = await response.text().catch(() => "");
    if (response.status === 409) {
      throw new Error("That username or email is already registered.");
    }
    throw new Error(text || "Registration failed");
  }

  return response.json();
};

export const getCustomerById = async (id) => {
  const response = await authFetch(`${API_CONFIG.CUSTOMER}/api/customers/${id}`);
  if (!response.ok) throw new Error("Failed to load customer");
  return response.json();
};

// The logged-in user's own profile (resolved server-side from the JWT subject).
export const getMyProfile = async () => {
  const response = await authFetch(`${API_CONFIG.CUSTOMER}/api/customers/me`);
  if (!response.ok) throw new Error("Failed to load your profile");
  return response.json();
};
