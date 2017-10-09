package com.liuyanzhao.blog.entity.custom;

import com.liuyanzhao.blog.entity.Comment;

/**
 * Created by 言曌 on 2017/9/10.
 */
public class CommentCustom extends Comment {
    //评论者的头像
    private String commentAuthorAvatar;

    public String getCommentAuthorAvatar() {
        return commentAuthorAvatar;
    }

    public void setCommentAuthorAvatar(String commentAuthorAvatar) {
        this.commentAuthorAvatar = commentAuthorAvatar;
    }
}
