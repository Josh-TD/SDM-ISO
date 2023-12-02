import { useNavigate } from "react-router-dom";

const Unauthorized = () => {
    const navigate = useNavigate();

    const goBack = useNavigate(-1);

    return(
        <div>
            <h1>Unauthorized</h1>
            <br />
            <p>You do not have access to the requested page.</p>
            <div className="flex-grow">
                <button onClick={goBack}>Go Back</button>
            </div>
        </div>
    );
}