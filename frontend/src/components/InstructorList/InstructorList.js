import { List, ListItem, ListItemText, Container, Typography } from '@mui/material'
import { useNavigate } from 'react-router-dom';

export default function InstructorList({ instructors }){
    const navigate = useNavigate();

    return(
        <Container>
            <Typography>
                InstructorList Component
            </Typography>
            <List>
                {instructors?.map(instructor => (
                <ListItem key={instructor.id} sx={{border: 1}} onClick={()=>{navigate(`/instructors/${instructor.id}`)}}>
                    <ListItemText>
                        {instructor.fullName}
                        {instructor.department}
                    </ListItemText>
                </ListItem>
                ))}
            </List>
        </Container>
    )
}