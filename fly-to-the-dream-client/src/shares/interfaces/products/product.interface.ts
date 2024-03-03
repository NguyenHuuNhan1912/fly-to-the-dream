export interface IProduct {
    productDetail: IproductDetail | null,
}

export interface IproductDetail {
    id: string,
    name: string,
    price: number
}