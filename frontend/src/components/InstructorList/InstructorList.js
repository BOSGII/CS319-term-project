import { List, Container, Typography } from "@mui/material";
import Instructor from "../Instructor/Instructor";

export default function InstructorList({ instructors, refreshInstructors }) {
  return (
    <Container>
      <Typography>InstructorList Component</Typography>
      <List>
        {instructors?.map((instructor) => (
          <Instructor
            key={instructor.id}
            instructor={instructor}
            refreshInstructors={refreshInstructors}
          />
        ))}
      </List>
    </Container>
  );
}
