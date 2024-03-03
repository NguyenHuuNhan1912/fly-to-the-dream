import {createSlice} from '@reduxjs/toolkit';
import { IProduct } from '@/shares/interfaces/products/product.interface';
import { productAPI } from './ProductAPI';
const initialState: IProduct = {
    productDetail: null,
};

const productSlice = createSlice({
    name: 'products',
    initialState,
    reducers: {

        setProduct: (state, action) => {
            state.productDetail = action.payload;
        },
    },
    extraReducers: builder => {
        builder.addMatcher(productAPI.endpoints.getProducts.matchFulfilled, (state, action) => {
            state.productDetail = action.payload.data;
          });
    }
});




export default productSlice;