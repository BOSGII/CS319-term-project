import { Button, Dialog } from "@mui/material";
import { useState } from "react";
import Upload from "../Upload/Upload";
import axios from "axios";

export default function ImportInternshipsButton({ refreshInternships }) {
  const [open, setOpen] = useState(false);

  const handleSubmit = (file) => {
    if (file) {
      let formData = new FormData();
      formData.append("file", file);

      axios
        .post(`/api/import`, formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          refreshInternships();
        })
        .catch((error) => {
          console.log("import error");
        })
        .finally(() => {
          setOpen(false);
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
        <Upload
          acceptedFileTypes={["XLSX"]}
          handleSubmit={handleSubmit}
          handleCancel={handleCancel}
        />
      </Dialog>
    </>
  );
}
