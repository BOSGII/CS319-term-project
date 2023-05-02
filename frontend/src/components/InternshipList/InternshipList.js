import {List, ListItem, ListItemText, Button, Container, Modal, Box, FormGroup, TextField, FormLabel, Typography} from '@mui/material'
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';

// styles
import './InternshipList.css'

export default function InternshipList({ studentId, internships }){
    const [open, setOpen] = useState(false);
    const navigate = useNavigate();

    return(
        <Container>
            <Typography>
                InternshipList Component
            </Typography>
            <Button onClick={()=>{setOpen(true)}}>
                Add Internship
            </Button>
            <List>
                {internships?.map(internship => (
                <ListItem key={internship.type} sx={{border: 1}} onClick={()=>{navigate(`/students/${studentId}/internships/${internship.type}`)}}>
                    <ListItemText>
                        {internship.type}
                    </ListItemText>
                </ListItem>
                ))}
            </List>
        </Container>
    )
}