import { TypeOptions } from "react-toastify";

interface ToastNotificationType  {
    SUCCESS: TypeOptions;
    INFO: TypeOptions;
    WARNING: TypeOptions;
    ERROR: TypeOptions;
    DEFAULT: TypeOptions;
};

export const STATUS = {
    TOAST_NOTIFICATION:<ToastNotificationType>  {
        SUCCESS: 'success',
        INFO: 'info',
        WARNING: 'warning',
        ERROR: 'error',
        DEFAULT: 'default',
    }
}