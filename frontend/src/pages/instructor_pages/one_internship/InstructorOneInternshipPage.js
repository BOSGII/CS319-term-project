import { Container } from '@mui/material'

// styles
import './InstructorOneInternshipPage.css'
import { useParams } from 'react-router-dom';
import { useFetch } from '../../../hooks/useFetch';
import Submission from '../../../components/Submission/Submission';
import SubmissionSidebar from '../../../components/SubmissionSidebar/SubmissionSidebar';
import { useState } from 'react';

export default function InstructorOneInternshipPage(){
    const {internshipId} = useParams();

    const {internship, isPending, error} = useFetch(`/internships/${internshipId}`);

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