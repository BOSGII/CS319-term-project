import { useState } from "react";
import Upload from "../UploadFeedback/UploadFeedback";
import axios from "axios";
import { Button, Dialog } from "@mui/material";

export default function ChangeFileButton({ putUrl, refreshVersion }) {
  const sessionId = localStorage.getItem("sessionId");
  const [open, setOpen] = useState(false);

  const handleSubmit = (file) => {
    if (file) {
      let formData = new FormData();
      formData.append("file", file);
      axios
        .put(putUrl, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
            Authorization: `${sessionId}`,
          },
        })
        .then((response) => {
          refreshVersion();
          setOpen(false);
        })
        .catch((error) => {
          console.log(error.message);
        });
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
        sx={{ mt: 5 }}
      >
        Change
      </Button>
      <Dialog
        open={open}
        onClose={() => {
          setOpen(false);
        }}
      >
        <Upload
          acceptedFileTypes={["PDF"]}
          handleSubmit={handleSubmit}
          handleCancel={handleCancel}
        />
      </Dialog>
    </>
  );
}
