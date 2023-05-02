import { Container, Typography } from '@mui/material'
import Internship from '../../../components/Internship/Internship'
import { useParams } from 'react-router-dom';
import { useFetch } from '../../../hooks/useFetch';

// styles
import './SecretaryOneStudentOneInternshipPage.css'

export default function SecretaryOneStudentOneInternshipPage(){
    const {studentId, internshipType} = useParams();
    const {data, isPending, error} = useFetch(`/api/students/${studentId}/internships/${internshipType}`);

    return(
        <Container>
            <Typography>
                Secretary page showing details about one internship of a student
            </Typography>
            {error && <div>{error}</div>}
            {isPending && <div>loading...</div>}
            {data && <Internship studentId={studentId} internship={data}/>}
        </Container>
    )
}