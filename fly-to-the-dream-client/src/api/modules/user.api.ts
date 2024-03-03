import privateClient from "../client/private.client";
const userApi = {
    
    searchUser: () => {
        return privateClient.get(`/search-user`);
    }, 
    
    signOut: () => {
        return privateClient.get("/sign-out");
    }
}

export default userApi;