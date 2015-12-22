package com.jweb.forms;

import com.jweb.beans.User;
import com.jweb.dao.DBErrors;
import com.jweb.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lopes_n on 12/22/15.
 */
public final class UpdateUserForm {
    private static final String email_input = "email";
    private static final String password_input = "password";
    private static final String confirmation_input = "confirmation";
    private static final String user_name = "name";
    private static final String user_news = "news";
    private static final String user_id = "id";

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

    public User UpdateUser(HttpServletRequest request) {
        String email = getValue(request, email_input);
        String password = getValue(request, password_input);
        String confirmation = getValue(request, confirmation_input);
        String userName = getValue(request, user_name);
        boolean newsLetter = Boolean.getBoolean(getValue(request, user_news));
        int id = Integer.parseInt(getValue(request, user_id));

        User user = new User();
        user.setNews(newsLetter);
        user.setId(id);
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
        user.setPassword(password);
        try {
            name_validator(userName);
        } catch (Exception e) {
            setError(user_name, e.getMessage());
        }
        user.setName(userName);
        if (errors.isEmpty()) {
            result = "You have signed up successfully.";
        } else {
            result = "Sign up failure.";
        }
        try {
            UserDao db = new UserDao();
            db.updateUser(user);
        } catch (DBErrors e) {
            setError(user_name, e.getMessage());
            result = "Sign up failure.";
            return null;
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
        if (name == null || name.length() < 3) {
            throw new Exception("Your name must be at least 3 characters long");
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