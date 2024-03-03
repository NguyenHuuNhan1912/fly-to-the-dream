import authApi from "@/api/modules/auth.api";
import userApi from "@/api/modules/user.api";
import authSlice from "@/redux/features/AuthSlice";
import { createAsyncThunk } from "@reduxjs/toolkit";
import { parse } from "cookie";
import { useDispatch } from "react-redux";


const useRefreshToken = (signOut: boolean = false) => {
    const dispatch = useDispatch();
    
    const refreshTokenApi = async () => {
        try {
          let getToken: string = document.cookie;

          const { refreshToken } = JSON.parse(parse(getToken).token);
          const res = await authApi.refreshToken(refreshToken);
          dispatch(authSlice.actions.setToken(res.data));

          const responseUserDetail = await userApi.searchUser();
          dispatch(authSlice.actions.setUser(responseUserDetail.data));

          if(signOut) {
            await userApi.signOut();
            dispatch(authSlice.actions.signOut(null));
          }
        }
        catch(err) {
          console.log(`Error: ${err}`);
        }
    }

    return refreshTokenApi;
}


export default useRefreshToken;


