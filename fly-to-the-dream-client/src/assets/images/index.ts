import {lookup, call, dropdown, logo, user, search, cart, bgAuth, showPassword, hidePassword} from './export';
const images = {
    icon: {
        lookUpOrders: lookup,
        call: call,
        cart: cart,
    },
    commons: {
        dropdown: dropdown,
        logo: logo,
        user: user,
        search: search
    }, 
    auth: {
        bgAuth: bgAuth,
        showPassword: showPassword,
        hidePassword: hidePassword,
    }
}

export default images;