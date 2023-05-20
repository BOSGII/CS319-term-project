import { Container, Typography } from "@mui/material";
import { useContext, useState } from "react";
import { UserContext } from "../../contexts/UserContext";
import { useParams } from "react-router-dom";
import { useFetch } from "../../hooks/useFetch";
import Version from "../../components/Version/Version";
import SubmissionSidebar from "../../components/SubmissionSidebar/SubmissionSidebar";

export default function SubmissionPage() {
  const { user } = useContext(UserContext);
  const { internshipId } = useParams();
  const { internshipType } = useParams();
  let fetchUrl;

  switch (user.role) {
    case "student":
      const studentId = user.id;
      fetchUrl = `/api/submissions?studentId=${studentId}&internshipType=${internshipType}`;
      break;

    case "instructor":
      fetchUrl = `/api/submissions?internshipId=${internshipId}`;
      break;

    default:
  }

  const { data, isPending, error } = useFetch(fetchUrl);

  const [versionUnderFocus, setVersionUnderFocus] = useState(0);

  return (
    <Container>
      <Typography>Submission Page</Typography>
      {error && <div>{error}</div>}
      {isPending && <div>loading...</div>}
      {data && (
        <>
          <SubmissionSidebar
            numberOfVersions={data.numOfVersions}
            versionUnderFocus={versionUnderFocus}
            setVersionUnderFocus={setVersionUnderFocus}
          />
          <Version
            submissionId={data.id}
            versionUnderFocus={versionUnderFocus}
          />
        </>
      )}
    </Container>
  );
}
