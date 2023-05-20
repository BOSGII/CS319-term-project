import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../../contexts/UserContext";
import { Button } from "@mui/material";

export default function SeeSubmissionButton({
  internshipId,
  internshipType,
  internshipStatus,
}) {
  const navigate = useNavigate();
  const { user } = useContext(UserContext);

  const redirectToSubmissionPage = () => {
    switch (user.role) {
      case "student":
        if (
          internshipStatus === "UNDER_EVALUATION" ||
          internshipStatus === "FAIL_UNSATISFACTORY_REPORT" ||
          internshipStatus === "SUCCESSFUL"
        ) {
          navigate(`/my_internships/${internshipType}`);
        } else if (internshipStatus === "NOT_SUBMITTED") {
          navigate(`/submit?internshipType=${internshipType}`);
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

  return <Button onClick={redirectToSubmissionPage}>Submission</Button>;
}
