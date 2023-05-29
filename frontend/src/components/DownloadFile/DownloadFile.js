import { Container, Link } from "@mui/material";
import PictureAsPdfIcon from "@mui/icons-material/PictureAsPdf";
import axios from "axios";
import fileDownload from "js-file-download";

export default function DownloadFile({ fileName, url }) {
  const sessionId = localStorage.getItem("sessionId");

  const downloadFileFromServer = (url) => {
    axios
      .get(url, {
        responseType: "blob",
        headers: {
          Authorization: `${sessionId}`,
        },
      })
      .then((response) => {
        console.log(response);
        fileDownload(response.data, fileName);
      });
  };

  return (
    <Container>
      <PictureAsPdfIcon />
      <Link
        onClick={() => {
          downloadFileFromServer(url);
        }}
        sx={{ cursor: "pointer" }}
      >
        {fileName}
      </Link>
    </Container>
  );
}
