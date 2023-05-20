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
    axios
      .post("/api/internships", internship)
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
        <form style={{ backgroundColor: "white" }} onSubmit={handleSubmit}>
          <InputLabel id="internship-type">Type</InputLabel>
          <Select
            label="Type"
            id="internship-type"
            name="type"
            value={internship.type}
            onChange={handleInputChange}
          >
            <MenuItem value={"CS299"}>CS299</MenuItem>
            <MenuItem value={"CS399"}>CS399</MenuItem>
          </Select>
          <TextField
            margin="normal"
            label="Start Date"
            name="startDate"
            type="date"
            value={internship.startDate}
            onChange={handleInputChange}
            InputLabelProps={{ shrink: true }}
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
          <TextField
            margin="normal"
            label="Student ID"
            name="studentId"
            onChange={handleInputChange}
            value={internship.studentId}
          />
          <TextField
            margin="normal"
            label="Student Full Name"
            name="studentFullName"
            onChange={handleInputChange}
            value={internship.studentFullName}
          />
          <TextField
            margin="normal"
            label="Student Mail"
            name="studentMail"
            onChange={handleInputChange}
            value={internship.studentMail}
          />
          <TextField
            margin="normal"
            label="Student Department"
            name="studentDepartment"
            onChange={handleInputChange}
            value={internship.studentDepartment}
          />
          <TextField
            margin="normal"
            label="Company Name"
            name="companyName"
            onChange={handleInputChange}
            value={internship.companyName}
          />
          <TextField
            margin="normal"
            label="Company Email"
            name="companyEmail"
            onChange={handleInputChange}
            value={internship.companyEmail}
          />
          <TextField
            margin="normal"
            label="Supervisor Name"
            name="supervisorName"
            onChange={handleInputChange}
            value={internship.supervisorName}
          />
          <TextField
            margin="normal"
            label="Supervisor Mail"
            name="supervisorMail"
            onChange={handleInputChange}
            value={internship.supervisorMail}
          />
          <TextField
            margin="normal"
            label="Supervisor Graduation Year"
            name="supervisorGraduationYear"
            type="date"
            onChange={handleInputChange}
            value={internship.supervisorGraduationYear}
            InputLabelProps={{ shrink: true }}
          />
          <TextField
            margin="normal"
            label="Supervisor Graduation Department"
            name="supervisorGraduationDepartment"
            onChange={handleInputChange}
            value={internship.supervisorGraduationDepartment}
          />
          <TextField
            margin="normal"
            label="Supervisor University"
            name="supervisorUniversity"
            onChange={handleInputChange}
            value={internship.supervisorUniversity}
          />
          <Button type="submit" variant="contained">
            Add Internship
          </Button>
        </form>
      </Modal>
    </>
  );
}
