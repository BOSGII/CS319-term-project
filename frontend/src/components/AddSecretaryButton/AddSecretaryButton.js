import {Button, Modal, TextField, Stack} from "@mui/material";
import {useState}from "react";
import axios from "axios";
import AddCircleIcon from '@mui/icons-material/AddCircle';
export default function AddSecretaryButton({refreshSecretaries}){

    const sessionId = localStorage.getItem("sessionId");

    const url = "";

    //state variables for secrataries
    const [secretary, setSecretary] = useState({
        id:"", 
        fullName:"", 
        mail:"",
    });

    //state variable for the modal
    const [open, setOpen] = useState(false);

    //input change 
    const handleInputChange = (event) => {
        setSecretary((prevState) => ({
            ...prevState,
            [event.target.name]: event.target.value,
        }));
    };

    //function to handle form submit
    const handleFormSubmit = async (event) => {
        event.preventDefault();
        axios
            .post("http://localhost:8080/api/secretaries", secretary,{
                headers: {
                  Authorization: `${sessionId}`,
                },
              })
            .then((response) => {
                refreshSecretaries();
                setOpen(false);
                setSecretary({
                    id:"", 
                    fullName:"", 
                    mail:"",
                });
            })
            .catch((error)=>{
                console.log("/secretaries post error");
            });

    };

    return(
        <>
            <Button variant="contained" onClick= {()=>setOpen(true)}>
                Add Secretary <AddCircleIcon></AddCircleIcon>
            </Button>
            <Modal sx={{
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                bgcolor: "rgba(0,0,0,0.5)",
            }}
            open={open}
            onClose={()=>setOpen(false)}>
                <form style={{backgroundColor: "white"}} onSubmit={handleFormSubmit}>
                
                    <TextField
                        label="ID"
                        name="id"
                        value={secretary.id}
                        margin="normal"
                        onChange={handleInputChange}>
                    </TextField>

                    <TextField
                        label="FULL NAME"
                        name="fullName"
                        value={secretary.fullName}
                        fullWidth
                        onChange={handleInputChange}>
                    </TextField>

                    <TextField
                        label="EMAIL"
                        name="mail"
                        value={secretary.mail}
                        fullWidth
                        onChange={handleInputChange}>
                    </TextField>

                    <Button variant="contained" type="submit" > Add Secretary<AddCircleIcon></AddCircleIcon></Button>

                </form>
            </Modal>
        </>
    );
}
