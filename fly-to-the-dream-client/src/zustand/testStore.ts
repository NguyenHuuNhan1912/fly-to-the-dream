import authApi from '@/api/modules/auth.api'
import userApi from '@/api/modules/user.api'
import { IUser } from '@/shares/interfaces/auths/user.interface'
import { parse } from 'cookie'
import { create } from 'zustand'
import tokenHelper from '@/shares/helpers/token'
type State = {
  count: number
  user: IUser | null,
  isLogin: boolean
}

type Actions = {
  increment: () => void
  decrement: () => void
  setToken: (token: string) => void
  setUser: (isSignOut?: boolean) => void
  removeToken: () => void
  refreshToken: (data:{refreshToken: string, isSignOut: boolean}) => void
  updateUser: (data: IUser) => void
}

const initialState = {
  count: 0,
  user: null,
  isLogin: false,
}

export const useCountStore = create<State & Actions>((set, get) => ({
  ...initialState,
  increment: () => set((state,) => ({ count: state.count + 1 })),
  decrement: () => set((state) => ({ count: state.count - 1 })),
  setToken: (token: string) => {
    tokenHelper.setToken(token);
  },
  removeToken: () => {
    tokenHelper.removeToken();
    set(() => ({
      isLogin: false,
      user: null
    }))
  },
  refreshToken: async data => {
    const res = await authApi.refreshToken(data.refreshToken);
    get().setToken(res.data);
    get().setUser(data.isSignOut);
  },

  updateUser: () => {

  },

  setUser: async (isSignOut: boolean = false) => {
    try {
      const res = await userApi.searchUser();
      set(() => ({
        user: res.data.data,
        isLogin: true,
      }))
      if(isSignOut) {
        await userApi.signOut();
        get().removeToken();
      }
    }
    catch(error: any) {
      if(error?.response?.status === 401) {
        get().refreshToken({refreshToken: JSON.parse(parse(document.cookie).token).refreshToken, isSignOut: false});
      }
    }
  },
}))