// styles
import './HomePage.css'
import AdminHomePage from '../../admin_pages/home/AdminHomePage'
import InstructorHomePage from '../../instructor_pages/home/InstructorHomePage'
import SecretaryHomePage from '../../secretary_pages/home/SecretaryHomePage'
import StudentHomePage from '../../student_pages/home/StudentHomePage'
import TAHomePage from '../../ta_pages/home/TAHomePage'

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