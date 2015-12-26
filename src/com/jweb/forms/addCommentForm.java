package com.jweb.forms;

import com.jweb.beans.Article;
import com.jweb.beans.Comment;
import com.jweb.dao.ArticleDao;
import com.jweb.dao.CommentDao;
import com.jweb.dao.DBErrors;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lopes_n on 12/24/15.
 */
public class addCommentForm {
    private static final String comment_content = "content";
    private static final String comment_article = "article";

    /**
     * Add a comment to the current article in database
     * @param request HttpServletRequest
     *                The request the user sent
     * @param name String
     *             The name of the user who created the comment
     * @return The created comment
     */
    public Comment addComment(HttpServletRequest request, String name) {
        String content = getValue(request, comment_content);
        int article = Integer.parseInt(getValue(request, comment_article));

        Comment comment = new Comment();
        comment.setName(name);
        comment.setContent(content);
        comment.setArticle(article);
        try {
            CommentDao db = new CommentDao();
            db.setComment(comment);
        } catch (DBErrors e) {
            return null;
        }
        return comment;
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
