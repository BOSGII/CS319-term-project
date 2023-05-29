import { Button, Dialog, Stack, TextField} from "@mui/material";
import { useState } from 'react';
import axios from 'axios';

export default function SetDeadlineButton({refreshInternships}) {
    const sessionId = localStorage.getItem("sessionId");

    const [date, setDate] = useState(Date.now().toString());
    const [open, setOpen] = useState(false);

    const handleDateChange = (event) => {
        setDate(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (date) {
        axios
            .post('http://localhost:8080/api/deadline', new Date(date), { headers: {
                Authorization: `${sessionId}`,
                }
            }).then((response) => {
                refreshInternships();
                setOpen(false);
            }).catch((error) => {
                if (error.response.status === 400) {
                    alert(error.response.data);
                  }
            })
        }
    };

    return (
        <>
        <Button 
            variant="contained" 
            type="submit"
            onClick={() => {
            setOpen(true);
            }}
            >
            Set Deadline
        </Button>
        <Dialog
            open={open}
            onClose={() => {
            setOpen(false);
            }}
        >
        <Stack spacing={3} >
        <form onSubmit={handleSubmit}>
            <TextField 
                margin="normal"
                type="date" 
                label="Deadline"
                value={date} 
                onChange={handleDateChange} 
                defaultValue={date}/>
        </form>
        <Button 
            variant="contained" 
            type="submit"
            onClick={handleSubmit}
            >
            Set Deadline
        </Button>
        </Stack>
        </Dialog>
        </>
    );
    };
