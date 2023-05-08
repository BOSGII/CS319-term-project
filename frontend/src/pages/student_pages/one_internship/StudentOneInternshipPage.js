import { Container } from '@mui/material'
import { useContext, useState } from 'react'
import { UserContext } from '../../../contexts/UserContext'
import { useParams } from 'react-router-dom';
import { useFetch } from '../../../hooks/useFetch';
import Submission from '../../../components/Submission/Submission';
import SubmissionSidebar from '../../../components/SubmissionSidebar/SubmissionSidebar';

// styles
import './StudentOneInternshipPage.css'

export default function StudentOneInternshipPage(){
    const {user} = useContext(UserContext);
    const studentId = user.id;
    const {internshipType} = useParams();

    const {internship, isPending, error} = useFetch(`/internships?studentId=${studentId}&internshipType=${internshipType}`)

    const [isSidebarOpen, setIsSidebarOpen] = useState(false);

    const handleToggleSidebar = () => {
      setIsSidebarOpen(!isSidebarOpen);
    };
  
    if(isPending){
        return <div>Loading ...</div>
    } else if(error){
        return <div>{error}</div>
    }
    else {
        return (<Container>
                    <SubmissionSidebar numberOfVersions={internship.numberOfVersions} isSidebarOpen={isSidebarOpen} onToggleSidebar={handleToggleSidebar}/>
                    <Submission internship={internship}/>
                </Container>
        );
    }
}