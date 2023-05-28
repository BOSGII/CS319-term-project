import { Container, Typography } from "@mui/material";
import InstructorList from "../../components/InstructorList/InstructorList";
import axios from "axios";
import AddInstructorButton from "../../components/AddInstructorButton/AddInstructorButton";

import { useEffect, useState } from "react";

export default function InstructorsPage() {
  const [instructors, setInstructors] = useState([]);
  const [isPending, setIsPending] = useState(false);
  const [error, setError] = useState(null);
  const [refresh, setRefresh] = useState(false);

  const refreshInstructors = () => {
    setRefresh(true);
  };


  useEffect(() => {
    const getInstructorsFromServer = () => {
      setIsPending(true);
      const sessionId = localStorage.getItem('sessionId');

      axios
        .get("http://localhost:8080/api/instructors", {
          headers: {
            Authorization: `${sessionId}`
          }
        })
        .then((response) => {
          setInstructors(response.data);
        })
        .catch((error) => {
          setError(error);
        })
        .finally(() => {
          setIsPending(false);
          setRefresh(false);
        });
    };
    getInstructorsFromServer();
  }, [refresh]);
  return (
    <Container sx={{mt: 10}}>
      <Typography>Secretary page showing all instructors</Typography>
      <AddInstructorButton refreshInstructors={refreshInstructors} />
      {error && <div>{error.message} </div>}
      {isPending && <div>loading...</div>}
      {instructors && (
        <InstructorList
          instructors={instructors}
          refreshInstructors={refreshInstructors}
        />
      )}
    </Container>
  );
}
