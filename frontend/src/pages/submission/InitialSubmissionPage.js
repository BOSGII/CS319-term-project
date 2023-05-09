import { useContext } from "react";
import { useSearchParams } from "react-router-dom";
import { UserContext } from "../../contexts/UserContext";
import { useFetch } from "../../hooks/useFetch";
import { Container, Typography } from "@mui/material";
import UploadReport from '../../components/UploadReport/UploadReport';

export default function InitialSubmissionPage(){
    const {user} = useContext(UserContext);
    const studentId = user.id;

    const [searchParams] = useSearchParams();
    const internshipType = searchParams.get("internshipType");

    const {data, isPending, error} = useFetch(`api/internships?studentId=${studentId}&internshipType=${internshipType}`);

    return(
        <Container>
            <Typography>
                Submission Page
            </Typography>
            {error && <div>{error}</div>}
            {isPending && <div>loading...</div>}
            {data && <UploadReport internshipId={data.id}/>}
        </Container>
    )
}