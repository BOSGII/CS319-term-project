import { Button, Container, Typography } from '@mui/material'
import { useNavigate } from 'react-router-dom';

// styles
import './Student.css'

export default function Student( {student} ){
    const navigate = useNavigate();
    
    return(
        <Container>
            <Typography>
                Student Component
            </Typography>
            <Container sx={{margin: "15px auto",display: "flex"}}>
                <Typography>
                    {`ID: ${student.id}`}
                </Typography>
                <hr></hr>
                <Typography>
                    {`Name: ${student.fullName}`}
                </Typography>
                <hr></hr>
                <Typography>
                    {`Department: ${student.department}`}
                </Typography>
                <hr></hr>
                <Typography>
                    {`Mail: ${student.mail}`}
                </Typography>
                <hr></hr>
                <Button onClick={() => {navigate(`/students/${student.id}/internships`)}}>
                    View Internships
                </Button>
            </Container>
        </Container>
    )
}