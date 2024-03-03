import images from "@/assets/images";
import '@/assets/css/apply/style.css';
import { useNavigate, Link } from "react-router-dom";
import { IUser , IUserTokenState  } from "@/shares/interfaces/auths/user.interface";
import { useSelector, useDispatch } from "react-redux";
import userApi from "@/api/modules/user.api";
import authSlice, { refreshToken } from "@/redux/features/AuthSlice";
import { parse } from "cookie";
import { useCountStore } from "@/zustand/testStore";
const Header = () => {

  const dispatch = useDispatch();
  // let isLogin: boolean = useSelector((state: IUserTokenState) => state.auth.isLogin);
  // let user: IUser | null = useSelector((state: IUserTokenState) => state.auth.userInformation);

  let {isLogin, user, removeToken, refreshToken} = useCountStore();

  const navigateAuth = useNavigate();

  const handleClickAuth = () => {
    navigateAuth('/signin');
  }

  const handleSignOut = async () => {
    let getToken: string = document.cookie;
    try {
      await userApi.signOut();
      removeToken();
    } 
    catch(err: any) {
      if(err?.response?.status === 401) {
        refreshToken({refreshToken: JSON.parse(parse(getToken).token).refreshToken, isSignOut: true});
      }
      console.log(`Error: ${err}`);
    }
  }
  
  return (
    <header>
      <section className='bg-secondary-color p-[10px]'>
        <section className='max-w-[1200px] mx-[auto] my-[0] flex justify-end'>
          <article className="fly-header-01">
            <img src={images.icon.lookUpOrders} alt="look" />
            <span className="ml-[5px]">Tra cứu đơn hàng</span>
          </article>
          <article className="fly-header-01 mx-[30px]">
            <img src={images.icon.call} alt="call" />
            <span className="ml-[5px]">Trợ giúp</span>
          </article>
          <article className="fly-header-01">
            <span>Ngôn ngữ: Tiếng Việt</span>
            <img src={images.commons.dropdown} alt="dropdown" className="ml-[5px]"/>
          </article>
        </section>
      </section>  
      <section className="bg-primary-color p-[20px] text-[14px]">
        <section className='max-w-[1200px] mx-[auto] fly-flex-center justify-between  text-white'> 
          <article className="flex">
            <Link to="/">
              <img src={images.commons.logo} alt="logo"/>
            </Link>
          </article>
          <article className="fly-flex-center bg-secondary-color p-[8px] rounded-[5px] mx-[25px]">
            <img src={images.commons.user} alt="user" />
            <span className="ml-[8px] font-bold">Danh mục</span>
            <img src={images.commons.dropdown} alt="dropdown" />
          </article>
          <article className="flex grow relative">
            <input 
              type="text" 
              placeholder="Bạn muốn tìm gì ? ..."
              className="text-[black] fly-input grow p-[11px]" 
            />
            <div className="fly-flex-center absolute bg-primary-color p-[5px] rounded-[5px] right-[5px] top-[5px]">
              <img src={images.commons.search} alt="search" />
              <span className="ml-[5px]">Tìm kiếm</span>
            </div>
          </article>
          <article className="flex mx-[25px] p-[8px] rounded-[10px] bg-button-color relative">
            <img src={images.icon.cart} alt="cart" />
            <div className="absolute top-[-25%] right-[-35%] bg-red-600 w-[25px] h-[25px] top-0 right-0 fly-flex-center justify-center rounded-[50px]">
              <p>10</p>
            </div>
          </article>
          {
            !isLogin ? 
              <article 
              className="fly-flex-center p-[8px] bg-secondary-color rounded-[5px] cursor-pointer"
              onClick={handleClickAuth}
              >
                <img src={images.commons.user} alt="user" />
                <span className="ml-[8px] font-bold">Đăng ký / Đăng nhập</span>
                <img src={images.commons.dropdown} alt="dropdown" />
              </article> :
                <article 
                className="fly-flex-center p-[8px] bg-secondary-color rounded-[5px] cursor-pointer"
                onClick={handleSignOut}
                >
                  <img src={images.commons.user} alt="user" />
                  {
                    user ? 
                    <span className="ml-[8px] font-bold">{user.username}</span> 
                    :
                    <></>
                  }
                  <img src={images.commons.dropdown} alt="dropdown" />
                </article> 
          }
        </section>
      </section>
    </header>
  )
}

export default Header;
