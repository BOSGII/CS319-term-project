import { Button, Typography } from "@mui/material";
import { useState } from "react";
import { FileUploader } from "react-drag-drop-files";

export default function Upload({
  acceptedFileTypes,
  handleSubmit,
  handleCancel,
}) {
  const [file, setFile] = useState(null);

  const handleChange = (file) => {
    console.log(file.name);
    setFile(file);
  };

  return (
    <>
      <FileUploader
        handleChange={handleChange}
        types={acceptedFileTypes}
        fileOrFiles={file}
      ></FileUploader>
      <Typography>Uploaded file: {file ? file.name : "None"}</Typography>
      <Button
        onClick={() => {
          if (file) {
            handleSubmit(file);
          }
        }}
      >
        Submit
      </Button>
      <Button
        onClick={() => {
          setFile(null);
          handleCancel();
        }}
      >
        Cancel
      </Button>
    </>
  );
}
