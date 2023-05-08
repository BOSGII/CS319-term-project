import { Container, Typography } from '@mui/material'

import './StudentMyInternshipsPage.css'
import { useContext } from 'react'
import { UserContext } from '../../../contexts/UserContext'
import { useFetch } from '../../../hooks/useFetch';
import InternshipList from '../../../components/InternshipList/InternshipList';

export default function StudentInternshipsPage(){
    const {user} = useContext(UserContext);
    const studentId = user.id;

    const {data, isPending, error} = useFetch(`/internships?studentId=${studentId}`);

    return(
        <Container>
            <Typography>
                student screen showing all internships (299 and 399)
            </Typography>
            {error && <div>{error}</div>}
            {isPending && <div>loading...</div>}
            {data && <InternshipList internships={data}/>}
        </Container>
    )
}