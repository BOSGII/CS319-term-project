import { List, ListItem, ListItemText, Container, Typography } from '@mui/material'
import { useNavigate } from 'react-router-dom';
import { useContext } from 'react';
import { UserContext } from '../../contexts/UserContext';

// styles
import './InternshipList.css'

export default function InternshipList({ internships }){
    const navigate = useNavigate();
    const {user} = useContext(UserContext);

    return(
        <Container>
            <Typography>
                InternshipList Component
            </Typography>
            <List>
                {internships?.map(internship => (
                <ListItem key={internship.type} sx={{border: 1}} onClick={()=>{
                    switch(user.role){
                        case "student":
                            if(internship.status === "NOT_SUBMITTED" || internship.status === "UNDER_EVALUATION" || internship.status === "FAIL_UNSATISFACTORY_REPORT" || internship.status === "SUCCESSFUL"){
                                navigate(`/my_internships/${internship.type}`);
                            }
                            break;
                        case "instructor":
                            navigate(`/assigned_internships/${internship.id}`)
                            break;
                        case "secretary":
                            navigate(`/internships/${internship.id}`)
                            break;
                        default:

                    }
                    }}>
                    <ListItemText>
                        {internship.type}
                        {internship.status}
                    </ListItemText>
                </ListItem>
                ))}
            </List>
        </Container>
    )
}