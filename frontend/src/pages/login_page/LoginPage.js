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
  const [role, setRole] = useState("");
  const { setUser } = useContext(UserContext);
  
/*
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
*/

const handleSubmit = (event) => {
  event.preventDefault();

  // Make a request to the authentication endpoint of your backend
  fetch('http://localhost:8080/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      id,
      password
    }),
  })
  .then(response => {
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    return response.json();
  })
  .then(data => {
    // Check if authentication was successful
    if(data.sessionId) {
      // Store the sessionId and roleName in context or somewhere else 
      // where it can be used in subsequent requests
      console.log(data.sessionId);
    
      setUser({ id:id, role: data.roleName, sessionId: data.sessionId });
      
      navigate("/home");
      localStorage.setItem('sessionId', data.sessionId);
    } else {
      // Handle failed authentication
      console.error('Authentication failed');
    }
  })
  .catch((error) => {
    // Handle network error
    console.error('Error:', error);
  });
};
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
        // eslint-disable-next-line react/jsx-no-comment-textnodes
        />

        <Box sx={{ display: "flex", justifyContent: "flex-end" }}>
          <Button variant="contained" type="submit">
            Login
          </Button>
        </Box>
      </form>
    </Box>
  );
}
