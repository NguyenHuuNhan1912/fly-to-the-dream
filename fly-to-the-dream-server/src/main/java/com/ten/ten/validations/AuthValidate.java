package com.ten.ten.validations;

import com.ten.ten.constants.ValidateConstant;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AuthValidate {
    public Boolean isValidUsername(String username) {
        Boolean isEmpty = username.isEmpty();
        Boolean isLengthAndTrim = username.trim().length() >= ValidateConstant.AUTH.LENGTH_USERNAME;
        return (!isEmpty && isLengthAndTrim) ? true : false;
    }

    public Boolean isValidPassword(String password) {
        Boolean isEmpty = password.isEmpty();
        Boolean hasUppercase = false;
        Boolean hasLowercase = false;
        Boolean hasDigit = false;
        Boolean hasSpecialChar = false;
        if(password.length() < ValidateConstant.AUTH.LENGTH_PASSWORD) return false;
        for (char ch : password.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecialChar = true;
            }
        }
        return hasUppercase && hasLowercase && hasDigit && hasSpecialChar && !isEmpty;
    }

    public Boolean isValidPhoneNumber(String phoneNumber) {
        if(phoneNumber.isEmpty()) return false;
        Pattern pattern = Pattern.compile(ValidateConstant.REGEX.PHONE_NUMBER);
        Matcher matcher = pattern.matcher(phoneNumber.trim().replaceAll("\\s+", ""));
        return matcher.matches();
    }

    public Boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(ValidateConstant.REGEX.EMAIL);
        Matcher matcher = pattern.matcher(email.trim());
        return matcher.matches();
    }
}
