import React from "react";
import { Container, Typography } from "@mui/material";
import Upload from "../Upload/Upload";
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function UploadReport({ internship, refreshInternship }) {
  const navigate = useNavigate();

  const handleSubmit = (file) => {
    let formData = new FormData();
    formData.append("file", file);

    axios
      .post(`/api/versions?internshipId=${internship.id}`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      })
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.log("versions post error", error);
      })
      .finally(() => {
        refreshInternship();
        navigate(`/my_internships/${internship.type}`);
      });
  };

  const handleCancel = () => {
    navigate(-1);
  };

  return (
    <Container>
      <Typography variant="h1">
        {internship.status === "UNDER_EVALUATION"
          ? `You are making submission for ${
              internship.numOfVersions + 1
            }. version`
          : "You are making initial submission"}
      </Typography>
      <Upload
        acceptedFileTypes={["PDF"]}
        handleSubmit={handleSubmit}
        handleCancel={handleCancel}
      />
    </Container>
  );
}
