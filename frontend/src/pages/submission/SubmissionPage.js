import { Container, Typography } from "@mui/material";
import { useContext, useEffect, useState } from "react";
import { UserContext } from "../../contexts/UserContext";
import axios from "axios";
import UploadReport from "../../components/UploadReport/UploadReport";
import Versions from "../../components/Versions/Versions";
import { InternshipIDContext } from "../../contexts/InternshipIDContext";

export default function SubmissionPage() {
  const { user } = useContext(UserContext);
  const { internshipId } = useContext(InternshipIDContext);

  const [internship, setInternship] = useState(null);
  const [isPending, setIsPending] = useState(false);
  const [error, setError] = useState(null);
  const [refresh, setRefresh] = useState(false);

  const [addNewVersionButtonPressed, setAddNewVersionButtonPressed] =
    useState(false);

  const refreshInternship = () => {
    setRefresh(true);
    setAddNewVersionButtonPressed(false);
  };

  useEffect(() => {
    const getInternshipFromServer = () => {
      setIsPending(true);

      axios
        .get(`/api/internships/${internshipId}`)
        .then((response) => {
          setInternship(response.data);
        })
        .catch((error) => {
          console.log("internship fetch problem");
          setError(error);
        })
        .finally(() => {
          setIsPending(false);
          setRefresh(false);
        });
    };

    getInternshipFromServer();
  }, [user, internshipId, refresh, addNewVersionButtonPressed]);

  return (
    <Container>
      <Typography>Submission Page</Typography>
      {error && <div>{error.message}</div>}
      {isPending && <div>loading...</div>}
      {internship && (
        <>
          {internship.status === "NOT_SUBMITTED" ||
          internship.numOfVersions === 0 ||
          addNewVersionButtonPressed ? (
            <UploadReport // component that is rendered only on student side
              internship={internship}
              refreshInternship={refreshInternship}
            />
          ) : (
            <Versions
              internship={internship}
              setAddNewVersionButtonPressed={setAddNewVersionButtonPressed}
            />
          )}
        </>
      )}
    </Container>
  );
}
