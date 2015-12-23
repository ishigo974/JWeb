package com.jweb.forms;

import com.jweb.beans.Article;
import com.jweb.dao.ArticleDao;
import com.jweb.dao.DBErrors;
import com.jweb.dao.NewsDao;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lopes_n on 12/23/15.
 */
public class addArticleForm {
    private static final String article_title = "title";
    private static final String article_content = "content";
    private static final String article_price = "price";

    public Article addArticle(HttpServletRequest request) {
        String title = getValue(request, article_title);
        String price = getValue(request, article_price);
        String content = getValue(request, article_content);

        Article article = new Article();
        article.setContent(content);
        article.setTitle(title);
        article.setPrice(price);
        try {
            ArticleDao db = new ArticleDao();
            db.setArticle(article);
        } catch (DBErrors e) {
            return null;
        }
        return article;
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
