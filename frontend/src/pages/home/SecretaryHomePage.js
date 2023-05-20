import { Button, Container, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";

export default function SecretaryHomePage() {
  const navigate = useNavigate();
  return (
    <Container>
      <Typography>Secretary Home Page</Typography>
      <Button
        onClick={() => {
          navigate(`/instructors`);
        }}
      >
        Instructors
      </Button>
      <Button
        onClick={() => {
          navigate(`/internships`);
        }}
      >
        Internships
      </Button>
    </Container>
  );
}
