import { configureStore } from "@reduxjs/toolkit";
import authSlice from "./features/AuthSlice";
import { productAPI } from "./features/ProductAPI";
import { setupListeners } from '@reduxjs/toolkit/query'
import productSlice from "./features/ProductSlice";
export const store = configureStore({
    reducer: {
        auth: authSlice.reducer,
        [productAPI.reducerPath]: productAPI.reducer,
        product: productSlice.reducer,
    },
    middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(productAPI.middleware),
});
setupListeners(store.dispatch);