import { Container, Typography } from "@mui/material";
import { Navigate } from "react-router-dom";

export default function AdminHomePage(){
    return(
        <Navigate to="/admin" replace={true}/>
    );
}