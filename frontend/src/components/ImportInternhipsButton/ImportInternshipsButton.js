import { Button, Dialog } from "@mui/material";
import { useState } from "react";
import UploadFeedback from "../UploadFeedback/UploadFeedback";
import axios from "axios";

export default function ImportInternshipsButton({ refreshInternships }) {
  const sessionId = localStorage.getItem("sessionId");
  const [open, setOpen] = useState(false);

  const handleSubmit = (file) => {
    if (file) {
      let formData = new FormData();
      formData.append("file", file);
      axios
        .post(`http://localhost:8080/api/import`, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
            Authorization: `${sessionId}`,
          },
        })
        .then((response) => {
          refreshInternships();
          setOpen(false);
        })
        .catch((error) => {
          if (error.response.status === 400) {
            alert(error.response.data);
          }
        });
    } else {
      // do nothing
    }
  };

  const handleCancel = () => {
    setOpen(false);
  };

  return (
    <>
      <Button
        variant="contained"
        onClick={() => {
          setOpen(true);
        }}
      >
        Import Internships
      </Button>
      <Dialog
        open={open}
        onClose={() => {
          setOpen(false);
        }}
      >
        <UploadFeedback
          acceptedFileTypes={["XLSX"]}
          handleSubmit={handleSubmit}
          handleCancel={handleCancel}
        />
      </Dialog>
    </>
  );
}
