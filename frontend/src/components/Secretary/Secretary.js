import {
    Button, 
    ListItem, 
    ListItemText,
    Stack, 
    Box, 
    Card } from "@mui/material";
import { 
    useState, 
    useEffect, 
    useNavigate } from "react";
import ChangeSecretaryDetailsButton from "../ChangeSecretaryDetailsButton/ChangeSecretaryDetailsButton";
import DeleteSecretaryButton from "../DeleteSecretaryButton/DeleteSecretaryButton";

//Secretary in Secretary List to be shown on the Admin Page
export default function Secretary({secretary, refreshSecretaries}){

    const[fullName, setFullName] = useState( secretary.fullName );
    const[mail, setMail] = useState( secretary.mail );

    useEffect( () => {
        setFullName(secretary.fullName);
        setMail(secretary.mail);
    }, [secretary]);

    return( 
        <Card elevation={10} style={{ borderRadius: 15 }}>
            <ListItem >
                <Stack spacing={70} direction='row'>
                    <ListItemText>
                        <Stack spacing={40} direction='row'>
                            <Box sx={{maxWidth: 30}}>{fullName}</Box><Box sx={{maxWidth: 30}}>{mail}</Box>
                        </Stack>
                    </ListItemText>
                <Stack spacing={5}direction='row'>
                    <Box>
                        <ChangeSecretaryDetailsButton secretary={secretary} refreshSecretaries = {refreshSecretaries}/>
                    </Box>
                    <Box>
                        <DeleteSecretaryButton
                        secId = {secretary.id}
                        refreshSecretaries = {refreshSecretaries}
                        />
                    </Box>
                </Stack>
            </Stack>
        
        </ListItem>
    </Card>
        
    );

}