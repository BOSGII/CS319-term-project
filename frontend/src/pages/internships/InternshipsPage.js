import { Container, Stack, Typography } from "@mui/material";
import { useContext, useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { UserContext } from "../../contexts/UserContext";
import InternshipList from "../../components/InternshipList/InternshipList";
import AddInternshipButton from "../../components/AddInternshipButton/AddInternshipButton";
import MatchInternshipsButton from "../../components/MatchInternshipsButton/MatchInternshipsButton";
import axios from "axios";
import ImportInternshipsButton from "../../components/ImportInternhipsButton/ImportInternshipsButton";

export default function InternshipsPage() {
  const sessionId = localStorage.getItem("sessionId");
  const { user } = useContext(UserContext);
  const location = useLocation();

  const [internships, setInternships] = useState([]);
  const [isPending, setIsPending] = useState(false);
  const [error, setError] = useState(null);
  const [refresh, setRefresh] = useState(false);

  const refreshInternships = () => {
    setRefresh(true);
  };

  useEffect(() => {
    const getInternshipsFromServer = () => {
      let fetchUrl;

      switch (user.role) {
        case "student":
          fetchUrl = `http://localhost:8080/api/internships?studentId=${user.id}`;

          break;
        case "instructor":
          fetchUrl = `http://localhost:8080/api/internships?instructorId=${user.id}`;
          break;

        case "secretary":
          if (location.pathname === "/internships") {
            fetchUrl = `http://localhost:8080/api/internships`;
          } else {
            // /instructors/{instructorId}
            const instructorId = location.pathname.split("/").at(-1);
            fetchUrl = `http://localhost:8080/api/internships?instructorId=${instructorId}`;
          }
          break;
        default:
          fetchUrl = `http://localhost:8080/api/internships?studentId=${user.id}`;

          break;
      }

      setIsPending(true);

      axios
        .get(fetchUrl, {
          headers: {
            Authorization: `${sessionId}`,
          },
        })
        .then((response) => {
          setInternships(response.data);
          console.log(response);
        })
        .catch((error) => {
          setError(error);
          console.log(error);
        })
        .finally(() => {
          setIsPending(false);
          setRefresh(false);
        });
    };

    getInternshipsFromServer();
  }, [user, location, refresh]);

  return (
    <Container sx={{ mt: 10 }}>
      <Stack alignItems="center" spacing={5}>
        <Typography>internships page</Typography>
        {user.role === "secretary" && location.pathname === "/internships" && (
          <Stack direction="row" spacing={2}>
            <ImportInternshipsButton refreshInternships={refreshInternships} />
            <AddInternshipButton refreshInternships={refreshInternships} />
            <MatchInternshipsButton refreshInternships={refreshInternships} />
          </Stack>
        )}
        {error && <div>{error.message}</div>}
        {isPending && <div>loading...</div>}
        {internships && (
          <InternshipList
            internships={internships}
            refreshInternships={refreshInternships} // used for deleting an internship
          />
        )}
      </Stack>
    </Container>
  );
}
