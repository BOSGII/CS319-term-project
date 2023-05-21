import { Container, Typography } from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";
import SubmissionSideBar from "../SubmissionSidebar/SubmissionSidebar";
import Version from "../Version/Version";

export default function Versions({
  internship,
  setAddNewVersionButtonPressed,
}) {
  const [submission, setSubmission] = useState(null);
  const [isPending, setIsPending] = useState(false);
  const [error, setError] = useState(null);
  const [refresh, setRefresh] = useState(false);

  const refreshSubmission = () => {
    setRefresh(true);
  };

  useEffect(() => {
    const getSubmissionFromServer = () => {
      setIsPending(true);

      axios
        .get(`/api/submissions?internshipId=${internship.id}`)
        .then((response) => {
          setSubmission(response.data);
        })
        .catch((error) => {
          setError(error);
        })
        .finally(() => {
          setIsPending(false);
        });
    };

    getSubmissionFromServer();
  }, [internship]);

  const [versionUnderFocus, setVersionUnderFocus] = useState(0);

  return (
    <Container>
      <Typography>Versions component</Typography>
      {error && <div>{error}</div>}
      {isPending && <div>loading...</div>}
      {submission && (
        <>
          <SubmissionSideBar
            numberOfVersions={submission.numOfVersions}
            versionUnderFocus={versionUnderFocus}
            setVersionUnderFocus={setVersionUnderFocus}
          />
          <Version
            submissionId={submission.id}
            versionUnderFocus={versionUnderFocus}
            setAddNewVersionButtonPressed={setAddNewVersionButtonPressed}
          />
        </>
      )}
    </Container>
  );
}
