import { useEffect, useState } from "react";
import axios from "axios";
import { Grid, List } from "@mui/material";

export default function ReplyCommentsSection({
  versionId,
  replies,
  setReplies,
}) {
  const [comments, setComments] = useState([]);
  const [isPending, setIsPending] = useState(false);
  const [error, setError] = useState(null);

  const handleInputChange = (newInput, index) => {
    setReplies((prevReplies) => {
      const updatedReplies = [...prevReplies];
      updatedReplies[index] = newInput;
      return updatedReplies;
    });
  };

  useEffect(() => {
    setIsPending(true);
    const sessionId = localStorage.getItem('sessionId');
    axios
      .get(`http://localhost:8080/api/comments?versionId=${versionId}`, {
        headers: {
          Authorization: `${sessionId}`
        }
      })
      .then((response) => {
        setComments(response.data);
        setReplies(response.data.map((comment) => ""));
        setIsPending(false);
      })
      .catch((error) => {
        setError(error);
      });
  }, [versionId, setReplies]);
  return (
    <>
      {error && <div>{error.message}</div>}
      {isPending && <div>Loading...</div>}
      {comments && (
        <List>
          {comments?.map((comment, index) => (
            <Grid key={index} container>
              <Grid item xs={6}>
                {comment.message}
              </Grid>

              <Grid item xs={6}>
                <input
                  type="text"
                  value={replies[index]}
                  onChange={(el) => handleInputChange(el.target.value, index)}
                />
              </Grid>
            </Grid>
          ))}
        </List>
      )}
    </>
  );
}
