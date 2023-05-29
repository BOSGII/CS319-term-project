import { Container, Typography, Button } from "@mui/material";
import { useLocation } from "react-router-dom";
import { UserContext } from "../../contexts/UserContext";
import { useContext, useEffect, useState } from "react";
import SecretaryList from "../../components/SecretaryList/SecretaryList";
import AddSecretaryButton from "../../components/AddSecretaryButton/AddSecretaryButton";
import axios from "axios";

//Admin Page adds secretaries, views them, edits them
export default function AdminPage(){
    const sessionId = localStorage.getItem("sessionId");

    
    const [secretaries, setSecretaries] = useState([]); //setting secreatries, changing them
    const [isPending, setIsPending] = useState(false); //for loading
    const [refresh, setRefresh] = useState(false); //for refreshing
    const [error, setError] = useState(null); //for error handling

    const refreshSecretaries = () => {
        setRefresh(true);
    }

    useEffect( ()=>{
        const getSecretariesFromServer = () => {
            setIsPending(true);
            axios
            .get("http://localhost:8080/api/secretaries", {
                headers: {
                  Authorization: `${sessionId}`,
                },
            })
            .then((response) => {
                setSecretaries(response.data); 
                console.log(sessionId);
            })
            .catch((error)=> {setError(error);})
            .finally(()=> {
                setIsPending(false);
                setRefresh(false);
            });
        };
        getSecretariesFromServer();
    }, [refresh]);

    return(
        <Container sx={{mt: 15}}>

            <Typography> Welcome to Admin Page. You can add secretaries from below button </Typography>

            <AddSecretaryButton refreshSecretaries={refreshSecretaries}/>
            <Typography> SECRETARIES </Typography>

            {error && <div>{error.message} </div>}
            {isPending && <div>loading...</div>}
            {secretaries && <SecretaryList
            secretaries = {secretaries}
            refreshSecretaries = {refreshSecretaries}
            />}

        </Container>
    );
}