import { Navigate } from 'react-router-dom'

// styles
import './StudentHomePage.css'

export default function StudentHomePage(){
    return(
        <Navigate to="/my_internships" replace={true}/>
    )
}