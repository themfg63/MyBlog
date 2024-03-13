package com.SpringBoot.WebBlog.controller;

import com.SpringBoot.WebBlog.dto.CommentDto;
import com.SpringBoot.WebBlog.dto.PostDto;
import com.SpringBoot.WebBlog.entity.Post;
import com.SpringBoot.WebBlog.service.Interface.CommentService;
import com.SpringBoot.WebBlog.service.Interface.PostService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    private CommentService commentService;
    private PostService postService;

    public CommentController(CommentService commentService,PostService postService){
        this.commentService=commentService;
        this.postService=postService;
    }

    @PostMapping("/{postUrl}/comments")
    public String saveComment(@PathVariable String postUrl, @Valid @ModelAttribute("comment")CommentDto commentDto, BindingResult result, Model model){
        PostDto postDto = postService.findPostByUrl(postUrl);

        if(result.hasErrors()){
            model.addAttribute("comment",commentDto);
            model.addAttribute("post",postDto);
            return "blog/blog_post";
        }

        commentService.createComment(postUrl,commentDto);

        return "redirect:/post/" + postUrl;
    }
}
