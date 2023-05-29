
import { IconButton, Dialog, Typography, Tooltip } from "@mui/material";
import UploadIcon from "@mui/icons-material/Upload";
import { useState } from "react";
import axios from "axios";
import Upload from "../Upload/Upload";
import UploadFeedback from "../UploadFeedback/UploadFeedback"
export default function UploadCompanyEvaluationFormButton({
  internshipId,
  setInternshipStatus,
}) {
  const sessionId = localStorage.getItem("sessionId");
  const [open, setOpen] = useState(false);
  const [supervisorGrade, setSupervisorGrade] = useState(null);

  const handleSubmit = (file) => {
    if (file && supervisorGrade) {
      let formData = new FormData();
      formData.append("file", file);
      formData.append("supervisorGrade", supervisorGrade);

      axios
        .post(
          `http://localhost:8080/api/internships/${internshipId}/companyForm`,
          formData,
          { headers: { Authorization: `${sessionId}` } }
        )
        .then((response) => {
          setInternshipStatus(response.data.status);
          setOpen(false);
        })
        .catch((error) => {
          console.log("upload company evaluation form error", error.message);
        });
    } else {
      alert("You need to upload the document AND enter the company evaluation grade!")
    }
  };

  const handleCancel = () => {
    setOpen(false);
  };

  return (
    <>
      <Tooltip title="Upload Company Evaluation Form">
        <IconButton
          onClick={() => {
            setOpen(true);
          }}
        >
          <UploadIcon></UploadIcon>
        </IconButton>
      </Tooltip>
      <Dialog
        open={open}
        onClose={() => {
          setOpen(false);
        }}
      >
        <Typography variant="h5" style={{ fontSize: "24px" }}>
          Enter supervisor grade:
        </Typography>
        <input
          type="number"
          value={supervisorGrade}
          onChange={(el) => setSupervisorGrade(el.target.value)}
        ></input>
        <UploadFeedback
          acceptedFileTypes={["PDF"]}
          handleSubmit={handleSubmit}
          handleCancel={handleCancel}
        />
      </Dialog>
    </>
  );

}
