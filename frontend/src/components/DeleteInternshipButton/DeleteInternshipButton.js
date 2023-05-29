
import { IconButton, Tooltip } from "@mui/material";
import CloseIcon from "@mui/icons-material/Close";


import axios from "axios";

export default function DeleteInternshipButton({
  internshipId,
  refreshInternships,
}) {
  const sessionId = localStorage.getItem("sessionId");

  const handleDeleteInternship = () => {
    axios
      .delete(`http://localhost:8080/api/internships/${internshipId}`, {
        headers: {
          Authorization: `${sessionId}`,
        },
      })
      .then(() => {
        refreshInternships();
      })
      .catch((error) => {
        alert("You cannot delete an internship once the company evaluation form is uploaded!");
      });
  };


  return (
    <Tooltip title="Delete Internship">
    <IconButton onClick={handleDeleteInternship}>
      <CloseIcon color="error"></CloseIcon>
    </IconButton>
    </Tooltip>
  );

}
