import { Container, Typography } from '@mui/material'

import { useFetch } from '../../../hooks/useFetch'

// styles
import './SecretaryStudentsPage.css'
import StudentList from '../../../components/StudentList/StudentList';

export default function SecretaryStudentsPage(){
    const {data, isPending, error} = useFetch("/api/students");
    return(
        <Container>
            <Typography>
                Secretary screen showing all students
            </Typography>
            {error && <div>{error}</div>}
            {isPending && <div>loading...</div>}
            {data && <StudentList students={data}/>}
        </Container>
    )
}