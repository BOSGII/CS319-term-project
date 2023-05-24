import { Grid } from "@mui/material";

export default function Comment({ comment }) {
  console.log(comment);
  if (comment.reply) {
    return (
      <Grid container spacing={8}>
        <Grid item xs={6}>
          {comment.message}
        </Grid>
        <Grid item xs={6}>
          {comment.reply}
        </Grid>
      </Grid>
    );
  } else {
    return (
      <Grid container spacing={8}>
        <Grid item xs={3}></Grid>
        <Grid item xs={6}>
          {comment.message}
        </Grid>
        <Grid item xs={3}></Grid>
      </Grid>
    );
  }
}
