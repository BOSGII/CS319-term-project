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
  const [refresh, setRefresh] = useState(false);

  const [versionUnderFocus, setVersionUnderFocus] = useState(0);
  const [sidebarOpen, setSidebarOpen] = useState(true);

  const refreshSubmission = () => {
    setRefresh(true);
  };

  const handleSidebarClose = () => {
    setSidebarOpen(false);
  };

  const handleSidebarOpen = () => {
    setSidebarOpen(true);
  };

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
          setRefresh(false);
          setIsPending(false);
        });
    };

    getSubmissionFromServer();
  }, [refresh, internship]);

  return (
    <>
      {error && <div>{error.message}</div>}
      {isPending && <div>loading...</div>}
      {submission && (
        <>
          <SubmissionSideBar
            numberOfVersions={submission.numOfVersions}
            versionUnderFocus={versionUnderFocus}
            changeVersionUnderFocus={changeVersionUnderFocus}
            handleSidebarClose={handleSidebarClose}
            sidebarOpen={sidebarOpen}
          />
          <Version
            submission={submission}
            versionUnderFocus={versionUnderFocus}
            setAddNewVersionButtonPressed={setAddNewVersionButtonPressed}
            handleSidebarOpen={handleSidebarOpen}
            refreshSubmission={refreshSubmission}
          />
        </>
      )}
    </>
  );
}
