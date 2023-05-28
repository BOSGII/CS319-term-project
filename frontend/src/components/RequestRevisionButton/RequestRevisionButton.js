import { Button, Dialog, Typography } from "@mui/material";
import Upload from "../Upload/Upload";
import { useState } from "react";
import axios from "axios";

export default function RequestRevisionButton({ versionId, refreshVersion }) {
  const [open, setOpen] = useState(false);
  const [comments, setComments] = useState([]);
  const [showInput, setShowInput] = useState(false);
  const [newComment, setNewComment] = useState("");
  const [editIndex, setEditIndex] = useState(-1);
  const [editedComment, setEditedComment] = useState("");

  const handleAddComment = () => {
    if (newComment.trim() !== "") {
      setComments((prevComments) => [...prevComments, newComment]);
      setNewComment("");
    }
    setShowInput(!showInput);
  };

  const handleChangeComment = (index, comment) => {
    setShowInput(true); // Reopen the input field
    setEditIndex(index); // Set the index of the comment being edited
    setEditedComment(comment);
  };

  const handleSaveComment = (index, updatedComment) => {
    setShowInput(false); // Reopen the input field
    setEditIndex(-1); // Set the index of the comment being edited
    setEditedComment("");
    setComments((prevComments) => {
      const updatedComments = [...prevComments];
      updatedComments[index] = updatedComment;
      return updatedComments;
    });
  };

  const handleDeleteComment = (index) => {
    setComments((prevComments) => {
      const updatedComments = [...prevComments];
      updatedComments.splice(index, 1);
      return updatedComments;
    });
  };

  const handleSubmit = (file) => {
    
    if (!file && comments.length === 0) {
      return;
    }

    let formData = new FormData();
    if (file) {
      formData.append("feedback", file);
    }
    if (comments) {
      formData.append("comments", comments);
    }
    const sessionId = localStorage.getItem('sessionId');
    axios
      .post(`http://localhost:8080/api/versions/${versionId}`, formData, {
        headers: {
          Authorization: `${sessionId}`
        }
      })
      .then((response) => {
        refreshVersion();
      })
      .catch((error) => {
        console.log(error);
      })
      .finally(() => {
        setOpen(false);
      });
  };

  return (
    <>
      <Button
        onClick={() => {
          setOpen(true);
        }}
      >
        Request Revision
      </Button>
      <Dialog
        open={open}
        onClose={() => {
          setOpen(false);
        }}
      >
        <Button
          onClick={() => {
            setShowInput(true);
            setEditIndex(-1);
          }}
        >
          Add comment
        </Button>
        <ul>
          {comments?.map((comment, index) => (
            <li key={index}>
              {index === editIndex ? (
                <>
                  <input
                    type="text"
                    value={editedComment}
                    onChange={(e) => setEditedComment(e.target.value)}
                  />
                  <Button
                    onClick={() => handleSaveComment(index, editedComment)}
                  >
                    Save
                  </Button>
                </>
              ) : (
                <>
                  {comment}
                  <Button onClick={() => handleChangeComment(index, comment)}>
                    Change
                  </Button>
                </>
              )}
              <Button
                onClick={() => {
                  handleDeleteComment(index);
                }}
              >
                Delete
              </Button>
            </li>
          ))}
        </ul>
        {showInput && editIndex === -1 && (
          <div>
            <input
              type="text"
              value={newComment}
              onChange={(el) => setNewComment(el.target.value)}
            />
            <Button onClick={handleAddComment}>Add</Button>
          </div>
        )}
        <Typography>Upload feedback</Typography>
        <Upload
          acceptedFileTypes={["PDF"]}
          handleSubmit={handleSubmit}
          handleCancel={() => {
            setOpen(false);
          }}
        />
      </Dialog>
    </>
  );
}
