import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
} from "@mui/material";
import axios from "axios";
import { useState } from "react";

export default function MatchInternshipsButton({ refreshInternships }) {
  const sessionId = localStorage.getItem("sessionId");
  const [open, setOpen] = useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };
  const matchInternships = (matchType) => {
    axios
      .get(`http://localhost:8080/api/match?matchType=${matchType}`,  {
        headers: {
          Authorization: `${sessionId}`,
        },
      })
      .then((response) => {
        refreshInternships();
        handleClose();
      })
      .catch((error) => {
        if (error.response.status === 400) {
          alert(error.response.data);
        }
      });
  };

  return (
    <div>
      <Button variant="contained" onClick={handleClickOpen}>
        Match Internships
      </Button>
      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {"Match Internships with Instructors"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            "Equal" option distributes internships to instructors as equally as
            possible while "Ratio" option performs a proportional distribution.
            Overrides current assignments. This action is irreversible!
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button
            onClick={() => {
              matchInternships("EQUAL");
            }}
          >
            Equal
          </Button>
          <Button
            onClick={() => {
              matchInternships("RATIO");
            }}
            autoFocus
          >
            Ratio
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
