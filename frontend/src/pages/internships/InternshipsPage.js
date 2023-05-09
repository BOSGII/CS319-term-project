import { Button, Modal, Container, TextField, Typography } from '@mui/material'
import { useContext, useState } from 'react'
import { UserContext } from '../../contexts/UserContext'
import { useFetch } from '../../hooks/useFetch';
import InternshipList from '../../components/InternshipList/InternshipList';

import './InternshipsPage.css'
import { useLocation } from 'react-router-dom';

function AddInternshipButton({refreshList}) {
  const {postData} = useFetch("/api/internships", "POST");

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

  // Define a function to handle the form submit
  const handleSubmit = async (event) => {
    event.preventDefault();
    postData(internship);
    setOpen(false);
    refreshList();
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
  };

  return (
    <>
      <Button variant="contained" onClick={() => setOpen(true)}>
        Add Internship
      </Button>
      <Modal sx={{
          display: 'flex',
          alignItems: 'center',
          justifyContent: 'center',
          bgcolor: 'rgba(0, 0, 0, 0.5)',
        }}
        open={open} onClose={() => setOpen(false)}>
        <form style={{backgroundColor: 'white'}} onSubmit={handleSubmit}>
            <TextField
              margin="normal"
              label="Type"
              name="type"
              value={internship.type}
              onChange={(event) => setInternship((prevState) => ({ ...prevState, [event.target.name]: event.target.value}))}
            />
            <TextField
              margin="normal"
              label="Start Date"
              name="startDate"
              type="date"
              value={internship.startDate}
              onChange={(event) => setInternship((prevState) => ({ ...prevState, [event.target.name]: event.target.value}))}
              InputLabelProps={{ shrink: true }}
            />
            <TextField
              margin="normal"
              label="End Date"
              name="endDate"
              type="date"
              value={internship.endDate}
              onChange={(event) => setInternship((prevState) => ({ ...prevState, [event.target.name]: event.target.value}))}
              InputLabelProps={{ shrink: true }}
            />
            <TextField
              margin="normal"
              label="Student ID"
              name="studentId"
              onChange={(event) => setInternship((prevState) => ({ ...prevState, [event.target.name]: event.target.value}))}
              value={internship.studentId}
            />
            <TextField
              margin="normal"
              label="Student Full Name"
              name="studentFullName"
              onChange={(event) => setInternship((prevState) => ({ ...prevState, [event.target.name]: event.target.value}))}
              value={internship.studentFullName}
            />
            <TextField
              margin="normal"
              label="Student Mail"
              name="studentMail"
              onChange={(event) => setInternship((prevState) => ({ ...prevState, [event.target.name]: event.target.value}))}
              value={internship.studentMail}
            />
            <TextField
              margin="normal"
              label="Student Department"
              name="studentDepartment"
              onChange={(event) => setInternship((prevState) => ({ ...prevState, [event.target.name]: event.target.value}))}
              value={internship.studentDepartment}
            />
            <TextField
              margin="normal"
              label="Company Name"
              name="companyName"
              onChange={(event) => setInternship((prevState) => ({ ...prevState, [event.target.name]: event.target.value}))}
              value={internship.companyName}
            />
            <TextField
              margin="normal"
              label="Company Email"
              name="companyEmail"
              onChange={(event) => setInternship((prevState) => ({ ...prevState, [event.target.name]: event.target.value}))}
              value={internship.companyEmail}
            />
            <TextField
              margin="normal"
              label="Supervisor Name"
              name="supervisorName"
              onChange={(event) => setInternship((prevState) => ({ ...prevState, [event.target.name]: event.target.value}))}
              value={internship.supervisorName}
            />
            <TextField
              margin="normal"
              label="Supervisor Mail"
              name="supervisorMail"
              onChange={(event) => setInternship((prevState) => ({ ...prevState, [event.target.name]: event.target.value}))}
              value={internship.supervisorMail}
            />
            <TextField
              margin="normal"
              label="Supervisor Graduation Year"
              name="supervisorGraduationYear"
              type="date"
              onChange={(event) => setInternship((prevState) => ({ ...prevState, [event.target.name]: event.target.value}))}
              value={internship.supervisorGraduationYear}
              InputLabelProps={{ shrink: true }}
            />
            <TextField
              margin="normal"
              label="Supervisor Graduation Department"
              name="supervisorGraduationDepartment"
              onChange={(event) => setInternship((prevState) => ({ ...prevState, [event.target.name]: event.target.value}))}
              value={internship.supervisorGraduationDepartment}
            />
            <TextField
              margin="normal"
              label="Supervisor University"
              name="supervisorUniversity"
              onChange={(event) => setInternship((prevState) => ({ ...prevState, [event.target.name]: event.target.value}))}
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

export default function InternshipsPage(){
    const {user} = useContext(UserContext);
    const location = useLocation();
    let fetchUrl;

    switch(user.role){
        case "student":
            fetchUrl = `/api/internships?studentId=${user.id}`;
            break;

        case "instructor":
            fetchUrl = `/api/internships?instructorId=${user.id}`;
            break;

        case "secretary":
            if(location.pathname === "/internships"){
                fetchUrl = `/api/internships`;
            }
            else { // /instructors/{instructorId}
                const instructorId = location.pathname.split('/').at(-1);
                fetchUrl = `/api/internships?instructorId=${instructorId}`;
            }
            break;

        default:
    }

    const {data, isPending, error, refreshList} = useFetch(fetchUrl);

    return(
        <Container>
            <Typography>
                internships page
            </Typography>
            <AddInternshipButton refreshList={refreshList}/>
            {error && <div>{error}</div>}
            {isPending && <div>loading...</div>}
            {data && <InternshipList internships={data}/>}
        </Container>
    )
}