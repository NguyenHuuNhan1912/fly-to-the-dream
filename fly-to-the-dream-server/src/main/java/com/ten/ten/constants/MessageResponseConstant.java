package com.ten.ten.constants;

public class MessageResponseConstant {
    public static final String SERVER_ERROR = "Server Error !";

    public static final class AUTH {
        public static final String SUCCESS = "Logged in successfully !";

        public static final String INVALID_USERNAME = "Invalid username(username must be greater than or equal to 5 characters, and cannot be empty)";

        public static final String INVALID_PASSWORD = "Invalid password(password must be greater than or equal to 8 characters, and cannot be empty and password include uppercase and lowercase and digit and specialchar)";

        public static final String WRONG_USERNAME_OR_PASSWORD = "Login failed(Wrong username or password)";

        public static final String INVALID_PHONE_NUMBER = "Invalid phone number(Phone number cannot be empty and includes valid numbers in Vietnam and only 10 numeric characters are allowed)";

        public static final String ACCOUNT_EXIST = "The account already exists in the system !";

        public static final String INVALID_EMAIL = "Invalid Email !";

        public static final String UNAUTHORIZED = "Unauthorized !";

        public static final String EXPIRED_REFRESH_TOKEN = "Refresh token was expired. Please make a new signin request !";

        public static final String NOT_FOUND_REFRESH_TOKEN = "Not found refresh token";

        public static final String REFRESH_TOKEN_SUCCESS = "Refresh token successfully !";



    }

}
