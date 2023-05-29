import { useEffect, useState } from "react";
import axios from "axios";
import { Grid, List } from "@mui/material";

export default function ReplyCommentsSection({
  versionId,
  replies,
  setReplies,
}) {
  const sessionId = localStorage.getItem("sessionId");

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
    axios
      .get(`http://localhost:8080/api/comments?versionId=${versionId}`, {
        headers: {
          Authorization: `${sessionId}`,
        },
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
        <List style={{ fontSize: "18px" }}>
          {comments?.map((comment, index) => (
            <Grid key={index} container>
              <Grid item xs={6}>
                {comment.message}
              </Grid>

              <Grid item xs={6}>
                <textarea
                  rows={8} // Adjust the number of rows to increase the size
                  value={replies[index]}
                  onChange={(el) => handleInputChange(el.target.value, index)}
                  style={{
                    width: '100%',
                    height: '150px', // Adjust the height as desired
                    resize: 'none', // Prevent resizing of the textarea
                  }}
                />
              </Grid>
            </Grid>
          ))}
        </List>
      )}
    </>
  );
}
