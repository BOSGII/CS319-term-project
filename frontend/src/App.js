import "./App.css";

import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import { useContext } from "react";
import { UserContext } from "./contexts/UserContext";

import LoginPage from "./pages/login_page/LoginPage";
import HomePage from "./pages/home/HomePage";
import InternshipsPage from "./pages/internships/InternshipsPage";
import InstructorsPage from "./pages/instructors/InstructorsPage";
import SubmissionPage from "./pages/submission/SubmissionPage";

import { InternshipIDProvider } from "./contexts/InternshipIDContext";

function App() {
  const { user } = useContext(UserContext);

  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Navigate to="/login" />} />
          <Route path="/login" element={<LoginPage />} />
          <Route
            path="/home"
            element={
              user.role ? <HomePage /> : <Navigate to="/login" replace={true} />
            }
          />
          <Route
            path="/internships"
            element={
              user.role ? (
                <InternshipIDProvider>
                  <InternshipsPage />
                </InternshipIDProvider>
              ) : (
                <Navigate to="/login" replace={true} />
              )
            }
          />
          <Route
            path="/internships/:internshipId"
            element={
              user.role ? (
                <InternshipIDProvider>
                  <SubmissionPage />
                </InternshipIDProvider>
              ) : (
                <Navigate to="/login" replace={true} />
              )
            }
          />
          <Route
            path="/my_internships"
            element={
              user.role === "student" ? (
                <InternshipIDProvider>
                  <InternshipsPage />
                </InternshipIDProvider>
              ) : (
                <Navigate to="/login" replace={true} />
              )
            }
          />
          <Route
            path="/my_internships/:internshipType"
            element={
              user.role === "student" ? (
                <InternshipIDProvider>
                  <SubmissionPage />
                </InternshipIDProvider>
              ) : (
                <Navigate to="/login" replace={true} />
              )
            }
          />
          <Route
            path="/instructors"
            element={
              user.role === "secretary" ? (
                <InstructorsPage />
              ) : (
                <Navigate to="/login" replace={true} />
              )
            }
          />
          <Route
            path="/instructors/:instructorId"
            element={
              user.role === "secretary" ? (
                <InternshipIDProvider>
                  <InternshipsPage />
                </InternshipIDProvider>
              ) : (
                <Navigate to="/login" replace={true} />
              )
            }
          />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
