import {Drawer, List, ListItem, Divider, Toolbar, Typography} from "@mui/material";
import { useEffect } from "react";

export default function SubmissionSidebar({ numberOfVersions, versionUnderFocus, setVersionUnderFocus }) {
  useEffect(() => {
    setVersionUnderFocus(numberOfVersions);
  }, [setVersionUnderFocus, numberOfVersions]);

  return (
    <Drawer
        sx={{
          width: 240,
          flexShrink: 0,
          '& .MuiDrawer-paper': {
            width: 240,
            boxSizing: 'border-box',
          },
        }}
        variant="permanent"
        anchor="left"
      >
        <Toolbar />
        <Divider />
          {numberOfVersions === 0 ? 
          <Typography>No versions</Typography> : 
          <List>
            {Array.from({length: numberOfVersions}, (_, i) => i + 1).map(el => (
              <ListItem key={el} sx={el === versionUnderFocus ? {fontWeight: "bold"} : {fontWeight: "light"}} onClick={() => {setVersionUnderFocus(el);}}>
                Version {el}
              </ListItem>))}
          </List>}
        <Divider />
      </Drawer>
  );
}
