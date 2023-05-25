import { IconButton } from "@mui/material";
import CloseIcon from '@mui/icons-material/Close';

import axios from "axios";

export default function DeleteInstructorButton({
  instructorId,
  refreshInstructors,
}) {
  const handleDeleteInstructor = () => {
    axios
      .delete(`/api/instructors/${instructorId}`)
      .then(() => {
        refreshInstructors();
      })
      .catch(() => {
        console.log("delete instructor error");
      });
  };

  return <IconButton onClick={handleDeleteInstructor}><CloseIcon color="error"></CloseIcon></IconButton>;
}
