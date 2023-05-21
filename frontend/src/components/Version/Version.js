import { Button } from "@mui/material";

export default function Version({ setAddNewVersionButtonPressed }) {
  return (
    <Button
      onClick={() => {
        setAddNewVersionButtonPressed(true);
      }}
    >
      add new version
    </Button>
  );
}
