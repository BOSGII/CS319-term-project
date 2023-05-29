import {
  Button,
  Dialog,
  InputLabel,
  MenuItem,
  Select,
  TextField,
  Typography,
} from "@mui/material";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function FinalizeButton({ submissionId, refreshSubmission }) {
  const sessionId = localStorage.getItem("sessionId");
  const navigate = useNavigate();
  const [open, setOpen] = useState(false);
  const [result, setResult] = useState({
    workDoneRelated: "",
    supervisorRelated: "",
    companyEvaluation: "",
    grade1: "",
    grade2: "",
    grade3: "",
    grade4: "",
    grade5: "",
    grade6: "",
    grade7: "",
    pages1: "",
    pages2: "",
    pages3: "",
    pages4: "",
    pages5: "",
    pages6: "",
    pages7: "",
  });

  const handleInputChange = (event) => {
    setResult((prevState) => ({
      ...prevState,
      [event.target.name]: event.target.value,
    }));
  };

  const isCSVValid = (csvString) => {
    // Split the string into individual values
    const values = csvString.split(",");

    // Check each value if it's a valid positive number
    for (let i = 0; i < values.length; i++) {
      const value = values[i].trim();

      // Use isNaN to check if the value is not a number or if it's less than or equal to 0
      if (isNaN(value) || parseFloat(value) <= 0) {
        return false;
      }
    }

    // All values are valid positive numbers
    return true;
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // check validity of the input
    if (
      result.workDoneRelated &&
      result.supervisorRelated &&
      result.companyEvaluation
    ) {
      if (
        result.workDoneRelated === "NO" ||
        result.supervisorRelated === "NO"
      ) {
        axios
          .post(
            `http://localhost:8080/api/submissions/${submissionId}/finalize`,
            {
              workDoneRelated: result.workDoneRelated,
              supervisorRelated: result.supervisorRelated,
              companyEvaluation: result.companyEvaluation,
              grade1: "",
              grade2: "",
              grade3: "",
              grade4: "",
              grade5: "",
              grade6: "",
              grade7: "",
              pages1: "",
              pages2: "",
              pages3: "",
              pages4: "",
              pages5: "",
              pages6: "",
              pages7: "",
            },
            {
              headers: {
                Authorization: `${sessionId}`,
              },
            }
          )
          .then((response) => {
            refreshSubmission();
            setOpen(false);
          })
          .catch((error) => {
            console.log("finalize post error", error.message);
          });
      } else {
        if (
          parseInt(result.grade1) >= 0 &&
          parseInt(result.grade1) <= 10 &&
          parseInt(result.grade2) >= 0 &&
          parseInt(result.grade2) <= 10 &&
          parseInt(result.grade3) >= 0 &&
          parseInt(result.grade3) <= 10 &&
          parseInt(result.grade4) >= 0 &&
          parseInt(result.grade4) <= 10 &&
          parseInt(result.grade5) >= 0 &&
          parseInt(result.grade5) <= 10 &&
          parseInt(result.grade6) >= 0 &&
          parseInt(result.grade6) <= 10 &&
          parseInt(result.grade7) >= 0 &&
          parseInt(result.grade7) <= 10
        ) {
          if (
            isCSVValid(result.pages1) &&
            isCSVValid(result.pages2) &&
            isCSVValid(result.pages3) &&
            isCSVValid(result.pages4) &&
            isCSVValid(result.pages5) &&
            isCSVValid(result.pages6) &&
            isCSVValid(result.pages7)
          ) {
            axios
              .post(
                `http://localhost:8080/api/submissions/${submissionId}/finalize`,
                result,
                {
                  headers: {
                    Authorization: `${sessionId}`,
                  },
                }
              )
              .then((response) => {
                refreshSubmission();
              })
              .catch((error) => {
                console.log("finalize post error", error.message);
              })
              .finally(() => {
                setOpen(false);
              });
          } else {
            alert("Pages should obey csv format");
          }
        } else {
          alert("Grades should be between 0 and 10");
        }
      }
    } else {
      alert("You must select all of the options");
    }
  };

  return (
    <>
      <Button
        onClick={() => {
          setOpen(true);
        }}
      >
        Finalize
      </Button>
      <Dialog
        open={open}
        onClose={() => {
          setOpen(false);
        }}
        maxWidth="1400"
      >
        <form onSubmit={handleSubmit}>
          <div style={{ display: "flex", alignItems: "center" }}>
            <InputLabel
              sx={{
                flex: "0 0 auto",
                marginRight: "1rem",
                whiteSpace: "nowrap",
                overflow: "hidden",
                textOverflow: "ellipsis",
              }}
            >
              Is the work done related to computer engineering?
            </InputLabel>
            <Select
              name="workDoneRelated"
              value={result.workDoneRelated}
              onChange={handleInputChange}
              sx={{ marginLeft: "auto" }}
            >
              <MenuItem value={"YES"}>YES</MenuItem>
              <MenuItem value={"NO"}>NO</MenuItem>
            </Select>
          </div>
          <div style={{ display: "flex", alignItems: "center" }}>
            <InputLabel
              sx={{
                flex: "0 0 auto",
                marginRight: "1rem",
                whiteSpace: "nowrap",
                overflow: "hidden",
                textOverflow: "ellipsis",
              }}
            >
              Is the supervisor a computer engineer or has a similar engineering
              background?{" "}
            </InputLabel>
            <Select
              name="supervisorRelated"
              value={result.supervisorRelated}
              onChange={handleInputChange}
              sx={{ marginLeft: "auto" }}
            >
              <MenuItem value={"YES"}>YES</MenuItem>
              <MenuItem value={"NO"}>NO</MenuItem>
            </Select>
          </div>
          <div style={{ display: "flex", alignItems: "center" }}>
            <InputLabel
              sx={{
                flex: "0 0 auto",
                marginRight: "1rem",
                whiteSpace: "nowrap",
                overflow: "hidden",
                textOverflow: "ellipsis",
              }}
            >
              Evaluation of Company/Department
            </InputLabel>
            <Select
              name="companyEvaluation"
              value={result.companyEvaluation}
              onChange={handleInputChange}
              sx={{ marginLeft: "auto" }}
            >
              <MenuItem value={"Strongly Recommend"}>
                Strongly Recommend
              </MenuItem>
              <MenuItem value={"Satisfied"}>Satisfied</MenuItem>
              <MenuItem value={"Not Recommending"}>Not Recommending</MenuItem>
            </Select>
          </div>
          <Typography variant="h6">Evaluation of the Work</Typography>
          <div style={{ display: "flex", alignItems: "center" }}>
            <InputLabel
              sx={{
                flex: "0 0 auto",
                marginRight: "1rem",
                whiteSpace: "pre-wrap",
                overflow: "hidden",
                textOverflow: "ellipsis",
              }}
            >
              1) Able to perform work at the level expected from a summer
              training in the area of computer engineering.
            </InputLabel>
            <div style={{ marginLeft: "auto" }}>
              <TextField
                name="grade1"
                label="Grade"
                type="number"
                value={result.grade1}
                onChange={handleInputChange}
                sx={{ width: "10rem" }}
              ></TextField>
              <TextField
                name="pages1"
                label="Pages (csv)"
                type="text"
                value={result.pages1}
                onChange={handleInputChange}
                sx={{ width: "10rem" }}
              ></TextField>
            </div>
          </div>
          <div style={{ display: "flex", alignItems: "center" }}>
            <InputLabel
              sx={{
                flex: "0 0 auto",
                marginRight: "1rem",
                whiteSpace: "pre-wrap",
                overflow: "hidden",
                textOverflow: "ellipsis",
              }}
            >
              2) Solves complex engineering problems by applying principles of
              engineering, science, and mathematics
            </InputLabel>
            <div style={{ marginLeft: "auto" }}>
              <TextField
                name="grade2"
                label="Grade"
                type="number"
                value={result.grade2}
                onChange={handleInputChange}
                sx={{ width: "10rem" }}
              ></TextField>
              <TextField
                name="pages2"
                label="Pages (csv)"
                type="text"
                value={result.pages2}
                onChange={handleInputChange}
                sx={{ width: "10rem" }}
              ></TextField>
            </div>
          </div>
          <div style={{ display: "flex", alignItems: "center" }}>
            <InputLabel
              sx={{
                flex: "0 0 auto",
                marginRight: "1rem",
                whiteSpace: "pre-wrap",
                overflow: "hidden",
                textOverflow: "ellipsis",
              }}
            >
              3) Recognizes ethical and professional responsibilities in
              engineering situations
            </InputLabel>
            <div style={{ marginLeft: "auto" }}>
              <TextField
                name="grade3"
                label="Grade"
                type="number"
                value={result.grade3}
                onChange={handleInputChange}
                sx={{ width: "10rem" }}
              ></TextField>
              <TextField
                name="pages3"
                label="Pages (csv)"
                type="text"
                value={result.pages3}
                onChange={handleInputChange}
                sx={{ width: "10rem" }}
              ></TextField>
            </div>
          </div>
          <div style={{ display: "flex", alignItems: "center" }}>
            <InputLabel
              sx={{
                flex: "0 0 auto",
                marginRight: "1rem",
                whiteSpace: "pre-wrap",
                overflow: "hidden",
                textOverflow: "ellipsis",
              }}
            >
              4) Able to make informed judgments that consider the impact of
              engineering solutions in global, economic, environmental, and
              societal contexts.
            </InputLabel>
            <div style={{ marginLeft: "auto" }}>
              <TextField
                name="grade4"
                label="Grade"
                type="number"
                value={result.grade4}
                onChange={handleInputChange}
                sx={{ width: "10rem" }}
              ></TextField>
              <TextField
                name="pages4"
                label="Pages (csv)"
                type="text"
                value={result.pages4}
                onChange={handleInputChange}
                sx={{ width: "10rem" }}
              ></TextField>
            </div>
          </div>
          <div style={{ display: "flex", alignItems: "center" }}>
            <InputLabel
              sx={{
                flex: "0 0 auto",
                marginRight: "1rem",
                whiteSpace: "pre-wrap",
                overflow: "hidden",
                textOverflow: "ellipsis",
              }}
            >
              5) Able to acquire new knowledge using appropriate learning
              strategy or strategies.
            </InputLabel>
            <div style={{ marginLeft: "auto" }}>
              <TextField
                name="grade5"
                label="Grade"
                type="number"
                value={result.grade5}
                onChange={handleInputChange}
                sx={{ width: "10rem" }}
              ></TextField>
              <TextField
                name="pages5"
                label="Pages (csv)"
                type="text"
                value={result.pages5}
                onChange={handleInputChange}
                sx={{ width: "10rem" }}
              ></TextField>
            </div>
          </div>
          <div style={{ display: "flex", alignItems: "center" }}>
            <InputLabel
              sx={{
                flex: "0 0 auto",
                marginRight: "1rem",
                whiteSpace: "pre-wrap",
                overflow: "hidden",
                textOverflow: "ellipsis",
              }}
            >
              6) Able to apply new knowledge as needed.
            </InputLabel>
            <div style={{ marginLeft: "auto" }}>
              <TextField
                name="grade6"
                label="Grade"
                type="number"
                value={result.grade6}
                onChange={handleInputChange}
                sx={{ width: "10rem" }}
              ></TextField>
              <TextField
                name="pages6"
                label="Pages (csv)"
                type="text"
                value={result.pages6}
                onChange={handleInputChange}
                sx={{ width: "10rem" }}
              ></TextField>
            </div>
          </div>
          <Typography variant="h6">Evaluation of the Report</Typography>
          <div style={{ display: "flex", alignItems: "center" }}>
            <InputLabel
              sx={{
                flex: "0 0 auto",
                marginRight: "1rem",
                whiteSpace: "pre-wrap",
                overflow: "hidden",
                textOverflow: "ellipsis",
              }}
            >
              Able to prepare reports with high standards in terms of content,
              organization, style and language (the Summer Training Report
              itself is to be evaluated.)
            </InputLabel>
            <div style={{ marginLeft: "auto" }}>
              <TextField
                name="grade7"
                label="Grade"
                type="number"
                value={result.grade7}
                onChange={handleInputChange}
                sx={{ width: "10rem" }}
              ></TextField>
              <TextField
                name="pages7"
                label="Pages (csv)"
                type="text"
                value={result.pages7}
                onChange={handleInputChange}
                sx={{ width: "10rem" }}
              ></TextField>
            </div>
          </div>
          <Button type="submit" variant="contained">
            Submit
          </Button>
        </form>
      </Dialog>
    </>
  );
}
