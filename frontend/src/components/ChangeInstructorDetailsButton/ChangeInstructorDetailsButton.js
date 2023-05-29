import { Button, IconButton, Modal, TextField, Tooltip } from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import { useState } from "react";
import axios from "axios";

export default function ChangeInstructorDetailsButton({
  instructor,
  refreshInstructors,
}) {
  const sessionId = localStorage.getItem("sessionId");

  const [open, setOpen] = useState(false);
  const [instructorFullName, setInstructorFullName] = useState(
    instructor.fullName
  );
  const [instructorMail, setInstructorMail] = useState(instructor.mail);
  const [instructorDepartment, setInstructorDepartment] = useState(
    instructor.department
  );
  const [instructorMaxNumOfInternships, setInstructorMaxNumOfInternships] =
    useState(instructor.maxNumOfInternships);

  const handleSubmit = (event) => {
    event.preventDefault();

    if (instructorMaxNumOfInternships < 0) {
      alert("Max Number of Assigned should be nonnegative");
      return;
    }

    if (instructorMaxNumOfInternships < instructor.numOfAssignedInternships) {
      alert(
        "You cannot set max number of assigned to smaller than current number of assigned"
      );
      return;
    }

    axios
      .put(
        `http://localhost:8080/api/instructors/${instructor.id}`,
        {
          fullName: instructorFullName,
          mail: instructorMail,
          department: instructorDepartment,
          maxNumOfInternships: instructorMaxNumOfInternships,
        },
        { headers: { Authorization: `${sessionId}` } }
      )
      .then((response) => {
        refreshInstructors();
        setOpen(false);
      })
      .catch((error) => {
        console.log("/api/instructors put error:", error.message);
      });
  };
  return (
    <>
      <Tooltip title="Change Instructor Details">
        <IconButton
          onClick={() => {
            setOpen(true);
          }}
        >
          <EditIcon></EditIcon>
        </IconButton>
      </Tooltip>
      <Modal
        sx={{
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          bgcolor: "rgba(0, 0, 0, 0.5)",
        }}
        open={open}
        onClose={() => {
          setOpen(false);
        }}
      >
        <form
          style={{
            backgroundColor: "white",
            padding: "2rem",
            borderRadius: "4px",
            maxWidth: "400px",
            width: "100%",
            textAlign: "center",
          }}
          onSubmit={handleSubmit}
        >
          <TextField
            margin="normal"
            label="Full Name"
            type="text"
            value={instructorFullName}
            onChange={(event) => {
              setInstructorFullName(event.target.value);
            }}
            fullWidth
          ></TextField>
          <TextField
            margin="normal"
            label="Mail"
            type="text"
            value={instructorMail}
            onChange={(event) => {
              setInstructorMail(event.target.value);
            }}
            fullWidth
          ></TextField>
          <TextField
            margin="normal"
            label="Department"
            type="text"
            value={instructorDepartment}
            onChange={(event) => {
              setInstructorDepartment(event.target.value);
            }}
            fullWidth
          ></TextField>
          <TextField
            margin="normal"
            label="Max Number of Assigned"
            type="number"
            value={instructorMaxNumOfInternships}
            onChange={(event) => {
              setInstructorMaxNumOfInternships(event.target.value);
            }}
            fullWidth
          ></TextField>
          <Button type="submit" variant="contained">
            Submit
          </Button>
        </form>
      </Modal>
    </>
  );
}
