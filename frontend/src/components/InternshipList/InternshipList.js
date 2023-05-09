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
                <ListItem key={internship.id} sx={{border: 1}} onClick={()=>{
                    switch(user.role){
                        case "student":
                            if(internship.status === "UNDER_EVALUATION" 
                            || internship.status === "FAIL_UNSATISFACTORY_REPORT" 
                            || internship.status ===  "SUCCESSFUL"){
                                navigate(`/my_internships/${internship.type}`);
                            }
                            else if (internship.status === "NOT_SUBMITTED"){
                                navigate(`/submit?internshipType=${internship.type}`);
                            }
                            break;
                        case "instructor":
                            console.log(internship.status)
                            if(internship.status === "UNDER_EVALUATION" 
                            || internship.status === "FAIL_UNSATISFACTORY_REPORT" 
                            || internship.status ===  "SUCCESSFUL"){
                                navigate(`/internships/${internship.id}`)
                            }
                            break;
                        case "secretary":
                            // TODO: decide whether secretary will see submission screen
                            break;
                        default:

                    }
                    }}>
                    <ListItemText>
                        {internship.type} Student Id:{internship.student.id}
                    </ListItemText>
                </ListItem>
                ))}
            </List>
        </Container>
    )
}