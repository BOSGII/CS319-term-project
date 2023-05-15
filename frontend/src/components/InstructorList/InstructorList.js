import { List, ListItem, ListItemText, Container, Typography, Button } from '@mui/material'
import { useNavigate } from 'react-router-dom';
import { useFetch } from '../../hooks/useFetch';

export default function InstructorList({ instructors, deleteInstructor, refreshList }){
    const navigate = useNavigate();

    const handleClick = (instructorId) => {
        deleteInstructor(instructorId);
        refreshList();
    }

    return(
        <Container>
            <Typography>
                InstructorList Component
            </Typography>
            <List>
                {instructors?.map(instructor => (
                <ListItem key={instructor.id} sx={{border: 1}}>
                    <ListItemText>
                        {instructor.fullName}--{instructor.department}--{instructor.numOfAssignedInternships}/{instructor.maxNumOfInternships}--
                    </ListItemText>
                    <Button onClick={() => {navigate(`/instructors/${instructor.id}`)}}>
                        See Internships
                    </Button>
                    <Button onClick={() => {handleClick(instructor.id)}}>
                        Delete
                    </Button>
                </ListItem>
                ))}
            </List>
        </Container>
    )
}