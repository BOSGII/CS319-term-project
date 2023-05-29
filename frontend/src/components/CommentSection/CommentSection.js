import { useEffect, useState } from "react";
import axios from "axios";
import CommentList from "../../components/CommentList/CommentList";

export default function CommentSection({ versionStatus, versionId }) {
  const sessionId = localStorage.getItem("sessionId");

  const [comments, setComments] = useState([]);
  const [isPending, setIsPending] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    setIsPending(true);
    axios
      .get(`http://localhost:8080/api/comments?versionId=${versionId}`, {
        headers: {
          Authorization: `${sessionId}`,
        },
      })
      .then((response) => {
        setComments(response.data);
        setIsPending(false);
      })
      .catch((error) => {
        setError(error);
      });
  }, [versionId]);

  return (
    <>
      {error && <div>{error.message}</div>}
      {isPending && <div>Loading...</div>}
      {comments && (
        <CommentList versionStatus={versionStatus} comments={comments} />
      )}
    </>
  );
}
