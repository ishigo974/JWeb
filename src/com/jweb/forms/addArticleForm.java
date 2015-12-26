package com.jweb.forms;

import com.jweb.beans.Article;
import com.jweb.dao.ArticleDao;
import com.jweb.dao.DBErrors;
import com.jweb.dao.NewsDao;

import javax.servlet.http.HttpServletRequest;

/**
 * The form to add an article
 * Created by lopes_n on 12/23/15.
 */
public class addArticleForm {
    private static final String article_title = "title";
    private static final String article_content = "content";
    private static final String article_price = "price";

    /**
     * Add an article in database
     * @param request HttpServletRequest
     *                The request the user sent
     * @param img String
     *            The name of the image linked with the article
     * @return The created article
     */
    public Article addArticle(HttpServletRequest request, String img) {
        String title = getValue(request, article_title);
        String price = getValue(request, article_price);
        String content = getValue(request, article_content);

        Article article = new Article();
        article.setContent(content);
        article.setTitle(title);
        article.setPrice(price);
        article.setImg(img);
        try {
            ArticleDao db = new ArticleDao();
            db.setArticle(article);
        } catch (DBErrors e) {
            return null;
        }
        return article;
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
