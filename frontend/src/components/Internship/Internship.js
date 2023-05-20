import { ListItem, ListItemText } from "@mui/material";
import { useContext, useEffect, useState } from "react";
import ChangeInternshipDetailsButton from "../ChangeInternshipDetailsButton/ChangeInternshipDetailsButton";
import UploadCompanyEvaluationFormButton from "../UploadCompanyEvaluationFormButton/UploadCompanyEvaluationFormButton";
import SeeSubmissionButton from "../SeeSubmissionButton/SeeSubmissionButton";
import AssignToAnInstructorButton from "../AssignToAnInstructorButton/AssignToAnInstructorButton";
import DeleteInternshipButton from "../DeleteInternshipButton/DeleteInternshipButton";
import { UserContext } from "../../contexts/UserContext";

export default function Internship({ internship, refreshInternships }) {
  const { user } = useContext(UserContext);

  const [studentId, setStudentId] = useState(internship.student.id);
  const [internshipType, setInternshipType] = useState(internship.type);
  const [internshipStatus, setInternshipStatus] = useState(internship.status);
  const [instructorId, setInstructorId] = useState(
    internship.instructor ? internship.instructor.id : -1
  );

  useEffect(() => {
    setStudentId(internship.student.id);
    setInternshipType(internship.type);
    setInternshipStatus(internship.status);
    setInstructorId(internship.instructor ? internship.instructor.id : -1);
  }, [internship]);

  return (
    <ListItem sx={{ border: 1 }}>
      <ListItemText>
        {internshipType} Student Id:{studentId} Instructor Id:
        {instructorId === -1 ? "Not assigned" : instructorId} Internship Status:{" "}
        {internshipStatus}
      </ListItemText>
      {user.role === "secretary" && (
        <>
          <ChangeInternshipDetailsButton />
          <UploadCompanyEvaluationFormButton />
          <AssignToAnInstructorButton
            internshipId={internship.id}
            instructorId={instructorId}
            setInstructorId={setInstructorId}
            refreshInternships={refreshInternships}
          />
        </>
      )}
      <SeeSubmissionButton
        internshipId={internship.id}
        internshipStatus={internship.status}
        internshipType={internship.type}
      />
      {user.role === "secretary" && (
        <DeleteInternshipButton
          internshipId={internship.id}
          refreshInternships={refreshInternships}
        />
      )}
    </ListItem>
  );
}
