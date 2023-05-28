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
  const [open, setOpen] = useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const matchInternships = (matchType) => {
    axios
      .post(`/api/match?matchType=${matchType}`)
      .then((response) => {
        refreshInternships();
        handleClose();
        console.log(response.data);
      })
      .catch(() => {
        console.log("match post error");
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
