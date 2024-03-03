import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MainLayout from './layouts/main-layout/MainLayout';
import './assets/css/main.css';
import { Home, NotFound } from './pages/main-pages/index';
import {SignIn} from './pages/auth-page/index';
import { useDispatch, useSelector } from 'react-redux';
import authSlice, { refreshToken } from "@/redux/features/AuthSlice";
import { useEffect } from 'react';
import { parse } from 'cookie';
import userApi from './api/modules/user.api';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { QueryClient, QueryClientProvider } from 'react-query';
import { useCountStore } from './zustand/testStore';
const App = () => {
  const dispatch = useDispatch();
  const {setUser, setToken, refreshToken} = useCountStore();

  const handleSetToken = () => {
    let getToken: string = document.cookie;
    if(getToken) {
      setToken(JSON.parse(parse(getToken).token));
      setUser();
      
        // dispatch(authSlice.actions.setToken(JSON.parse(parse(getToken).token)));
        // const responseUserDetail = await userApi.searchUser();
        // dispatch(authSlice.actions.setUser(responseUserDetail.data));
      
    }
  }

  useEffect(() => {
    console.log("app-re-render");
    handleSetToken();
  }, []);

  
  return (
      <Router>
          <ToastContainer />
          <Routes>
            <Route element={<MainLayout />}>
              <Route index element={<Home />} />
              <Route path="signin" element={<SignIn />} />
            </Route>
            <Route path="*" element={<NotFound />} />
          </Routes>
      </Router>
  )
}

export default App;
