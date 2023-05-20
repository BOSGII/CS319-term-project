import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  DialogContentText,
  InputLabel,
  Select,
  MenuItem,
  Typography,
} from "@mui/material";

import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

export default function AssignToAnInstructorButton({
  internshipId,
  instructorId,
  setInstructorId,
  refreshInternships,
}) {
  const location = useLocation();
  const [open, setOpen] = useState(false);
  const [availableInstructors, setAvailableInstructors] = useState([]);
  const [error, setError] = useState(null);
  const [selectedInstructorId, setSelectedInstructorId] = useState("");
  const [currentInstructorId, setCurrentInstructorId] = useState("");

  useEffect(() => {
    axios
      .get("/api/instructors?available=true")
      .then((response) => {
        setAvailableInstructors(response.data);
      })
      .catch((error) => {
        setError(error);
      });

    axios
      .get(`/api/internships/${internshipId}`)
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
  }, [internshipId, instructorId]);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const assign = () => {
    axios
      .post(`/api/internships/${internshipId}`, {
        newInstructorId: selectedInstructorId,
      })
      .then((response) => {
        if (location.pathname === "/internships") {
          setInstructorId(selectedInstructorId);
        } else {
          refreshInternships();
        }
        handleClose();
      })
      .catch(() => {
        console.log("assign put error");
      });
  };

  return (
    <div>
      <Button variant="outlined" onClick={handleClickOpen}>
        Assign/Reassign
      </Button>
      {error && <div>{error}</div>}
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