import './App.css';

import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom"
import { useContext } from 'react';
import { UserContext } from './contexts/UserContext';

import LoginPage from './pages/common_pages/login_page/LoginPage';
import HomePage from './pages/common_pages/home/HomePage';
import SecretaryInternshipsPage from './pages/secretary_pages/internships/SecretaryInternshipsPage';
import SecretaryOneInternshipPage from './pages/secretary_pages/one_internship/SecretaryOneInternshipPage';
import SecretaryInstructorsPage from './pages/secretary_pages/instructors/SecretaryInstructorsPage';
import SecretaryOneInstructorPage from './pages/secretary_pages/one_instructor/SecretaryOneInstructorPage';
import InstructorAssignedInternshipsPage from './pages/instructor_pages/assigned_internships/InstructorAssignedInternshipsPage';
import InstructorOneInternshipPage from './pages/instructor_pages/one_internship/InstructorOneInternshipPage';
import StudentMyInternshipsPage from './pages/student_pages/my_internships/StudentMyInternshipsPage';
import StudentOneInternshipPage from './pages/student_pages/one_internship/StudentOneInternshipPage';

function App() {
  const {user} = useContext(UserContext);

  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          {/* TODO: redirect to respective home page if authenticated */}
          <Route path="/" element={<Navigate to="/login"/>}/>
          <Route path="/login" element={<LoginPage/>}/>
          <Route path="/home" element={user.role ? <HomePage/> : <Navigate to="/login" replace={true}/>}/>

          {/* Secretary Pages */}
          <Route path="/internships" element={user.role === "secretary" ? <SecretaryInternshipsPage/> : <Navigate to="/login" replace={true}/>}/>
          <Route path="/internships/:internshipId" element={user.role === "secretary" ? <SecretaryOneInternshipPage/> : <Navigate to="/login" replace={true}/>}/>
          <Route path="/instructors" element={user.role === "secretary" ? <SecretaryInstructorsPage/> : <Navigate to="/login" replace={true}/>}/>
          <Route path="/instructors/:instructorId" element={user.role === "secretary" ? <SecretaryOneInstructorPage/> : <Navigate to="/login" replace={true}/>}/>

          {/* Instructor Pages */}
          <Route path="/assigned_internships" element={user.role === "instructor" ? <InstructorAssignedInternshipsPage/> : <Navigate to="/login" replace={true}/>}/>
          <Route path="/assigned_internships/:internshipId" element={user.role === "instructor" ? <InstructorOneInternshipPage/> : <Navigate to="/login" replace={true}/>}/>

          {/* Student Pages */}
          <Route path="/my_internships" element={user.role === "student" ? <StudentMyInternshipsPage/> : <Navigate to="/login" replace={true}/>}/>
          <Route path="/my_internships/:internshipType" element={user.role === "student" ? <StudentOneInternshipPage/> : <Navigate to="/login" replace={true}/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
