import { Button, Dialog } from "@mui/material";
import { useState } from "react";
import Upload from "../Upload/Upload";

export default function ImportInternshipsButton() {
  const [open, setOpen] = useState(false);

  const handleCancel = () => {
    setOpen(false);
  };

  return (
    <>
      <Button
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
        <Upload acceptedFileTypes={["XLSX"]} handleCancel={handleCancel} />
      </Dialog>
    </>
  );
}
