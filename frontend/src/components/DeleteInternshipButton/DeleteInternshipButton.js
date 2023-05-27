import { Button } from "@mui/material";
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

  return <Button variant ="contained" onClick={handleDeleteInternship}>Delete</Button>;
}
