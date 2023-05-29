import { IconButton, Tooltip } from "@mui/material";
import EditIcon from '@mui/icons-material/Edit';

export default function ChangeInternshipDetailsButton() {
  return <Tooltip title="Change Internship Details"><IconButton><EditIcon></EditIcon></IconButton></Tooltip>;
}
