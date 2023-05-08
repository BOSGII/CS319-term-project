import { Container, Typography } from '@mui/material'
import { useFetch } from '../../../hooks/useFetch';
import InternshipList from '../../../components/InternshipList/InternshipList';

// styles
import './SecretaryOneInstructorPage.css'
import { useParams } from 'react-router-dom';

export default function SecretaryOneInstructorPage(){
    const {instructorId} = useParams();

    const {internships, isPending, error} = useFetch(`/internships?${instructorId}`)

    return(
        <Container>
            <Typography>
                instructor screen showing all assigned internships
            </Typography>
            {error && <div>{error}</div>}
            {isPending && <div>loading...</div>}
            {internships && <InternshipList internships={internships}/>}
        </Container>
    )
}