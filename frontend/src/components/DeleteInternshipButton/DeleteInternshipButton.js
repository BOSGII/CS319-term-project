import { IconButton } from "@mui/material";
import CloseIcon from '@mui/icons-material/Close';

import axios from "axios";

export default function DeleteInternshipButton({
  internshipId,
  refreshInternships,
}) {
  const handleDeleteInternship = () => {
    const sessionId = localStorage.getItem('sessionId');
    axios
      .delete(`http://localhost:8080/api/internships/${internshipId}` , {
        headers: {
          Authorization: `${sessionId}`
        }
      })
      .then(() => {
        refreshInternships();
      })
      .catch(() => {
        console.log("delete internship error");
      });
  };

  return <IconButton onClick={handleDeleteInternship}>
    <CloseIcon color="error"></CloseIcon>
  </IconButton>;
}
