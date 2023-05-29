import {List, Typography, Container, Stack, Box} from "@mui/material";
import Secretary from "../Secretary/Secretary";
import Instructor from "../Instructor/Instructor";

//Secretary List to be shown on the Admin Page
export default function SecretaryList({secretaries, refreshSecretaries}){

    return(
        <Container>
            {secretaries && secretaries.length > 0 && (
            <Typography>
                <Stack spacing={30} direction='row' marginLeft={1}>
                    <Box>Secretary Name</Box> <Box>Secretary Email</Box>
                </Stack>
            </Typography> )}
            <List>
                {secretaries?.map( (secretary) => (
                    <Secretary
                        key = {secretary.id}
                        secretary = {secretary}
                        refreshSecretaries = {refreshSecretaries}
                    />
                ))
                }
            </List>
        </Container>
    )
}