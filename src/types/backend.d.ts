export {};
// https://bobbyhadz.com/blog/typescript-make-types-global#declare-global-types-in-typescript

declare global {
    interface IRequest {
        url: string;
        method: string;
        body?: { [key: string]: any };
        queryParams?: any;
        useCredentials?: boolean;
        headers?: any;
        nextOption?: any;
    }

    interface IBackendRes<T> {
        error?: string | string[];
        message: string;
        statusCode: number;
        data?: T;
    }

    interface ILogin {
        access_token?: string;
        user: IUser;
    }

    interface IMeta {
        page: number;
        pageSize: number;
        pages: number;
        total: number;
    }

    interface IModelPaginate<T> {
        meta: IMeta;
        result: T[];
    }

    interface IUploadFile {
        fileName: string;
        uploadedAt: string;
    }

    interface IDashBoard {
        countUser: number;
        countProduct: number;
        countOrder: number;
    }

    interface IRole {
        id: 'USER' | 'ADMIN';
        name: string;
        description: string;
    }

    interface IUser {
        id: string;
        fullName: string;
        email: string;
        role: IRole;
        access_token?: string;
    }

    interface IProduct {
        id?: string;
        name: string;
        price: string;
        image: string;
        detailDesc: string;
        shortDesc: string;
        quantity: number;
        sold?: string;
        factory?: string;
        target?: string;
    }

    interface ICart {
        id: string;
        product: IProduct;
        cartDetails: ICartDetail[];
        sum: number;
        totalPrice: number;
    }

    interface ICartDetail {
        id: string;
        product: IProduct;
        quantity: number;
        price: number;
    }

    interface IOrder {
        id: string;
        product?: IProduct;
        orderDetails?: IOrderDetail[];
        totalPrice: number;
        user?: IUser;
        receiverName: string;
        receiverAddress: string;
        receiverPhone: string;
        status: 'PENDING|STATUS|SUCCESS';
        createdAt: string;
        updatedAt: string;
        createdBy: string;
        updatedBy: string;
    }

    interface IOrderDetail {
        id: string;
        product: IProduct;
        quantity: number;
        price: number;
    }
}
