import { createSlice } from '@reduxjs/toolkit';
import type { PayloadAction } from '@reduxjs/toolkit';

import { RootState } from '../store';

// Define a type for the slice state

// Define the initial state using that type
const initialState: ICart = {
    id: '',
    cartDetails: [],
    sum: 0,
    totalPrice: 0,
};

export const cartSlice = createSlice({
    name: 'cart',
    // `createSlice` will infer the state type from the `initialState` argument
    initialState,
    reducers: {
        setCart: (state, action: PayloadAction<ICart>) => {
            state.cartDetails = action.payload.cartDetails;
            state.id = action.payload.id;
            state.sum = action.payload.sum;
            state.totalPrice = action.payload.totalPrice;
        },

        setCartDetail: (state, action: PayloadAction<ICartDetail[]>) => {
            state.cartDetails = action.payload;
        },
    },
});

export const { setCart, setCartDetail } = cartSlice.actions;

// Other code such as selectors can use the imported `RootState` type
export const getCartSelector = (state: RootState) => state.cartSlice;

export default cartSlice.reducer;
