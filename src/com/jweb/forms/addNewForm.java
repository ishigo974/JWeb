package com.jweb.forms;

import com.jweb.beans.News;
import com.jweb.dao.DBErrors;
import com.jweb.dao.NewsDao;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lopes_n on 12/22/15.
 */
public class addNewForm {
    private static final String news_title = "title";
    private static final String news_content = "content";

    public News addNews(HttpServletRequest request) {
        String title = getValue(request, news_title);
        String content = getValue(request, news_content);

        News simpleNew = new News();
        simpleNew.setContent(content);
        simpleNew.setTitle(title);
        try {
            NewsDao db = new NewsDao();
            db.setNews(simpleNew);
        } catch (DBErrors e) {
            return null;
        }
        return simpleNew;
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
