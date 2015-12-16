package com.jweb.forms;

import com.jweb.beans.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by menigo_m on 16/12/15.
 */
public class LoginForm {
    private static final String email_input = "email";
    private static final String password_input = "password";

    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public Map<String, String> getErrors() {
        return errors;
    }

    private void setError(String champ, String message) {
        errors.put(champ, message);
    }

    public String getResult() {
        return result;
    }

    public User loginUser(HttpServletRequest request) {
        String email = getValue(request, email_input);
        String password = getValue(request, password_input);
        User user = new User();
        try {
            email_validator(email);
        } catch (Exception e) {
            setError(email_input, e.getMessage());
        }
        user.setEmail(email);
        try {
            password_validator(password);
        } catch (Exception e) {
            setError(password_input, e.getMessage());
        }
        user.setPassword(password);
        if (errors.isEmpty()) {
            result = "Login success";
        } else {
            result = "Login failure";
        }
        return user;
    }

    private void email_validator(String email) throws Exception {
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Invalid email");
            }
        } else {
            throw new Exception("Invalid email");
        }
    }

    private void password_validator(String password) throws Exception {
        if (password == null || password.length() < 3) {
            throw new Exception("Invalid password");
        }
    }

    private static String getValue(HttpServletRequest request, String field) {
        String value = request.getParameter(field);
        if (value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value.trim();
        }
    }
}
