import axios from "axios";

const API = "http://localhost:8080/api/auth";

export const register = (data) => {
  return axios.post(`${API}/register`, data);
};

export const login = (data) => {
  return axios.post(`${API}/login`, data);
};

export const getProfile = () => {
  const token = localStorage.getItem("token");

  return axios.get("http://localhost:8080/api/user/me", {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};