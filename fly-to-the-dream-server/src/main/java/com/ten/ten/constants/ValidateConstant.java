package com.ten.ten.constants;

public class ValidateConstant {
    public static final class AUTH {
        public static final Integer LENGTH_USERNAME = 5;

        public static final Integer LENGTH_PASSWORD = 8;

    }

    public static final class REGEX {
        public static final String PHONE_NUMBER = "^(0|84|\\+84)[35789]\\d{8}$";

        public static final String EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
    }

}