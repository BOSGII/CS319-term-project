import { Container, List, Typography } from "@mui/material";
import Comment from "../Comment/Comment";

export default function CommentList({ comments }) {
  return (
    <Container>
      <Typography>CommentList Component</Typography>
      <List>
        {comments?.map((comment) => (
          <Comment key={comment.id} comment={comment} />
        ))}
      </List>
    </Container>
  );
}
