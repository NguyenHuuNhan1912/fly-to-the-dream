import productApi from "@/api/modules/product.api";
import { IProduct } from "@/shares/interfaces/products/product.interface";
import { memo, useEffect, useRef } from "react";
import { useQuery, useMutation, QueryClient, QueryClientProvider } from 'react-query';
interface ChildProps {
    handleIncrease : () => void;
}
const Content: React.FC<ChildProps> = ({handleIncrease}) => {

  return (
    <div>
      <button>React Query</button>
    </div>
  )
}

export default memo(Content); 