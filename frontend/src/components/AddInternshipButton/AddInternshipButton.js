import {
  Button,
  InputLabel,
  MenuItem,
  Modal,
  Select,
  TextField,
} from "@mui/material";
import { useState } from "react";
import axios from "axios";

export default function AddInternshipButton({ refreshInternships }) {
  const sessionId = localStorage.getItem("sessionId");

  // Define state variables for the form fields
  const [internship, setInternship] = useState({
    type: "",
    startDate: "",
    endDate: "",
    studentId: "",
    studentFullName: "",
    studentMail: "",
    studentDepartment: "",
    companyName: "",
    companyEmail: "",
    supervisorName: "",
    supervisorMail: "",
    supervisorGraduationYear: "",
    supervisorGraduationDepartment: "",
    supervisorUniversity: "",
  });

  // Define a state variable for the modal
  const [open, setOpen] = useState(false);

  const handleInputChange = (event) => {
    setInternship((prevState) => ({
      ...prevState,
      [event.target.name]: event.target.value,
    }));
  };

  // Define a function to handle the form submit
  const handleSubmit = async (event) => {
    event.preventDefault();

    // check if input fields are filled
    if (
      !internship.type.trim() ||
      !internship.startDate ||
      !internship.endDate ||
      !internship.studentId.trim()  ||
      !internship.studentFullName.trim()  ||
      !internship.studentMail.trim()  ||
      !internship.studentDepartment.trim()  ||
      !internship.companyName.trim()  ||
      !internship.companyEmail.trim()  ||
      !internship.supervisorName.trim()  ||
      !internship.supervisorMail.trim()  ||
      !internship.supervisorGraduationYear  ||
      !internship.supervisorGraduationDepartment ||
      !internship.supervisorUniversity.trim() 
    ) {
      alert("All input fields must be filled");
      return;
    }

    if (isNaN(internship.studentId)) {
      alert("Student id must be a number");
      return;
    }

    axios
      .post("http://localhost:8080/api/internships", internship, {
        headers: {
          Authorization: `${sessionId}`,
        },
      })
      .then((response) => {
        refreshInternships();
        setOpen(false);
        setInternship({
          type: "",
          startDate: "",
          endDate: "",
          studentId: "",
          studentFullName: "",
          studentMail: "",
          studentDepartment: "",
          companyName: "",
          companyEmail: "",
          supervisorName: "",
          supervisorMail: "",
          supervisorGraduationYear: "",
          supervisorGraduationDepartment: "",
          supervisorUniversity: "",
        });
      })
      .catch((error) => {
        console.log("/internships post error");
      });
  };

  return (
    <>
      <Button variant="contained" onClick={() => setOpen(true)}>
        Add Internship
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
        <form
          style={{
            backgroundColor: "white",
            maxHeight: "90vh",
            overflowY: "auto",
            padding: "1rem",
            maxWidth: "400px",
            width: "100%",
          }}
          onSubmit={handleSubmit}
        >
          <div style={{ padding: "1rem" }}>
            <InputLabel id="internship-type">Type</InputLabel>
            <Select
              labelId="internship-type"
              name="type"
              value={internship.type}
              onChange={handleInputChange}
              style={{ marginBottom: "1rem" }}
            >
              <MenuItem value={"CS299"}>CS299</MenuItem>
              <MenuItem value={"CS399"}>CS399</MenuItem>
            </Select>
          </div>

          <div style={{ padding: "1rem" }}>
            <TextField
              margin="normal"
              label="Start Date"
              name="startDate"
              type="date"
              value={internship.startDate}
              onChange={handleInputChange}
              InputLabelProps={{ shrink: true }}
              style={{ marginRight: "1rem" }}
            />
            <TextField
              margin="normal"
              label="End Date"
              name="endDate"
              type="date"
              value={internship.endDate}
              onChange={handleInputChange}
              InputLabelProps={{ shrink: true }}
            />
          </div>

          <div style={{ padding: "1rem" }}>
            <TextField
              margin="normal"
              label="Student ID"
              name="studentId"
              onChange={handleInputChange}
              value={internship.studentId}
              style={{ marginRight: "1rem" }}
            />
            <TextField
              margin="normal"
              label="Student Full Name"
              name="studentFullName"
              onChange={handleInputChange}
              value={internship.studentFullName}
            />
          </div>

          <div style={{ padding: "1rem" }}>
            <TextField
              margin="normal"
              label="Student Mail"
              name="studentMail"
              onChange={handleInputChange}
              value={internship.studentMail}
              style={{ marginRight: "1rem" }}
            />
            <TextField
              margin="normal"
              label="Student Department"
              name="studentDepartment"
              onChange={handleInputChange}
              value={internship.studentDepartment}
            />
          </div>

          <div style={{ padding: "1rem" }}>
            <TextField
              margin="normal"
              label="Company Name"
              name="companyName"
              onChange={handleInputChange}
              value={internship.companyName}
              style={{ marginRight: "1rem" }}
            />
            <TextField
              margin="normal"
              label="Company Email"
              name="companyEmail"
              onChange={handleInputChange}
              value={internship.companyEmail}
            />
          </div>

          <div style={{ padding: "1rem" }}>
            <TextField
              margin="normal"
              label="Supervisor Name"
              name="supervisorName"
              onChange={handleInputChange}
              value={internship.supervisorName}
              style={{ marginRight: "1rem" }}
            />
            <TextField
              margin="normal"
              label="Supervisor Mail"
              name="supervisorMail"
              onChange={handleInputChange}
              value={internship.supervisorMail}
            />
          </div>

          <div style={{ padding: "1rem" }}>
            <TextField
              margin="normal"
              label="Supervisor Graduation Year"
              name="supervisorGraduationYear"
              type="date"
              onChange={handleInputChange}
              value={internship.supervisorGraduationYear}
              InputLabelProps={{ shrink: true }}
              style={{ marginRight: "1rem" }}
            />
            <TextField
              margin="normal"
              label="Supervisor Graduation Department"
              name="supervisorGraduationDepartment"
              onChange={handleInputChange}
              value={internship.supervisorGraduationDepartment}
            />
          </div>

          <div style={{ padding: "1rem" }}>
            <TextField
              margin="normal"
              label="Supervisor University"
              name="supervisorUniversity"
              onChange={handleInputChange}
              value={internship.supervisorUniversity}
            />
          </div>

          <div style={{ padding: "1rem" }}>
            <Button type="submit" variant="contained">
              Add Internship
            </Button>
          </div>
        </form>
      </Modal>
    </>
  );
}
