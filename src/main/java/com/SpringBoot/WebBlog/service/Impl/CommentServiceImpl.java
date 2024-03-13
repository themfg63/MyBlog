package com.SpringBoot.WebBlog.service.Impl;

import com.SpringBoot.WebBlog.dto.CommentDto;
import com.SpringBoot.WebBlog.entity.Comment;
import com.SpringBoot.WebBlog.entity.Post;
import com.SpringBoot.WebBlog.entity.User;
import com.SpringBoot.WebBlog.mapper.CommentMapper;
import com.SpringBoot.WebBlog.repository.CommentRepository;
import com.SpringBoot.WebBlog.repository.PostRepository;
import com.SpringBoot.WebBlog.repository.UserRepository;
import com.SpringBoot.WebBlog.service.Interface.CommentService;
import com.SpringBoot.WebBlog.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository,UserRepository userRepository){
        this.commentRepository=commentRepository;
        this.postRepository=postRepository;
        this.userRepository=userRepository;
    }

    @Override
    public void createComment(String postUrl, CommentDto commentDto){
        Post post = postRepository.findByUrl(postUrl).get() ;
        Comment comment = CommentMapper.mapToComment(commentDto);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> findAllComments(){
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList())   ;
    }

    @Override
    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentDto> findCommentsByPost(){
        String email = SecurityUtils.getCurrentUser().getUsername();
        User createdBY = userRepository.findByEmail(email);
        Long userId = createdBY.getId();
        List<Comment> comments = commentRepository.findCommentsByPost(userId);
        return comments.stream().map((comment) -> CommentMapper.mapToCommentDto(comment)).collect(Collectors.toList());
    }
}
