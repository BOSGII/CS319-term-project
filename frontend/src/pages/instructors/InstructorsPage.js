import { Button, Container, Modal, TextField, Typography } from '@mui/material';
import { useFetch } from '../../hooks/useFetch';
import InstructorList from '../../components/InstructorList/InstructorList';

// styles
import './InstructorsPage.css'
import { useState } from 'react';

function AddInstructorButton({refreshList}) {
  // Define state variables for the form fields
  const [id, setId] = useState('');
  const [fullName, setFullName] = useState('');
  const [mail, setMail] = useState('');
  const [department, setDepartment] = useState('');
  const [maxNumOfInternships, setMaxNumOfInternships] = useState('');
  const {postData} = useFetch("/api/instructors", "POST");

  // Define a state variable for the modal
  const [open, setOpen] = useState(false);

  // Define a function to handle the form submit
  const handleSubmit = async (event) => {
    event.preventDefault();
    postData({id, fullName, mail, department, maxNumOfInternships});
    setOpen(false);
    refreshList();
    setId('');
    setFullName('');
    setMail('');
    setDepartment('');
    setMaxNumOfInternships('');
  };

  return (
    <>
      <Button variant="contained" onClick={() => setOpen(true)}>
        Add Instructor
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
            label="ID"
            value={id}
            onChange={(event) => setId(event.target.value)}
            fullWidth
          />
          <TextField
            label="Full Name"
            value={fullName}
            onChange={(event) => setFullName(event.target.value)}
            fullWidth
          />
          <TextField
            label="Email"
            value={mail}
            onChange={(event) => setMail(event.target.value)}
            fullWidth
          />
          <TextField
            label="Department"
            value={department}
            onChange={(event) => setDepartment(event.target.value)}
            fullWidth
          />
          <TextField
            label="Max Number of Internships"
            value={maxNumOfInternships}
            onChange={(event) =>
              setMaxNumOfInternships(parseInt(event.target.value))
            }
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

export default function InstructorsPage(){
    const {data, isPending, error, refreshList } = useFetch(`/api/instructors`);
    const {deleteData} = useFetch("/api/instructors", "DELETE");

    return(
        <Container>
            <Typography>
                Secretary page showing all instructors
            </Typography>
            <AddInstructorButton refreshList={refreshList} />
            {error && <div>{error}</div>}
            {isPending && <div>loading...</div>}
            {data && <InstructorList instructors={data} deleteInstructor={deleteData} refreshList={refreshList}/>}
        </Container>
    )
}