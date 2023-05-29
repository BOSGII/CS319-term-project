
import { IconButton, Tooltip } from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";


import axios from "axios";

export default function DeleteInstructorButton({
  instructorId,
  refreshInstructors,
}) {
  const sessionId = localStorage.getItem("sessionId");

  const handleDeleteInstructor = () => {
    axios
      .delete(`http://localhost:8080/api/instructors/${instructorId}`, {
        headers: {
          Authorization: `${sessionId}`,
        },
      })
      .then(() => {
        refreshInstructors();
      })
      .catch(() => {
        console.log("delete instructor error");
      });
  };


  return (
    <Tooltip title="Delete Instructor">
    <IconButton onClick={handleDeleteInstructor}>
      <CloseIcon color="error"></CloseIcon>
    </IconButton>
    </Tooltip>
  );

}
