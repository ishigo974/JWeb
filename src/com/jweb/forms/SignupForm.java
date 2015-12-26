package com.jweb.forms;

import com.jweb.beans.User;
import com.jweb.dao.DBErrors;
import com.jweb.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by menigo_m on 16/12/15.
 */
public final class SignupForm {
    private static final String email_input = "email";
    private static final String password_input = "password";
    private static final String confirmation_input = "confirmation";
    private static final String user_name = "name";
    private static final String user_news = "news";

    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public Map<String, String> getErrors() {
        return errors;
    }

    /**
     * Set an error in the errors map
     * @param champ String
     *              The invalid field
     * @param message String
     *                The error output
     */
    private void setError(String champ, String message) {
        errors.put(champ, message);
    }

    public String getResult() {
        return result;
    }

    /**
     * Create a new user in database if the fields are valid
     * @param request HttpServletRequest
     *                The request the user sent
     * @return The new user
     */
    public User signupUser(HttpServletRequest request) {
        String email = getValue(request, email_input);
        String password = getValue(request, password_input);
        String confirmation = getValue(request, confirmation_input);
        String userName = getValue(request, SignupForm.user_name);
        boolean newsLetter;
        if (getValue(request, SignupForm.user_news) != null)
            newsLetter = getValue(request, SignupForm.user_news).equals("checked");
        else
            newsLetter = false;

        User user = new User();
        user.setNews(newsLetter);
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
            setError(confirmation_input, e.getMessage());
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            setError("Encryption", e.getMessage());
        }
        if (md5 != null && password != null)
            user.setPassword((new HexBinaryAdapter()).marshal(md5.digest(password.getBytes())));
        try {
            name_validator(userName);
        } catch (Exception e) {
            setError(SignupForm.user_name, e.getMessage());
        }
        user.setName(userName);
        if (errors.isEmpty()) {
            try {
                UserDao db = new UserDao();
                db.setUser(user);
            } catch (DBErrors e) {
                setError(SignupForm.user_name, e.getMessage());
                result = "Sign up failure.";
                return null;
            }
            result = "You have signed up successfully.";
        } else {
            result = "Sign up failure.";
            return null;
        }
        return user;
    }

    /**
     * Check if the mail is valid
     * @param email String
     *              The mail value
     */
    private void email_validator(String email) throws Exception {
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Invalid email");
            }
        } else {
            throw new Exception("Invalid email");
        }
    }

    /**
     * Check if the password and confirmation fields are valid
     * @param password String
     *                 The password value
     * @param confirmation String
     *                 The confirmation value
     */
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

    /**
     * Check if the name is at least 3 characters long
     * @param name String
     *             The name value
     */
    private void name_validator(String name) throws Exception {
        if (name == null || name.length() < 3) {
            throw new Exception("Your name must be at least 3 characters long");
        }
    }

    /**
     * Get the value of a given field in the request
     * @param request HttpServletRequest
     *                User's request
     * @param field String
     *              The field of which you want the value
     * @return the value of the form field
     */
    private static String getValue(HttpServletRequest request, String field) {
        String value = request.getParameter(field);
        if (value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value.trim();
        }
    }
}
