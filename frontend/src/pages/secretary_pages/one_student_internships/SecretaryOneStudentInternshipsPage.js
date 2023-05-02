import { Container, Typography } from '@mui/material'
import InternshipList from '../../../components/InternshipList/InternshipList';
import { useParams } from 'react-router-dom';
import { useFetch } from '../../../hooks/useFetch';

// styles
import './SecretaryOneStudentInternshipsPage.css'

export default function SecretaryOneStudentInternshipsPage(){
    const {studentId} = useParams();
    const {data, isPending, error} = useFetch(`/api/students/${studentId}/internships`);
    
    return(
        <Container>
            <Typography>
                Secretary page showing internships of a student
            </Typography>
            {error && <div>{error}</div>}
            {isPending && <div>loading...</div>}
            {data && <InternshipList studentId={studentId} internships={data}/>}
        </Container>
    )
}