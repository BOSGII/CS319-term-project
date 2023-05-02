import {List, ListItem, ListItemText, Button, Container, Modal, Box, FormGroup, TextField, FormLabel, Typography} from '@mui/material'
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';

// styles
import './StudentList.css'

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

export default function StudentList({ students }){
    const [open, setOpen] = useState(false);
    const navigate = useNavigate();

    return(
        <Container>
            <Typography>
                StudentList Component
            </Typography>
            <Button onClick={()=>{setOpen(true)}}>
                Add Student
            </Button>
            <Modal
                open={open}
                onClose={()=>{setOpen(false)}}
            >
                <Box sx={style}>
                    <FormGroup>
                        <FormLabel>
                            ID:
                        </FormLabel>
                        <TextField
                            required
                            name="id"
                        />
                        <FormLabel>
                            Name:
                        </FormLabel>
                        <TextField
                            required
                            name="fullName"
                        />
                        <FormLabel>
                            Department:
                        </FormLabel>
                        <TextField
                            required
                            name="department"
                        />
                        <FormLabel>
                            Email:
                        </FormLabel>
                        <TextField
                            required
                            name="mail"
                        />
                        <Button type="submit">
                            Submit
                        </Button>
                    </FormGroup>
                </Box>
            </Modal>
            <List>
                {students?.map(student => (
                <ListItem key={student.id} sx={{border: 1}} onClick={()=>{navigate(`/students/${student.id}`)}}>
                    <ListItemText>
                        {student.fullName}
                    </ListItemText>
                </ListItem>
                ))}
            </List>
        </Container>
    )
}