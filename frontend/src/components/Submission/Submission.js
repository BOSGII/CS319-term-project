// import { useCallback, useState} from "react";
// import { useDropzone } from "react-dropzone";

export default function Submission({ internship }) {
    /*
    const [file, setFile] = useState(null);
  
    const handleFileChange = (event) => {
      setFile(event.target.files[0]);
    };
  
    const handleSubmit = (event) => {
      event.preventDefault();
      //onSubmit(file);
    };
  
    const onDrop = useCallback((acceptedFiles) => {
      // Set the dropped file as the current file
      setFile(acceptedFiles[0]);
    }, []);
  
    const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });
  
    */

    /*
    if (internship.number) {
      // Render the existing submission
      return (
        <div>
          <p>You have already submitted a file:</p>
          <p>{submission.fileName}</p>
          <form onSubmit={handleSubmit}>
            <input type="file" onChange={handleFileChange} />
            <button type="submit">Update Submission</button>
          </form>
        </div>
      );
    } else {
      // Render the file upload form with drag and drop
      return (
        <div {...getRootProps()}>
          <input {...getInputProps()} />
          {isDragActive ? (
            <p>Drop the file here ...</p>
          ) : (
            <p>Drag 'n' drop a file here, or click to select a file</p>
          )}
          {file && <p>Selected file: {file.name}</p>}
          <form onSubmit={handleSubmit}>
            <button type="submit" disabled={!file}>Submit File</button>
          </form>
        </div>
      );
    }
    */
    return <div>Submission Component</div>
  } 