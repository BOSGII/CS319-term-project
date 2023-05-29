import {IconButton} from "@mui/material";
import ClearIcon from '@mui/icons-material/Clear';
export default function DeleteSecretaryButton({ secretaryId, refreshSecretaries }){
    return (<IconButton> <ClearIcon color="error"></ClearIcon></IconButton>);
}