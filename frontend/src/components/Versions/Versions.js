import { Container, Typography } from "@mui/material";
import axios from "axios";
import { useCallback, useEffect, useState } from "react";
import SubmissionSideBar from "../SubmissionSidebar/SubmissionSidebar";
import Version from "../Version/Version";

export default function Versions({
  internship,
  setAddNewVersionButtonPressed,
}) {
  const [submission, setSubmission] = useState(null);
  const [isPending, setIsPending] = useState(false);
  const [error, setError] = useState(null);

  const [versionUnderFocus, setVersionUnderFocus] = useState(0);

  const changeVersionUnderFocus = useCallback((versionToBeFocused) => {
    setVersionUnderFocus(versionToBeFocused);
  }, []);

  useEffect(() => {
    const getSubmissionFromServer = () => {
      setIsPending(true);

      axios
        .get(`/api/submissions?internshipId=${internship.id}`)
        .then((response) => {
          setSubmission(response.data);
          setVersionUnderFocus(response.data.numOfVersions);
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

  return (
    <Container>
      <Typography>Versions component</Typography>
      {error && <div>{error.message}</div>}
      {isPending && <div>loading...</div>}
      {submission && (
        <>
          <SubmissionSideBar
            numberOfVersions={submission.numOfVersions}
            versionUnderFocus={versionUnderFocus}
            changeVersionUnderFocus={changeVersionUnderFocus}
          />
          <Version
            submission={submission}
            versionUnderFocus={versionUnderFocus}
            setAddNewVersionButtonPressed={setAddNewVersionButtonPressed}
          />
        </>
      )}
    </Container>
  );
}
