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
      <div style={{ marginLeft: '300px' }}>
        <FileUploader
          handleChange={handleChange}
          types={acceptedFileTypes}
          fileOrFiles={file}
        />
      </div>
      <Typography>Uploaded file: {file ? file.name : "None"}</Typography>
      <Button
        style={{ fontSize: "18px" }}
        onClick={() => {
          if (file) {
            handleSubmit(file);
          } else {
            handleSubmit();
          }
        }}
      >
        Submit
      </Button>
      <Button
        style={{ fontSize: "18px" }}
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
