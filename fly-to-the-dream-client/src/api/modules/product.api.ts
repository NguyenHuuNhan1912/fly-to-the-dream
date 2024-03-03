import publicClient from "../client/public.client";
const productApi = {
    
    getAllProducts: () => {
        return publicClient.get('/products');
    }
}

export default productApi;