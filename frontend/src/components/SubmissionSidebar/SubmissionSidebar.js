import {
  Drawer,
  List,
  ListItem,
  Divider,
  Toolbar,
  Typography,
  IconButton,
} from "@mui/material";

import ChevronLeftIcon from "@mui/icons-material/ChevronLeft";

export default function SubmissionSidebar({
  numberOfVersions,
  versionUnderFocus,
  changeVersionUnderFocus,
  handleSidebarClose,
  sidebarOpen,
}) {
  return (
    <Drawer
      sx={{
        width: 240,
        flexShrink: 0,
        "& .MuiDrawer-paper": {
          width: 240,
          boxSizing: "border-box",
        },
      }}
      variant="persistent"
      anchor="left"
      open={sidebarOpen}
    >
      <Toolbar>
        <IconButton onClick={handleSidebarClose}>
          <ChevronLeftIcon />
        </IconButton>
      </Toolbar>
      <Divider />
      {numberOfVersions === 0 ? (
        <Typography>No versions</Typography>
      ) : (
        <List>
          {Array.from({ length: numberOfVersions }, (_, i) => i + 1).map(
            (el) => (
              <ListItem
                key={el}
                sx={{
                  fontWeight: el === versionUnderFocus ? "bold" : "light",
                  cursor: "pointer",
                }}
                onClick={() => {
                  changeVersionUnderFocus(el);
                }}
              >
                Version {el}
              </ListItem>
            )
          )}
        </List>
      )}
      <Divider />
    </Drawer>
  );
}
