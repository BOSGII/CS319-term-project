import { Navigate } from 'react-router-dom'

// styles
import './InstructorHomePage.css'

export default function InstructorHomePage(){
    return(
        <Navigate to="/internships" replace={true}/>
    )
}