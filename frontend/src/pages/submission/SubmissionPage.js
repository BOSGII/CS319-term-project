import { Container, Typography } from '@mui/material'
import { useContext } from 'react'
import { UserContext } from '../../contexts/UserContext'
import { useParams } from 'react-router-dom';
import { useFetch } from '../../hooks/useFetch';
import Submission from '../../components/Submission/Submission';
import SubmissionSidebar from '../../components/SubmissionSidebar/SubmissionSidebar';

// styles
import './SubmissionPage.css'

export default function SubmissionPage(){
    const {user} = useContext(UserContext);
    const {internshipTypeOrId} = useParams();
    let fetchUrl;

    switch(user.role){
        case "student":
            const studentId = user.id;
            fetchUrl = `/internships?studentId=${studentId}&internshipType=${internshipTypeOrId}`;
            break;

        case "instructor":
            fetchUrl = `/internships/${internshipTypeOrId}`;
            break;

        default:
    }

    const {internship, isPending, error} = useFetch(fetchUrl);

    if(internship){

    }

    return(
        <Container>
            <Typography>
                student screen showing all internships (299 and 399)
            </Typography>
            {error && <div>{error}</div>}
            {isPending && <div>loading...</div>}
            {internship && <><SubmissionSidebar/><Submission/></>}
        </Container>
    )
}