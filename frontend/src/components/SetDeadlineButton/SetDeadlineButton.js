import { Button, Dialog, Stack} from "@mui/material";
import { useState } from 'react';
import axios from 'axios';

export default function SetDeadlineButton({refreshInternships}) {
    const sessionId = localStorage.getItem("sessionId");

    const [date, setDate] = useState('');
    const [open, setOpen] = useState(false);

    const handleDateChange = (event) => {
        setDate(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        axios
            .post('http://localhost:8080/api/deadline', {
                date: date
            }, { headers: {
                Authorization: `${sessionId}`,
                }
            }).then((response) => {
                refreshInternships();
            }).catch((error) => {
                console.error(error);
            })
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
            <label>
            Deadline:
            <input type="date" value={date} onChange={handleDateChange} />
            </label>
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
