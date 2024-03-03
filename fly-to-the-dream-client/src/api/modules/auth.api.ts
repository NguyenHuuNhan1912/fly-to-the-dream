import publicClient from '../client/public.client';
const authApi = {
    
    signIn: (params: any) => {
        return publicClient.post(`/sign-in`, params);
    }, 

    refreshToken: (refreshToken: string) => {
        return publicClient.post('/refresh-token', refreshToken);
    }
}

export default authApi;