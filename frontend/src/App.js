import './App.css';
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom"
import LoginPage from './pages/common_pages/login_page/LoginPage';
import HomePage from './pages/common_pages/home/HomePage';
import SecretaryStudentsPage from './pages/secretary_pages/students/SecretaryStudentsPage';
import SecretaryOneStudentPage from './pages/secretary_pages/one_student/SecretaryOneStudentPage';
import SecretaryOneStudentInternshipsPage from './pages/secretary_pages/one_student_internships/SecretaryOneStudentInternshipsPage';
import SecretaryOneStudentOneInternshipPage from './pages/secretary_pages/one_student_one_internship/SecretaryOneStudentOneInternshipPage';
import SecretaryInstructorsPage from './pages/secretary_pages/instructors/SecretaryInstructorsPage';
import SecretaryTAsPage from './pages/secretary_pages/tas/SecretaryTAsPage';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          {/* TODO: redirect to respective home page if authenticated */}
          <Route path="/" element={<Navigate to="/login"/>}/>
          <Route path="/login" element={<LoginPage/>}/>
          <Route path="/home" element={<HomePage/>}/>

          {/* Secretary Pages */}
          <Route path="/students" element={<SecretaryStudentsPage/>}/>
          <Route path="/students/:studentId" element={<SecretaryOneStudentPage/>}/>
          <Route path="/students/:studentId/internships" element={<SecretaryOneStudentInternshipsPage/>}/>
          <Route path="/students/:studentId/internships/:internshipType" element={<SecretaryOneStudentOneInternshipPage/>}/>
          <Route path="/instructors" element={<SecretaryInstructorsPage/>}/>
          <Route path="/tas" element={<SecretaryTAsPage/>}/>

          {/* Instructor Pages */}


          {/* Student Pages */}

        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
