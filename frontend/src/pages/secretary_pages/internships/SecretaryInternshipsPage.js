import { Container, Typography } from '@mui/material'
import { useFetch } from '../../../hooks/useFetch';
import InternshipList from '../../../components/InternshipList/InternshipList';

// styles
import './SecretaryInternshipsPage.css'

export default function SecretaryInternshipsPage(){
    const {internships, isPending, error} = useFetch(`/internships`)

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