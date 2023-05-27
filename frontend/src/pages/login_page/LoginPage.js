import {
  TextField,
  Button,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  Box,
  Container, 
  Typography
} from "@mui/material";

import { useNavigate } from "react-router-dom";
import { UserContext } from "../../contexts/UserContext";
import { useContext, useState } from "react";

export default function LoginPage() {
  const navigate = useNavigate();

  // Initialize state variables for ID, password, and role
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("student");

  // Handle form submission
  const handleSubmit = (event) => {
    event.preventDefault();

    // TODO: Validate ID and password
    // TODO: Authenticate user with server
    // TODO: Store user ID and role in context

    // For now, just log the user in and set their ID and role in context
    console.log(`Logged in as ${id} with role ${role}`);
    setUser({ id, role });
    navigate("/home");
  };

  // Get the setUser function from the context
  const { setUser } = useContext(UserContext);

  return (

    <Box
      sx={{
        maxWidth: 400,
        mx: "auto",
        mt: 10,
        p: 2,
        bgcolor: "background.paper",
        borderRadius: 2,
      }}
    >
      <Container>
        <Typography variant="h4"> Welcome to BIM </Typography>
        <Typography variant="h5"> Login </Typography>
      </Container>
      <form onSubmit={handleSubmit}>
        <TextField
          label="ID"
          variant="outlined"
          fullWidth
          margin="normal"
          value={id}
          onChange={(e) => setId(e.target.value)}
        />
        <TextField
          label="Password"
          variant="outlined"
          fullWidth
          margin="normal"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <FormControl fullWidth margin="normal">
          <InputLabel>Role</InputLabel>
          <Select value={role} onChange={(e) => setRole(e.target.value)}>
            <MenuItem value="student">Student</MenuItem>
            <MenuItem value="admin">Admin</MenuItem>
            <MenuItem value="instructor">Instructor</MenuItem>
            <MenuItem value="secretary">Secretary</MenuItem>
          </Select>
        </FormControl>
        <Box sx={{ display: "flex", justifyContent: "flex-end" }}>
          <Button variant="contained" type="submit">
            Login
          </Button>
        </Box>
      </form>
    </Box>
  );
}
