import { Button, Container, Typography } from '@mui/material'
import { useNavigate } from 'react-router-dom';

// styles
import './Internship.css'

export default function Internship( {internship} ){
    const navigate = useNavigate();
    
    return(
        <Container>
            <Typography>
                Internship Component
            </Typography>
            <Container sx={{margin: "15px auto",display: "flex"}}>
                <Typography>
                    {`Type: ${internship.type}`}
                </Typography>
                <hr></hr>
                <Typography>
                    {`CompanyName: ${internship.company.name}`}
                </Typography>
                <hr></hr>
                <Typography>
                    {`Start Date: ${internship.startDate.split("T")[0]}`}
                </Typography>
                <hr></hr>
                <Typography>
                    {`End Date: ${internship.endDate.split("T")[0]}`}
                </Typography>
                <hr></hr>
                <Button onClick={() => {navigate(`/students/${internship.student.id}/internships/${internship.type}/submission`)}}>
                    View Submission
                </Button>
            </Container>
        </Container>
    )
}