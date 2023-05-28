import { IconButton, Dialog, Typography } from "@mui/material";
import UploadIcon from "@mui/icons-material/Upload";
import { useState } from "react";
import axios from "axios";
import Upload from "../Upload/Upload";

export default function UploadCompanyEvaluationFormButton({
  internshipId,
  setInternshipStatus,
}) {
  const [open, setOpen] = useState(false);
  const [supervisorGrade, setSupervisorGrade] = useState(null);

  const handleSubmit = (file) => {
    if (file && supervisorGrade) {
      let formData = new FormData();
      formData.append("file", file);
      formData.append("supervisorGrade", supervisorGrade);

      axios
        .post(`/api/internships/${internshipId}/companyForm`, formData)
        .then((response) => {
          setInternshipStatus(response.data.status);
          setOpen(false);
        })
        .catch((error) => {
          console.log("upload company evaluation form error", error.message);
        });
    }
  };

  const handleCancel = () => {
    setOpen(false);
  };

  return (
    <>
      <IconButton
        onClick={() => {
          setOpen(true);
        }}
      >
        <UploadIcon></UploadIcon>
      </IconButton>
      <Dialog
        open={open}
        onClose={() => {
          setOpen(false);
        }}
      >
        <Typography>Enter supervisor grade:</Typography>
        <input
          type="number"
          value={supervisorGrade}
          onChange={(el) => setSupervisorGrade(el.target.value)}
        ></input>
        <Upload
          acceptedFileTypes={["PDF"]}
          handleSubmit={handleSubmit}
          handleCancel={handleCancel}
        />
      </Dialog>
    </>
  );
}
