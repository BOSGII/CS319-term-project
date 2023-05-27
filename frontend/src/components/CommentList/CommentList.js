import { Container, List, Grid, Typography } from "@mui/material";
import Comment from "../Comment/Comment";

export default function CommentList({ versionStatus, comments }) {
  return (
    <Container sx={{ mt: 7 }}>
      <Typography variant="h5">Comments</Typography>
      <List>
        {versionStatus === "OLD_VERSION" ? (
          <Grid container spacing={6} sx={{ mb: 3 }}>
            <Grid item xs={6}>
              <Typography variant="h6">Instructor Message</Typography>
            </Grid>
            <Grid item xs={6}>
              <Typography variant="h6">Student Reply</Typography>
            </Grid>
          </Grid>
        ) : (
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <Typography variant="h6">Instructor Message</Typography>
            </Grid>
          </Grid>
        )}
        {comments?.map((comment) => (
          <Comment key={comment.id} comment={comment} />
        ))}
      </List>
    </Container>
  );
}
