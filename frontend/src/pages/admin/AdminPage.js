import { Container, Typography } from "@mui/material";
import { useLocation } from "react-router-dom";
import { UserContext } from "../../contexts/UserContext";
import { useContext, useEffect, useState } from "react";

export default function AdminPage(){
    const { user } = useContext(UserContext);
    const location = useLocation();
    return(
        <Container sx={{mt: 15}}>
            <Typography> Welcome to Admin Page.</Typography>
        </Container>
    );
}