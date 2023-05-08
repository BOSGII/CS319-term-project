import {Drawer} from "@mui/material";

export default function SubmissionSidebar({ isSidebarOpen, onToggleSidebar }) {
  return (
    <Drawer anchor="left" open={isSidebarOpen} onClose={onToggleSidebar}>
      {<div>ASDFGASDF</div>}
    </Drawer>
  );
}
