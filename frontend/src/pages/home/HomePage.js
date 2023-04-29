// styles
import './HomePage.css'
import AdminHomePage from './home_admin/AdminHomePage'
import InstructorHomePage from './home_instructor/InstructorHomePage'
import SecretaryHomePage from './home_secretary/SecretaryHomePage'
import StudentHomePage from './home_student/StudentHomePage'
import TAHomePage from './home_ta/TAHomePage'

export default function HomePage(){
    // TODO: determine role based on authentication
    const role = "student";

    switch(role){
        case "admin":
            return <AdminHomePage/>;
        case "instructor":
            return <InstructorHomePage/>;
        case "secretary":
            return <SecretaryHomePage/>;
        case "student":
            return <StudentHomePage/>;
        case "ta":
            return <TAHomePage/>;
        default:
            return <div>No role specified</div>
    }
}