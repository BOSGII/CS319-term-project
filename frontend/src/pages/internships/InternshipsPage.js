import { Container, Typography } from '@mui/material'
import { useContext } from 'react'
import { UserContext } from '../../contexts/UserContext'
import { useFetch } from '../../hooks/useFetch';
import InternshipList from '../../components/InternshipList/InternshipList';

import './InternshipsPage.css'
import { useLocation, useParams } from 'react-router-dom';

export default function InternshipsPage(){
    const {user} = useContext(UserContext);
    const location = useLocation();
    let fetchUrl;

    switch(user.role){
        case "student":
            fetchUrl = `/internships?studentId=${user.id}`;
            break;

        case "instructor":
            fetchUrl = `/internships?instructorId=${user.id}`;
            break;

        case "secretary":
            if(location.pathname === "/internships"){
                fetchUrl = `/internships`;
            }
            else { // /instructors/{instructorId}
                const instructorId = location.pathname.split('/')[-1];
                fetchUrl = `/internships?instructorId=${instructorId}`;
            }
            break;

        default:
    }

    const {internships, isPending, error} = useFetch(fetchUrl);

    return(
        <Container>
            <Typography>
                student screen showing all internships (299 and 399)
            </Typography>
            {error && <div>{error}</div>}
            {isPending && <div>loading...</div>}
            {internships && <InternshipList internships={internships}/>}
        </Container>
    )
}