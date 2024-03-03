// Validate
import images from "@/assets/images";
import { useFormik } from "formik";
import * as Yup from 'yup';
import '@/assets/css/apply/style.css';
import authApi from "@/api/modules/auth.api";
import { useDispatch } from "react-redux";
import authSlice from "@/redux/features/AuthSlice";
import userApi from "@/api/modules/user.api";
import toastNotification from "@/shares/helpers/ToastNotification";
import { STATUS } from "@/shares/constants/status.enum";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useGetProductsQuery } from "@/redux/features/ProductAPI";
import { useCountStore } from "@/zustand/testStore";
import { Row, Col } from 'antd';

const SignIn = () => {
  const dispatch = useDispatch();
  let navi = useNavigate();
  const [showPassword, setShowPassword] = useState(false);
  const {setUser, setToken} = useCountStore();

  const formik = useFormik({
    initialValues: {
        username: '',
        password: '',
    },
    validationSchema: Yup.object({
        username: Yup.string().required("Tên tài khoản không được để trống"),
        password: Yup.string().required("Mật khẩu không thể để trống"),
    }),
    onSubmit: async (values, { resetForm }) => {
      try {
        const response: any = await authApi.signIn({username: values.username, password: values.password});
        setToken(response.data);
        setUser();
        // dispatch(authSlice.actions.setToken(response.data));
        // const responseUserDetail = await userApi.searchUser();
        // dispatch(authSlice.actions.setUser(responseUserDetail.data));
        resetForm();
        navi('/');
        toastNotification(response.code, STATUS.TOAST_NOTIFICATION.SUCCESS, 1200);
      }
      catch(err: any) {
        switch(err.code) {
          case 1004: 
            toastNotification(err.code, STATUS.TOAST_NOTIFICATION.ERROR, 1200);
            break;
          default:
        }
      }
    }
  });

  return (
    <main className="bg-white my-[50px] rounded-[12px] p-[80px] max-sm:p-[20px] max-md:p-[40px]">
      <Row gutter={[{ xl: 80, md: 40, sm: 20, xs: 20 }, { xl: 80, md: 40,sm: 20, xs: 20 }]}>
        <Col  md={12} xs={24}>
          <section className="">
            <img 
              src={images.auth.bgAuth} 
              alt="auth" 
              className="rounded-[8px] w-full"
            />
          </section>
        </Col>
        <Col md={12}xs={24}>
          <section className="">
              <article>
                <h1 className="font-jambono-black text-[24px]">Chào mừng bạn đến với HuuNhan Shop</h1>
                <p className="text-[#A7ADB2]">Tham gia và tận hưởng trải nghiệm mua sắm trực tuyến tốt nhất</p>
              </article>
              <form onSubmit={formik.handleSubmit} className="mt-[25px]">
                <section>
                  <input 
                    type="text" 
                    placeholder="Tên tài khoản *"
                    name="username"
                    value={formik.values.username}
                    onChange={formik.handleChange}
                    className="fly-input-auth"
                  />
                  {formik.errors.username && (<p className="mt-[8px] text-[red]">{formik.errors.username}</p>)}
                </section>
                <section className="relative mt-[25px]">
                  <input
                    type={showPassword ? 'text' : 'password'}
                    name="password"
                    value={formik.values.password}
                    onChange={formik.handleChange}
                    placeholder="Mật khẩu *"
                    className="fly-input-auth"
                    autoComplete="true"
                  />
                  {formik.errors.password && (<p className="mt-[8px] text-[red]">{formik.errors.password}</p>)}
                  <img 
                    src={!showPassword ? images.auth.showPassword : images.auth.hidePassword} 
                    alt={showPassword ? 'show-password' : 'hide-password'}
                    className="absolute top-0 right-[12px] translate-y-[100%] cursor-pointer"
                    onClick={() => {setShowPassword(!showPassword)}}
                  />
                </section>
                <section>
                  <p className="text-right underline text-blue-600 my-[25px]">Forgot password ?</p>
                </section>
                <section>
                  <button
                    className={
                      `
                        fly-input-auth 
                        bg-button-color 
                        font-jambono-medium 
                        mb-[70px] 
                      hover:bg-primary-color 
                        transition duration-300 
                      hover:text-white
                      `
                    }
                  >
                    Đăng nhập
                  </button>
              </section>
              <section className="fly-flex-center justify-center">
                <span>Bạn chưa có tài khoản ?</span>
                <span className="ml-[10px] underline text-blue-600 font-bold">Đăng ký tài khoản</span>
              </section>
              </form>
          </section>
        </Col>
      </Row>
    </main>
  )
}

export default SignIn;