import { Button, Typography } from "@mui/material";
import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { UserContext } from "../../contexts/UserContext";
import CommentSection from "../CommentSection/CommentSection";
import RequestRevisionButton from "../RequestRevisionButton/RequestRevisionButton";
import FinalizeButton from "../FinalizeButton/FinalizeButton";
import DownloadReport from "../DownloadReport/DownloadReport";
import DownloadFeedback from "../DownloadFeedback/DownloadFeedback";

export default function Version({
  submission,
  versionUnderFocus,
  setAddNewVersionButtonPressed,
}) {
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
        `/api/versions?submissionId=${submission.id}&versionNumber=${versionUnderFocus}`
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
  }, [refresh, submission, versionUnderFocus]);

  return (
    <>
      {error && <div>{error.message}</div>}
      {isPending && <div>Loading...</div>}
      {version && (
        <>
          <Typography>
            Version id in database: {version.id} totalNumOfVersions ={" "}
            {submission.numOfVersions}
          </Typography>
          <DownloadReport versionId={version.id} />
          {(version.status === "OLD_VERSION" ||
            version.status === "REVISION_REQUIRED") &&
            version.isFeedbackFileProvided && (
              <DownloadFeedback versionId={version.id} />
            )}
          {(version.status === "REVISION_REQUIRED" ||
            version.status === "OLD_VERSION") &&
            version.areCommentsProvided && (
              <CommentSection versionId={version.id} />
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
          {version.status === "NOT_EVALUATED" && user.role === "instructor" && (
            <>
              <RequestRevisionButton
                versionId={version.id}
                refreshVersion={refreshVersion}
              />
              <FinalizeButton />
            </>
          )}
        </>
      )}
    </>
  );
}
