import { useEffect, useState } from "react";
import { getProfile } from "../services/authService";
import { useNavigate } from "react-router-dom";

function Dashboard() {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    getProfile()
      .then((res) => setUser(res.data))
      .catch(() => navigate("/"));
  }, []);

  const logout = () => {
    localStorage.removeItem("token");
    navigate("/");
  };

  return (
    <div>
      <h2>Dashboard</h2>
      {user && <p>Welcome, {user.username}</p>}
      <button onClick={logout}>Logout</button>
    </div>
  );
}

export default Dashboard;
