import { Container, Typography } from '@mui/material'
import { useFetch } from '../../../hooks/useFetch';
import InstructorList from '../../../components/InstructorList/InstructorList';

// styles
import './SecretaryInstructorsPage.css'

export default function SecretaryInstructorsPage(){
    const {instructors, isPending, error } = useFetch(`/instructors`);

    return(
        <Container>
            <Typography>
                Secretary page showing all instructors
            </Typography>
            {error && <div>{error}</div>}
            {isPending && <div>loading...</div>}
            {instructors && <InstructorList instructors={instructors}/>}
        </Container>
    )
}