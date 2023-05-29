import { Button, Container, Grid, Typography } from "@mui/material";
import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { UserContext } from "../../contexts/UserContext";
import CommentSection from "../CommentSection/CommentSection";
import RequestRevisionButton from "../RequestRevisionButton/RequestRevisionButton";
import FinalizeButton from "../FinalizeButton/FinalizeButton";
import DownloadFile from "../DownloadFile/DownloadFile";

export default function Version({
  submission,
  versionUnderFocus,
  setAddNewVersionButtonPressed,
  handleSidebarOpen,
  refreshSubmission,
}) {
  const sessionId = localStorage.getItem("sessionId");
  const { user } = useContext(UserContext);
  const [version, setVersion] = useState(null);
  const [isPending, setIsPending] = useState(false);
  const [error, setError] = useState(null);
  const [refresh, setRefresh] = useState(false);

  const refreshVersion = () => {
    setRefresh(true);
  };

  useEffect(() => {
    setIsPending(true);
    axios
      .get(
        `http://localhost:8080/api/versions?submissionId=${submission.id}&versionNumber=${versionUnderFocus}`,
        {
          headers: {
            Authorization: `${sessionId}`,
          },
        }
      )
      .then((response) => {
        setVersion(response.data);
      })
      .catch((error) => {
        console.log(error);
        setError(error);
      })
      .finally(() => {
        setRefresh(false);
        setIsPending(false);
      });
  }, [sessionId, refresh, submission, versionUnderFocus]);

  return (
    <>
      {error && <div>{error.message}</div>}
      {isPending && <div>Loading...</div>}
      {version && (
        <Container sx={{ mt: 5 }}>
          <button
            onClick={handleSidebarOpen}
            style={{ position: "absolute", left: "1rem", top: "5rem" }}
          >
            See All Versions
          </button>
          <Grid container spacing={2} sx={{ mt: 10 }}>
            <Grid item xs={6}>
              <Typography variant="h6" sx={{ mb: 1 }}>
                Download Report:
              </Typography>
              <DownloadFile
                fileName={version.reportFileName}
                url={`http://localhost:8080/api/versions/${version.id}/report`}
              />
            </Grid>
            <Grid item xs={6}>
              <Typography variant="h6" sx={{ mb: 1 }}>
                Download Feedback:
              </Typography>
              {(version.status === "OLD_VERSION" ||
                version.status === "REVISION_REQUIRED") &&
              version.isFeedbackFileProvided ? (
                <DownloadFile
                  fileName={version.feedbackFileName}
                  url={`http://localhost:8080/api/versions/${version.id}/feedback`}
                />
              ) : (
                <Typography>Feedback file is not provided.</Typography>
              )}
            </Grid>
          </Grid>
          {(version.status === "REVISION_REQUIRED" ||
            version.status === "OLD_VERSION") &&
            version.areCommentsProvided && (
              <CommentSection
                versionStatus={version.status}
                versionId={version.id}
              />
            )}
          {version.status === "REVISION_REQUIRED" &&
            user.role === "student" && (
              <Button
                onClick={() => {
                  setAddNewVersionButtonPressed(true);
                }}
              >
                add new version
              </Button>
            )}
          {version.status === "NOT_EVALUATED" && user.role === "student" && (
            <Typography variant="h6" sx={{ mt: 10 }}>
              Instructor is evaluating the report...
            </Typography>
          )}
          {version.status === "NOT_EVALUATED" && user.role === "instructor" && (
            <>
              <RequestRevisionButton
                versionId={version.id}
                refreshVersion={refreshVersion}
              />
              <FinalizeButton
                submissionId={submission.id}
                refreshSubmission={refreshSubmission}
              />
            </>
          )}
          {submission.status === "CLOSED" &&
            version.versionNumber === submission.numOfVersions && (
              <DownloadFile
                fileName={submission.finalReportName}
                url={`http://localhost:8080/api/submissions/${submission.id}/finalReport`}
              />
            )}
        </Container>
      )}
    </>
  );
}
