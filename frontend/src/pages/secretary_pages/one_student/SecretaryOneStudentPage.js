import { Container, Typography } from '@mui/material'
import Student from '../../../components/Student/Student';
import { useParams } from 'react-router-dom';
import { useFetch } from '../../../hooks/useFetch';

// styles
import './SecretaryOneStudentPage.css'

export default function SecretaryOneStudentPage(){
    const {studentId} = useParams();
    const {data, isPending, error} = useFetch(`/api/students/${studentId}`);
    return(
        <Container>
            <Typography>
                Secretary page showing one student
            </Typography>
            {error && <div>{error}</div>}
            {isPending && <div>loading...</div>}
            {data && <Student student={data}/>}
        </Container>
    )
}