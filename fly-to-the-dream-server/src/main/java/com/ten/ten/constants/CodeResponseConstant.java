package com.ten.ten.constants;

public class CodeResponseConstant {
    public static final Integer SERVER_ERROR = 500;
    public static final class AUTH {
        public static final Integer SUCCESS = 1001;

        public static final Integer INVALID_USERNAME = 1002;

        public static final Integer INVALID_PASSWORD = 1003;

        public static final Integer WRONG_EMAIL_OR_PASSWORD = 1004;

        public static final Integer INVALID_PHONE_NUMBER = 1005;

        public static final Integer ACCOUNT_EXIST = 1006;

        public static final Integer INVALID_EMAIL = 1007;
        public static final Integer UNAUTHORIZED = 1008;

        public static final Integer EXPIRED_REFRESH_TOKEN = 1009;

        public static final Integer NOT_FOUND_REFRESH_TOKEN = 1010;

        public static final Integer REFRESH_TOKEN_SUCCESS = 1011;


    }
}
