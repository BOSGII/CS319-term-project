
import { IconButton, Card, ListItem, ListItemText, Tooltip, Stack } from "@mui/material";

import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import ChangeInstructorDetailsButton from "../ChangeInstructorDetailsButton/ChangeInstructorDetailsButton";
import DeleteInstructorButton from "../DeleteInstructorButton/DeleteInstructorButton";

import VisibilityIcon from "@mui/icons-material/Visibility";


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

    <Card
      elevation={10}
      style={{ borderRadius: 15 }}
      sx={{
        display: "flex",
        flexDirection: "column",
        justifyContent: "space-between",
        height: "100%",
      }}
    >
      <ListItem style={{ justifyContent: "space-between" }}>
        <Stack direction={"column"}>
          <ListItemText> Full Name: {fullName} </ListItemText>
          <ListItemText> Department: {department}</ListItemText>
          <ListItemText>
            {" "}
            Number of Assigned Internships: {numOfAssignedInternships}/
            {maxNumOfInternships}
          </ListItemText>
        </Stack>
        <Stack direction={"row"} spacing={5}>
          <ChangeInstructorDetailsButton
            instructor={instructor}
            refreshInstructors={refreshInstructors}
          />
          <Tooltip title="See Assigned Internships">
          <IconButton
            onClick={() => {
              navigate(`/instructors/${instructor.id}`);
            }}
          >
            <VisibilityIcon></VisibilityIcon>
          </IconButton>
          </Tooltip>
          <DeleteInstructorButton
            instructorId={instructor.id}
            refreshInstructors={refreshInstructors}
          />
        </Stack>
      </ListItem>
    </Card>
  );
}
