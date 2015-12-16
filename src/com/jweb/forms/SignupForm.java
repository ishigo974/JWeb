package com.jweb.forms;

import com.jweb.beans.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by menigo_m on 16/12/15.
 */
public final class SignupForm {
    private static final String email_input = "email";
    private static final String password_input = "password";
    private static final String confirmation_input = "confirmation";
    private static final String name = "name";

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

    public User signupUser(HttpServletRequest request) {
        String email = getValue(request, email_input);
        String password = getValue(request, password_input);
        String confirmation = getValue(request, confirmation_input);
        String name = getValue(request, SignupForm.name);
        User user = new User();
        try {
            email_validator(email);
        } catch (Exception e) {
            setError(email_input, e.getMessage());
        }
        user.setEmail(email);
        try {
            password_validator(password, confirmation);
        } catch (Exception e) {
            setError(password_input, e.getMessage());
            setError(confirmation_input, null);
        }
        user.setPassword(password);
        try {
            name_validator(name);
        } catch (Exception e) {
            setError(SignupForm.name, e.getMessage());
        }
        user.setName(name);
        if (errors.isEmpty()) {
            result = "Sign up success";
        } else {
            result = "Sign up failure";
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

    private void password_validator(String password, String confirmation) throws Exception {
        if (password != null && confirmation != null) {
            if (!password.equals(confirmation)) {
                throw new Exception("Password and confirmation do not match");
            } else if (password.length() < 3) {
                throw new Exception("Your password must be at least 3 characters long");
            }
        } else {
            throw new Exception("Invalid password");
        }
    }

    private void name_validator(String name) throws Exception {
        if (name != null && name.length() < 3) {
            throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
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