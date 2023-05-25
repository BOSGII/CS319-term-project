import { IconButton } from "@mui/material";
import CloseIcon from '@mui/icons-material/Close';

import axios from "axios";

export default function DeleteInternshipButton({
  internshipId,
  refreshInternships,
}) {
  const handleDeleteInternship = () => {
    axios
      .delete(`/api/internships/${internshipId}`)
      .then(() => {
        refreshInternships();
      })
      .catch(() => {
        console.log("delete internship error");
      });
  };

<<<<<<< HEAD
  return <Button variant ="contained" onClick={handleDeleteInternship}>Delete</Button>;
=======
  return <IconButton onClick={handleDeleteInternship}>
    <CloseIcon color="error"></CloseIcon>
  </IconButton>;
>>>>>>> 072d63e (Styling improvements in Instructors and Internships)
}
