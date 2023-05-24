import { useEffect, useState } from "react";
import axios from "axios";
import CommentList from "../../components/CommentList/CommentList";

export default function CommentSection({ versionId }) {
  const [comments, setComments] = useState([]);
  const [isPending, setIsPending] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    setIsPending(true);
    axios
      .get(`/api/comments?versionId=${versionId}`)
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
      {comments && <CommentList comments={comments} />}
    </>
  );
}
