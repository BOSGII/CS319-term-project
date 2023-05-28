import React, { useEffect, useState } from "react";
import { Container, Typography } from "@mui/material";
import Upload from "../Upload/Upload";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import ReplyCommentsSection from "../ReplyCommentsSection/ReplyCommentsSection";

export default function UploadReport({ internship, refreshInternship }) {
  const navigate = useNavigate();

  const [oldVersion, setOldVersion] = useState(null);

  const [replies, setReplies] = useState([]);

  useEffect(() => {
    // fetch oldest version if exists (if not initial submission)
    if (internship.status === "UNDER_EVALUATION") {
      axios
        .get(
          `/api/versions?internshipId=${internship.id}&versionNumber=${internship.numOfVersions}`
        )
        .then((response) => {
          setOldVersion(response.data);
        })
        .catch((error) => {
          console.log("/api/versions get error: ", error.message);
        });
    }
  }, [internship]);

  const handleSubmit = (file) => {
    let areAllRepliesProvided = true;

    if (oldVersion) {
      // means student uploads for revision
      // check if all input fields are filled
      replies.forEach((reply) => {
        if (reply.trim() === "") {
          areAllRepliesProvided = false;
        }
      });
    }

    if (file && areAllRepliesProvided) {
      let formData = new FormData();
      formData.append("report", file);

      if (oldVersion) {
        formData.append("replies", replies);
      }

      axios
        .post(`/api/versions?internshipId=${internship.id}`, formData)
        .then((response) => {
          refreshInternship();
        })
        .catch((error) => {
          console.log("versions post error", error);
        })
        .finally(() => {
          navigate(`/my_internships/${internship.type}`, { replace: true });
        });
    } else {
      // do nothing
    }
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
      {oldVersion && oldVersion.areCommentsProvided && (
        <ReplyCommentsSection
          versionId={oldVersion.id}
          replies={replies}
          setReplies={setReplies}
        />
      )}

      <Upload
        acceptedFileTypes={["PDF"]}
        handleSubmit={handleSubmit}
        handleCancel={handleCancel}
      />
    </Container>
  );
}
