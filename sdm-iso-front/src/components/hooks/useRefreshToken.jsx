import axios from "../api/axios";
import useAuth from "./useAuth";


const useRefreshToken = () => {
    const { setAuth } = useAuth();

    const refresh = async () => {
        const response = await axios.post("/api/auth/authenticate", {
            withCredentials: true
        });
        setAuth(prev => {
            console.log(JSON.stringify(prev));
            console.log(response.data.access_token);
            return { ...prev, access_token: response.data.accessToken };
        })
        return response.data.access_token;
    }
    return refresh;
};

export default useRefreshToken;