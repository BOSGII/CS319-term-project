import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import { useContext, useState } from 'react';
import SecretarySideBar from '../SecretarySidebar/SecretarySidebar';
import { UserContext } from "../../contexts/UserContext";
import { useNavigate } from 'react-router-dom';

export default function CustomNavbar () {
  const [open, setOpen] = useState(false);
  const {user, setUser} = useContext(UserContext)
  const navigate = useNavigate();

  const handleSidebarOpen = () => {
    setOpen(true);
  }

  const handleSidebarClose = () => {
    setOpen(false);
  }

    return (
      <>
        <AppBar position="fixed">
          <Toolbar>
            <IconButton
              size="large"
              edge="start"
              color="inherit"
              aria-label="menu"
              sx={{ visibility: user.role === "secretary" ? "visible" : "hidden", mr: 2 }}
              onClick={handleSidebarOpen}
            >
              <MenuIcon />
            </IconButton>
            <Typography variant="h6" component="div" sx={{ cursor: "pointer", flexGrow: 1 }} onClick={() => {
              navigate('/home');
            }}>
              Bilkent Internship Management System
            </Typography>
            <Button sx={{visibility: user.role ? "visible" : "hidden"}} color="inherit" onClick={() => {
              setUser({id: null, role:null});
              navigate('/login');
            }}>Logout</Button>
          </Toolbar>
        </AppBar>
        <SecretarySideBar open={open} handleSidebarClose={handleSidebarClose}/>
      </>
      
      )
}
