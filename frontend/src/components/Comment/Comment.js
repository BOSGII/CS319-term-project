import { Grid, Typography } from "@mui/material";

export default function Comment({ comment }) {
  if (comment.reply) {
    return (
      <Grid container spacing={8} sx={{ mb: 2 }}>
        <Grid item xs={6}>
          <Typography>{comment.message}</Typography>
        </Grid>
        <Grid item xs={6}>
          <Typography>{comment.reply}</Typography>
        </Grid>
      </Grid>
    );
  } else {
    return (
      <Grid container spacing={8}>
        <Grid item xs={3}></Grid>
        <Grid item xs={6}>
          <Typography>{comment.message}</Typography>
        </Grid>
        <Grid item xs={3}></Grid>
      </Grid>
    );
  }
}
