export interface IUser {
    username: string,
    roles: Array<IRole>
    email: string,
    gender: boolean
    dateOfBirth: Date,
    phoneNumber: string,
}

export interface IRole {
    description: string,
    role: string,
}


export interface IToken {
    accessToken: string,
    refreshToken: string,
}

export interface ISignInRequest {
    username: string,
    password: string,
}


export interface IUserTokenState {
    auth: IUserTokenInit
}
export interface IUserTokenInit {
    userInformation: IUser | null;
    isLogin: boolean
}

  