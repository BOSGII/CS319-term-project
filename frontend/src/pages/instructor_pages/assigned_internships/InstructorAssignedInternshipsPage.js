import { Container, Typography } from '@mui/material'

import './InstructorAssignedInternshipsPage.css'
import { useContext } from 'react'
import { UserContext } from '../../../contexts/UserContext'
import { useFetch } from '../../../hooks/useFetch';
import InternshipList from '../../../components/InternshipList/InternshipList';

export default function InstructorAssignedInternshipsPage () {
    const {user} = useContext(UserContext);
    const instructorId = user.id;

    const {internships, isPending, error} = useFetch(`/internships?instructorId=${instructorId}`);

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