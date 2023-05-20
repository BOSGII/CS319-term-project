import { Button, ListItem, ListItemText } from "@mui/material";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import ChangeInstructorDetailsButton from "../ChangeInstructorDetailsButton/ChangeInstructorDetailsButton";
import DeleteInstructorButton from "../DeleteInstructorButton/DeleteInstructorButton";

export default function Instructor({ instructor, refreshInstructors }) {
  const navigate = useNavigate();

  const [fullName, setFullName] = useState(instructor.fullName);
  const [department, setDepartment] = useState(instructor.department);
  const [numOfAssignedInternships, setNumOfAssignedInternships] = useState(
    instructor.numOfAssignedInternships
  );
  const [maxNumOfInternships, setMaxNumOfInternships] = useState(
    instructor.maxNumOfInternships
  );

  useEffect(() => {
    setFullName(instructor.fullName);
    setDepartment(instructor.department);
    setNumOfAssignedInternships(instructor.numOfAssignedInternships);
    setMaxNumOfInternships(instructor.maxNumOfInternships);
  }, [instructor]);

  return (
    <ListItem sx={{ border: 1 }}>
      <ListItemText>
        {fullName}---{department}---
        {numOfAssignedInternships}/{maxNumOfInternships}
      </ListItemText>
      <ChangeInstructorDetailsButton />
      <Button
        onClick={() => {
          navigate(`/instructors/${instructor.id}`);
        }}
      >
        See Assigned Internships
      </Button>
      <DeleteInstructorButton
        instructorId={instructor.id}
        refreshInstructors={refreshInstructors}
      />
    </ListItem>
  );
}
