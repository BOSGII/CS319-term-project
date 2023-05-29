import {
  Button,
  IconButton,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  DialogContentText,
  InputLabel,
  Select,
  MenuItem,
  Typography,
  Tooltip,
} from "@mui/material";

import ChangeCircleIcon from "@mui/icons-material/ChangeCircle";


import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

export default function AssignToAnInstructorButton({
  internshipId,
  instructorId,
  setInstructorId,
  refreshInternships,
}) {
  const sessionId = localStorage.getItem("sessionId");

  const location = useLocation();
  const [open, setOpen] = useState(false);
  const [availableInstructors, setAvailableInstructors] = useState([]);
  const [error, setError] = useState(null);
  const [selectedInstructorId, setSelectedInstructorId] = useState("");
  const [currentInstructorId, setCurrentInstructorId] = useState("");

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/instructors?available=true", {
        headers: {
          Authorization: `${sessionId}`,
        },
      })
      .then((response) => {
        setAvailableInstructors(response.data);
      })
      .catch((error) => {
        setError(error);
      });

    axios
      .get(`http://localhost:8080/api/internships/${internshipId}`, {
        headers: {
          Authorization: `${sessionId}`,
        },
      })
      .then((response) => {
        setCurrentInstructorId(
          response.data.instructor ? response.data.instructor.id : ""
        );
        setSelectedInstructorId(
          response.data.instructor ? response.data.instructor.id : ""
        );
      })
      .catch((error) => {
        setError(error);
      });
  }, [sessionId, internshipId, instructorId]);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const assign = () => {
    axios
      .post(
        `http://localhost:8080/api/internships/${internshipId}`,
        {
          newInstructorId: selectedInstructorId,
        },
        {
          headers: {
            Authorization: `${sessionId}`,
          },
        }
      )
      .then((response) => {
        if (location.pathname === "/internships") {
          setInstructorId(selectedInstructorId);
        } else {
          refreshInternships();
        }
        handleClose();
      })
      .catch((error) => {
        console.log(error.data.body);
        console.log("assign put error");
      });
  };

  return (
    <div>
      <Tooltip title="Assign to an Instructor">
        <IconButton onClick={handleClickOpen}>
          <ChangeCircleIcon></ChangeCircleIcon>
        </IconButton>
      </Tooltip>
      {error && <div>{error.message}</div>}
      {availableInstructors && (
        <Dialog
          open={open}
          onClose={handleClose}
          aria-labelledby="alert-dialog-title"
          aria-describedby="alert-dialog-description"
        >
          <DialogTitle id="alert-dialog-title">
            {"Assign Internship to an Instructor"}
          </DialogTitle>
          <DialogContent>
            <DialogContentText id="alert-dialog-description">
              You may reassign internship to a different instructor
            </DialogContentText>
          </DialogContent>
          <DialogActions>
            <Typography>
              Current Instructor:{" "}
              {currentInstructorId === -1
                ? "Not assigned"
                : currentInstructorId}
            </Typography>
            <InputLabel id="demo-simple-select-helper-label">
              New Instructor:{" "}
            </InputLabel>
            <Select
              labelId="demo-simple-select-helper-label"
              id="demo-simple-select-helper"
              value={selectedInstructorId}
              label="Age"
              onChange={(event) => {
                setSelectedInstructorId(event.target.value);
              }}
            >
              <MenuItem value="">
                <em>None</em>
              </MenuItem>
              {availableInstructors?.map((instructor) => (
                <MenuItem key={instructor.id} value={instructor.id}>
                  {instructor.id}
                </MenuItem>
              ))}
            </Select>
            <Button onClick={assign} autoFocus>
              Assign
            </Button>
          </DialogActions>
        </Dialog>
      )}
    </div>
  );
}
