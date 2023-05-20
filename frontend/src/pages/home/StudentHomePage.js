import { Navigate } from "react-router-dom";

export default function StudentHomePage() {
  return <Navigate to="/my_internships" replace={true} />;
}
