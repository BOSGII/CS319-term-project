import InstructorHomePage from './InstructorHomePage'
import SecretaryHomePage from './SecretaryHomePage'
import StudentHomePage from './StudentHomePage'
import { useContext } from 'react'
import { UserContext } from '../../contexts/UserContext'

// styles
import './HomePage.css'

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