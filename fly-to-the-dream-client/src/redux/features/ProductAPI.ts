import { IProduct } from '@/shares/interfaces/products/product.interface';
import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';
import { ResponseResult } from '@/shares/interfaces/respose-result.interface';
export const productAPI = createApi({
  // Tương tự tên Slice khi tạo Slice thông thường
  reducerPath: 'productAPI',

  // Cấu hình chung cho tất cả request
  baseQuery: fetchBaseQuery({
    baseUrl: 'http://localhost:8080/api/v1/',
  }),
  
  // Các endpoints (lệnh gọi request)
  endpoints: (builder) => ({
    getProducts: builder.query<ResponseResult, void>({
        query: () => `products`
    }),
    addNewProduct: builder.mutation<ResponseResult, {name: string, price: number}>({
        query: (body) => {
          return {
            url: '/addNewProduct',
            method: 'POST',
            body
          }
        }
    })
  }),
});

export const { useGetProductsQuery, useAddNewProductMutation } = productAPI;