package com.jweb.forms;

import com.jweb.beans.News;
import com.jweb.dao.DBErrors;
import com.jweb.dao.NewsDao;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lopes_n on 12/22/15.
 */
public class UpdateNewForm {
    private static final String news_title = "title";
    private static final String news_content = "content";
    private static final String news_id = "id";

    /**
     * Update a news
     * @param request HttpServletRequest
     *                The post request the user sent
     * @return The updated news
     */
    public News UpdateNews(HttpServletRequest request) {
        String title = getValue(request, news_title);
        String content = getValue(request, news_content);
        int id = Integer.parseInt(getValue(request, news_id));

        News simpleNew = new News();
        simpleNew.setContent(content);
        simpleNew.setTitle(title);
        simpleNew.setId(id);
        try {
            NewsDao db = new NewsDao();
            db.updateNews(simpleNew);
            } catch (DBErrors e) {
                return null;
            }
        return simpleNew;
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
