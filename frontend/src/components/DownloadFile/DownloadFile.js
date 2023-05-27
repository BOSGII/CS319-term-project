import { Container, Link } from "@mui/material";
import PictureAsPdfIcon from "@mui/icons-material/PictureAsPdf";
import axios from "axios";
import fileDownload from "js-file-download";

export default function DownloadFile({ fileName, url }) {
  const downloadFileFromServer = (url) => {
    axios
      .get(url, {
        responseType: "blob",
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
