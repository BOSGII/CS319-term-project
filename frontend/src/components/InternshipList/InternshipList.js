import { List, Container, Typography } from "@mui/material";
import Internship from "../Internship/Internship";

export default function InternshipList({ internships, refreshInternships }) {
  return (
    <Container>
      <Typography>InternshipList Component</Typography>
      <List>
        {internships.map((internship) => (
          <Internship
            key={internship.id}
            internship={internship}
            refreshInternships={refreshInternships}
          />
        ))}
      </List>
    </Container>
  );
}
