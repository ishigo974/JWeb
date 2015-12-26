package com.jweb.forms;

import com.jweb.beans.User;
import com.jweb.dao.DBErrors;
import com.jweb.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by menigo_m on 16/12/15.
 */
public class LoginForm {
    private static final String email_input = "email";
    private static final String password_input = "password";
    private static final String bdd_error = "database";

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
     * Login the user with the informations given in the form
     * @param request HttpServletRequest
     *                The request the user sent
     * @return The logged in user
     */
    public User loginUser(HttpServletRequest request) {
        UserDao db;
        try {
            db = new UserDao();
        } catch (DBErrors e)
        {
            setError(bdd_error, e.getMessage());
            result = e.getMessage();
            return null;
        }

        String email = getValue(request, email_input);
        String password = getValue(request, password_input);
        User user = null;
        try {
            user = db.getUser(email, password);
        } catch (DBErrors e) {
            setError(bdd_error, e.getMessage());
            result = e.getMessage();
            return null;
        }
        result = "You are now logged in";
        return user;
    }

    /**
     * Check if the mail is valid in the database
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
     * Check if the password field is valid in the database
     * @param password String
     *                 The password value
     */
    private void password_validator(String password) throws Exception {
        if (password == null || password.length() < 3) {
            throw new Exception("Invalid password");
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
