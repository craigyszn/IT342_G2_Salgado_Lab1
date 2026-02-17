import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Dashboard.css";

function Dashboard() {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const stored = localStorage.getItem("photolab_user");
    if (stored) {
      setUser(JSON.parse(stored));
    }
  }, []);

  const logout = () => {
    localStorage.removeItem("photolab_token");
    localStorage.removeItem("photolab_user");
    navigate("/", { replace: true });
  };

  const displayName = user
    ? `${user.userFirstName || ""} ${user.userLastName || ""}`.trim()
    : "Guest";
  const displayEmail = user?.userEmail || "";
  const initials = displayName
    .split(" ")
    .map((n) => n[0])
    .join("")
    .slice(0, 2)
    .toUpperCase();

  return (
    <div className="dashboard-page">
      {/* Navbar */}
      <nav className="dashboard-navbar">
        <div className="navbar-logo">
          Wel<span>come</span>
        </div>
        <div className="navbar-right">
          <span className="navbar-username">{displayName}</span>
          <button onClick={logout} className="navbar-logout">
            Logout
          </button>
        </div>
      </nav>

      {/* Content */}
      <main className="dashboard-content">
        <div className="profile-card">
          {/* Banner */}
          <div className="profile-banner">
            <div className="profile-avatar-wrap">
              <div className="profile-avatar">{initials}</div>
            </div>
          </div>

          {/* Body */}
          <div className="profile-body">
            <h2 className="profile-name">{displayName}</h2>
            <p className="profile-email">{displayEmail}</p>

            <p className="profile-section-title">Account Info</p>
            <div className="profile-info-grid">
              <div className="profile-info-item">
                <div className="profile-info-label">First Name</div>
                <div className="profile-info-value">
                  {user?.userFirstName || "—"}
                </div>
              </div>
              <div className="profile-info-item">
                <div className="profile-info-label">Last Name</div>
                <div className="profile-info-value">
                  {user?.userLastName || "—"}
                </div>
              </div>
              <div className="profile-info-item">
                <div className="profile-info-label">Email</div>
                <div className="profile-info-value">{displayEmail || "—"}</div>
              </div>
              <div className="profile-info-item">
                <div className="profile-info-label">Account Status</div>
                <div className="profile-info-value">✅ Active</div>
              </div>
            </div>

            <hr className="profile-divider" />

            <button onClick={logout} className="profile-logout-btn">
              Sign Out
            </button>
          </div>
        </div>
      </main>
    </div>
  );
}

export default Dashboard;