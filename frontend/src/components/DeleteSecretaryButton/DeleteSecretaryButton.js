import {IconButton, Tooltip} from "@mui/material";
import ClearIcon from '@mui/icons-material/Clear';
import axios from "axios";
export default function DeleteSecretaryButton({ secId, refreshSecretaries }){

    const sessionId = localStorage.getItem("sessionId");

    const handleSecretaryDelete = () => {
        axios
            .delete(`http://localhost:8080/api/secretaries/${secId}`, {
                headers: {
                  Authorization: `${sessionId}`,
                },
            })
            .then(()=> { refreshSecretaries();
            })
            .catch(() => {
                console.log("delete secretary error");
            });
    };

    return (<Tooltip title="Delete Secretary"><IconButton onClick={handleSecretaryDelete}> <ClearIcon color="error"></ClearIcon></IconButton>
        </Tooltip>);
}