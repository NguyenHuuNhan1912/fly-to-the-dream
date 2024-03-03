import axios from "axios";
import { BASE_URL } from "@/shares/constants/api.enum";
import { parse } from "cookie";

const baseURL = BASE_URL.BASE_URL_API;

const privateClient = axios.create({
  baseURL
});


privateClient.interceptors.request.use(async (config: any) => {
  let getToken: string = document.cookie;
  return {  
    ...config,
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${JSON.parse(parse(getToken).token).accessToken}`
    },
  };
});



export default privateClient;