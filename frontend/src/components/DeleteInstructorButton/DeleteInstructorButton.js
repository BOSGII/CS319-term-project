import { Button } from "@mui/material";
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

  return <Button onClick={handleDeleteInstructor}>Delete</Button>;
}
