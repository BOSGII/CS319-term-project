import './App.css';
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom"
import TAsPage from './pages/tas/TAsPage';
import StudentsPage from './pages/students/StudentsPage';
import HomePage from './pages/home/HomePage';
import LoginPage from './pages/login_page/LoginPage';
import InstructorsPage from './pages/instructors/InstructorsPage';
import InternshipsPage from './pages/internships/InternshipsPage';
import AssignedSubmissionsPage from './pages/assigned_submissions/AssignedSubmissionsPage';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          {/* TODO: redirect to respective home page if authenticated */}
          <Route path="/" element={<Navigate to="/login"/>}/>
          <Route path="/login" element={<LoginPage/>}/>
          <Route path="/home" element={<HomePage/>}/>
          <Route path="/students" element={<StudentsPage/>}/>
          <Route path="/instructors" element={<InstructorsPage/>}/>
          <Route path="/internships" element={<InternshipsPage/>}/>
          <Route path="/assigned_submissions" element={<AssignedSubmissionsPage/>}/>
          <Route path="/tas" element={<TAsPage/>}/>
        </Routes>
      </BrowserRouter>
      
    </div>
  );
}

export default App;
