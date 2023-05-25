<<<<<<< HEAD
import { ListItem, ListItemText, Container, Typography, Stack } from "@mui/material";
=======
import { ListItem, ListItemText, Card, Stack } from "@mui/material";
>>>>>>> 072d63e (Styling improvements in Instructors and Internships)
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
<<<<<<< HEAD
  
    <ListItem sx={{ border: 1 }}>
      <ListItemText sx={{ whiteSpace: 'pre-line'}}>
       Internship: {`${internshipType}\n Student Id: ${studentId} \nInstructor Id: ${instructorId === -1 ? "N/A" : instructorId} \nInternship Status:${" "} ${internshipStatus}\n`}
      </ListItemText>
      <Stack spacing={4} direction='row' marginLeft={15} marginRight={5}>
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
      </Stack>
    </ListItem>
=======
    <Card elevation={10} style={{ borderRadius: 15 }}>
      <ListItem >
        <Stack direction={"row"} alignItems={"center"}> 
          <Stack direction={"column"} spacing={1} alignItems={"left"} marginRight={40}>
            <ListItemText>{internshipType} </ListItemText>
            <ListItemText>Student Id:{studentId} </ListItemText>
            <ListItemText>Instructor Id: {instructorId === -1 ? "Not assigned" : instructorId}</ListItemText>
            <ListItemText>Status:{" "} {internshipStatus}</ListItemText>
          </Stack>
          <Stack direction={"row"} spacing={5}>
            {user.role === "secretary" && (
              <Stack direction={"row"} spacing={5}>
                <ChangeInternshipDetailsButton />
                <UploadCompanyEvaluationFormButton />
                <AssignToAnInstructorButton
                  internshipId={internship.id}
                  instructorId={instructorId}
                  setInstructorId={setInstructorId}
                  refreshInternships={refreshInternships}
                />
              </Stack >
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
            </Stack>
          </Stack>
      </ListItem>
    </Card>
>>>>>>> 072d63e (Styling improvements in Instructors and Internships)
  );
}
