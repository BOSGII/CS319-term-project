import { IconButton } from "@mui/material";
import CloseIcon from '@mui/icons-material/Close';

import axios from "axios";

export default function DeleteInstructorButton({
  instructorId,
  refreshInstructors,
}) {
  const handleDeleteInstructor = () => {
    const sessionId = localStorage.getItem('sessionId');
    axios
      .delete(`http://localhost:8080/api/instructors/${instructorId}`, {
        headers: {
          Authorization: `${sessionId}`
        }
      })
      .then(() => {
        refreshInstructors();
      })
      .catch(() => {
        console.log("delete instructor error");
      });
  };

  return <IconButton onClick={handleDeleteInstructor}><CloseIcon color="error"></CloseIcon></IconButton>;
}
