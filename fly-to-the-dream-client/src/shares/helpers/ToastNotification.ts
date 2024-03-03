import { Bounce, Flip, Slide, TypeOptions, Zoom, toast } from 'react-toastify';
import { SYSTEM_MSG } from '../constants/system-message.enum';

const toastNotification = (code: number, type: TypeOptions, time: number) => {
    return (
        toast(
            SYSTEM_MSG[code], 
            {
                type,
                position: "top-center",
                autoClose: time,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
                theme: "light",
                transition: Slide,
            }
        )
    )
}

export default toastNotification;