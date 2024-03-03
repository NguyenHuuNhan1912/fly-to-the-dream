import authSlice from '@/redux/features/AuthSlice';
import { IToken, IUserTokenState } from '@/shares/interfaces/auths/user.interface';
import { useCountStore } from '@/zustand/testStore';
import { parse } from 'cookie';
import { useEffect, useId, useState, useContext} from 'react';
import { useDispatch, useSelector } from 'react-redux';

const Home = () => {
  const [token, setToken] = useState<IToken>();
  const {count, increment, decrement, isLogin} = useCountStore();
  useEffect(() => {
    let getToken: string = document.cookie;
    if(getToken) {
      setToken(JSON.parse(parse(getToken).token));
    }
  }, []);
  return (
    <div>
      {
        isLogin ?
        <>
          <h1>Access Token: {token?.accessToken}</h1>
          <h1>Refresh Token: {token?.refreshToken}</h1>
        </>
        :
        <>
         <h1>{count}</h1>
          <h1>You need sign in !!!</h1>
          <button onClick={increment}>Plus</button>
          <button onClick={decrement}>Minus</button>
        </>
      }
    </div>
  )
}

export default Home;

