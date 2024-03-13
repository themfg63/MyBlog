package com.SpringBoot.WebBlog.service.Interface;

import com.SpringBoot.WebBlog.dto.CommentDto;

import java.util.List;

public interface CommentService {
    void createComment(String postUrl,CommentDto commentDto);
    List<CommentDto> findAllComments();
    void deleteComment(Long id);

    List<CommentDto> findCommentsByPost();
}
