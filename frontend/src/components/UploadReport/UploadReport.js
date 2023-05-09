import React, { useState } from 'react';
import { Box, Button, Typography } from '@mui/material';
import {styled} from '@mui/material/styles'

const UploadBox = styled(Box)(({ theme }) => ({
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    gap: theme.spacing(2),
}));
  
const DropZone = styled(Box)(({ theme, selectedFile }) => ({
    width: '100%',
    height: 200,
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    border: `2px dashed ${theme.palette.primary.main}`,
    borderRadius: theme.shape.borderRadius,
    backgroundColor: selectedFile ? theme.palette.grey[100] : theme.palette.background.paper,
    cursor: 'pointer',
}));

const SelectedFileBox = styled(Box)(({ theme }) => ({
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    gap: theme.spacing(1),
}));

export default function UploadReport() {
    const [selectedFile, setSelectedFile] = useState(null);
    const [isSubmitting, setIsSubmitting] = useState(false);
  
    const handleDrop = (event) => {
      event.preventDefault();
      const file = event.dataTransfer.files[0];
      setSelectedFile(file);
    };
  
    const handleDelete = () => {
      setSelectedFile(null);
    };
  
    const handleSubmit = () => {
      setIsSubmitting(true);
      // TODO: Implement file submission logic
    };
  
    const handleCancel = () => {
      setSelectedFile(null);
      setIsSubmitting(false);
    };
  
    const handleDragOver = (event) => {
      event.preventDefault();
    };
  
    const renderDropZone = () => (
      <DropZone selectedFile={selectedFile} onDrop={handleDrop} onDragOver={handleDragOver}>
        {selectedFile ? (
          <SelectedFileBox>
            <Typography>{selectedFile.name}</Typography>
            <Button onClick={handleDelete}>
              Delete
            </Button>
          </SelectedFileBox>
        ) : (
          <Typography>Drag and drop a file here or click to select</Typography>
        )}
      </DropZone>
    );
  
    const renderButtons = () => (
      <Box sx={{ display: 'flex', gap: 2 }}>
        {selectedFile && (
          <>
            <Button onClick={handleSubmit} disabled={isSubmitting}>
              Submit
            </Button>
            <Button onClick={handleCancel}>
              Change
            </Button>
          </>
        )}
      </Box>
    );
  
    return (
      <UploadBox>
        {renderDropZone()}
        {renderButtons()}
      </UploadBox>
    );
};