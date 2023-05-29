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
      .catch(() => {
        console.log("delete internship error");
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
