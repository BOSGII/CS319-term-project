import { Button, Modal, TextField } from "@mui/material";
import { useState } from "react";
import axios from "axios";

export default function AddInstructorButton({ refreshInstructors }) {
  // Define state variables for the form fields
  const [instructor, setInstructor] = useState({
    id: "",
    fullName: "",
    mail: "",
    department: "",
    maxNumOfInternships: "",
  });

  // Define a state variable for the modal
  const [open, setOpen] = useState(false);

  const handleInputChange = (event) => {
    setInstructor((prevState) => ({
      ...prevState,
      [event.target.name]: event.target.value,
    }));
  };

  // Define a function to handle the form submit
  const handleSubmit = async (event) => {
    event.preventDefault();
    axios
      .post("/api/instructors", instructor)
      .then((response) => {
        refreshInstructors();
        setOpen(false);
        setInstructor({
          id: "",
          fullName: "",
          mail: "",
          department: "",
          maxNumOfInternships: "",
        });
      })
      .catch((error) => {
        console.log("/instructors post error");
      });
  };

  return (
    <>
      <Button variant="contained" onClick={() => setOpen(true)}>
        Add Instructor
      </Button>
      <Modal
        sx={{
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
          bgcolor: "rgba(0, 0, 0, 0.5)",
        }}
        open={open}
        onClose={() => setOpen(false)}
      >
        <form style={{ backgroundColor: "white" }} onSubmit={handleSubmit}>
          <TextField
            margin="normal"
            label="ID"
            name="id"
            value={instructor.id}
            onChange={handleInputChange}
          />
          <TextField
            label="Full Name"
            name="fullName"
            value={instructor.fullName}
            onChange={handleInputChange}
            fullWidth
          />
          <TextField
            label="Email"
            name="mail"
            value={instructor.mail}
            onChange={handleInputChange}
            fullWidth
          />
          <TextField
            label="Department"
            name="department"
            value={instructor.department}
            onChange={handleInputChange}
            fullWidth
          />
          <TextField
            label="Max Number of Internships"
            name="maxNumOfInternships"
            value={instructor.maxNumOfInternships}
            onChange={handleInputChange}
            fullWidth
            type="number"
          />
          <Button type="submit" variant="contained">
            Add Instructor
          </Button>
        </form>
      </Modal>
    </>
  );
}
