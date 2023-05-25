import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../../contexts/UserContext";
import { IconButton } from "@mui/material";
import { InternshipIDContext } from "../../contexts/InternshipIDContext";

import VisibilityIcon from '@mui/icons-material/Visibility';

export default function SeeSubmissionButton({
  internshipId,
  internshipType,
  internshipStatus,
}) {
  const navigate = useNavigate();
  const { user } = useContext(UserContext);
  const { setInternshipId } = useContext(InternshipIDContext);

  const redirectToSubmissionPage = () => {
    setInternshipId(internshipId);
    switch (user.role) {
      case "student":
        if (
          internshipStatus === "UNDER_EVALUATION" ||
          internshipStatus === "FAIL_UNSATISFACTORY_REPORT" ||
          internshipStatus === "SUCCESSFUL" ||
          internshipStatus === "NOT_SUBMITTED"
        ) {
          navigate(`/my_internships/${internshipType}`);
        }
        break;
      case "instructor":
        console.log(internshipStatus);
        if (
          internshipStatus === "UNDER_EVALUATION" ||
          internshipStatus === "FAIL_UNSATISFACTORY_REPORT" ||
          internshipStatus === "SUCCESSFUL"
        ) {
          navigate(`/internships/${internshipId}`);
        }
        break;
      case "secretary":
        // TODO: decide whether secretary will see submission screen
        break;
      default:
    }
  };

<<<<<<< HEAD
  return <Button variant= "outlined" onClick={redirectToSubmissionPage}>Submission</Button>;
=======
  return <IconButton onClick={redirectToSubmissionPage}><VisibilityIcon></VisibilityIcon></IconButton>;
>>>>>>> 072d63e (Styling improvements in Instructors and Internships)
}
