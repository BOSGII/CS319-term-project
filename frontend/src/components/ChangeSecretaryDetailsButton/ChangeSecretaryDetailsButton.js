import {IconButton,Tooltip, TextField, Button,Modal} from "@mui/material";
import EditIcon from '@mui/icons-material/Edit';
import { useState } from "react";
import axios  from "axios";

export default function ChangeSecretaryDetailsButton({secretary, refreshSecretaries}){
    const sessionId = localStorage.getItem("sessionId");
    const [open, setOpen] = useState(false);
    const[secretaryFullName, setSecretaryFullName] = useState( secretary.fullName );
    const[secretaryMail, setSecretaryMail] = useState( secretary.mail );

    const secId = secretary.id;
    const handleSubmit = (event) => {
        event.preventDefault();

        axios
        .put(
            `http://localhost:8080/api/secretaries/${secId}`,
            {
              fullName: secretaryFullName,
              mail: secretaryMail,
            },
            { headers: { Authorization: `${sessionId}` } }
        )
        .then((response) => {
            refreshSecretaries();
            setOpen(false);
        })
        .catch((error) => {
            if (error.response.status === 400) {
              alert(error.response.data);
            }
            console.log("/api/secretaries put error:", error.message);
        });
    };
    return (
        <>
        <Tooltip title="Change Secretary Details">
        <IconButton onClick={() => {
            setOpen(true);
        }}>
            <EditIcon></EditIcon>
        </IconButton>
        </Tooltip>
        <Modal sx={{
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                bgcolor: "rgba(0,0,0,0.5)",
            }}
            open={open}
            onClose={()=>setOpen(false)}
            >
                <form style={{backgroundColor: "white"}} onSubmit={handleSubmit}>
                
                    <TextField
                        margin="normal"
                        label="FULL NAME"
                        type="text"
                        value={secretaryFullName}
                        onChange={(event) => {
                          setSecretaryFullName(event.target.value);
                        }}
                        fullWidth
                    >    
                    </TextField>

                    <TextField
                        margin="normal"
                        label="EMAIL"
                        type="text"
                        value={secretaryMail}
                        onChange={(event) => {
                          setSecretaryMail(event.target.value);
                        }}
                        fullWidth
                      >
                    </TextField>

                    <Button type="submit" variant="contained"> Submit </Button>
                </form>
            </Modal>
        </>

    );
      
}