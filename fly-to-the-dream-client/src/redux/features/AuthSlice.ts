import authApi from '@/api/modules/auth.api';
import userApi from '@/api/modules/user.api';
import {createAsyncThunk, createSlice} from '@reduxjs/toolkit';
import { IUserTokenInit } from '@/shares/interfaces/auths/user.interface';
import toastNotification from '@/shares/helpers/ToastNotification';
import { STATUS } from '@/shares/constants/status.enum';

const initialState: IUserTokenInit = {
        userInformation: null,
        isLogin: false,
      
    }

const authSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {

        setToken: (state, action) => {
            document.cookie = `token=${JSON.stringify(action.payload)}; expires=${new Date('2025-1-29 10:00:00').toUTCString()}`;
            state.isLogin = true;
        },
        removeToken: (state, action) => {
            document.cookie = `token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
            state.isLogin = false;
            state.userInformation = null;
        },
        setUser: (state, action) => {
            const { data } = action.payload;
            state.userInformation = {...data};
        },
    },
    extraReducers: builder => {
        builder
            .addCase(refreshToken.rejected, (state, action) => {
                if(action.payload.code === 1009) {
                    toastNotification(action.payload.code, STATUS.TOAST_NOTIFICATION.ERROR, 1500);
                    document.cookie = `token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
                    state.isLogin = false;
                    state.userInformation = null;
                }
            })
            .addCase(refreshToken.fulfilled, (state, action) => {
            })
    }
});

export const getUser: any = createAsyncThunk('getUser/searchUser', async (isSignOut: boolean = false, thunkApi) => {
    try {
        const responseUserDetail = await userApi.searchUser();
        thunkApi.dispatch(authSlice.actions.setUser(responseUserDetail.data));
        if(isSignOut) {
            thunkApi.dispatch(signOut());
        }
    }
    catch(error: any) {
        console.log(`Error: ${error}`);
        return thunkApi.rejectWithValue(error.message);
    }
});

export const refreshToken: any = createAsyncThunk('token/refreshToken', async (data: any, thunkApi) => {
    try {
        const res = await authApi.refreshToken(data.refreshToken);
        document.cookie = `token=${JSON.stringify(res.data)}; expires=${new Date('2025-1-29 10:00:00').toUTCString()}`;
        thunkApi.dispatch(getUser(data.signOut));
        return res.data;
    }
    catch(error: any) {
        return thunkApi.rejectWithValue(error);
    }
})

export const signOut: any = createAsyncThunk('auth/signOut', async (_, thunkApi) => {
    try{
        const res = await userApi.signOut();
        thunkApi.dispatch(authSlice.actions.removeToken(""));
        return res.data;
    }
    catch(error: any) {
        console.log(`Error: ${error}`);
        return thunkApi.rejectWithValue(error);
    }
})


export default authSlice;