// styles
import './HomePage.css'
import InstructorHomePage from '../../instructor_pages/home/InstructorHomePage'
import SecretaryHomePage from '../../secretary_pages/home/SecretaryHomePage'
import StudentHomePage from '../../student_pages/home/StudentHomePage'
import { useContext } from 'react'
import { UserContext } from '../../../contexts/UserContext'

export default function HomePage(){
    // TODO: determine role based on authentication
    const {user} = useContext(UserContext);

    switch(user.role){
        case "admin":
            return <div>AdminHomePage</div>;
        case "instructor":
            return <InstructorHomePage/>;
        case "secretary":
            return <SecretaryHomePage/>;
        case "student":
            return <StudentHomePage/>;
        case "ta":
            return <div>TAHomePage</div>;
        default:
            return <div>No role specified</div>
    }
}