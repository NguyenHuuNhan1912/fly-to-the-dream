import Header from "@/components/header/Header";
import Footer from "@/components/footer/Footer";
import { Outlet } from 'react-router-dom';
import Content from "@/components/content/Content";
import { createContext, useCallback, useState ,useContext} from "react";  
export const dataContext = createContext("");

const MainLayout = () => {
  const [count, setCount] = useState(0);
  let dataContext2 = useContext(dataContext);
  const handleIncrease = () => {
    console.log("call app");
    console.log(dataContext2)
    setCount((value) => value+1);
  }

  return (
    <dataContext.Provider  value={"a"}>
      <main>
          <Header/>
          <div className="bg-background">
              <div>
                <Content
                    handleIncrease={handleIncrease}       
                />
                <button onClick={handleIncrease}>Click</button>
              </div>
              <div className="max-w-[1200px] mx-[auto] my-[0] border border-background">
                  <Outlet />
              </div>
          </div>
          <Footer/>
      </main>
    </dataContext.Provider>
  )
}

export default MainLayout;