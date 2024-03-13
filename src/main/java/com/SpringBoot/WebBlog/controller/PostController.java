package com.SpringBoot.WebBlog.controller;

import com.SpringBoot.WebBlog.dto.CommentDto;
import com.SpringBoot.WebBlog.dto.PostDto;
import com.SpringBoot.WebBlog.entity.Post;
import com.SpringBoot.WebBlog.service.Interface.CommentService;
import com.SpringBoot.WebBlog.service.Interface.PostService;
import com.SpringBoot.WebBlog.util.ROLE;
import com.SpringBoot.WebBlog.util.SecurityUtils;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private PostService postService;
    private CommentService commentService;

    public PostController(PostService postService,CommentService commentService){
        this.commentService=commentService;
        this.postService=postService;
    }

    @GetMapping("/admin/posts")
    public String getPosts(Model model){
        List<PostDto> posts = null;
        String role = SecurityUtils.getRole();

        if(ROLE.ROLE_ADMIN.name().equals(role)){
            posts = postService.findAllPosts();
        }else{
            posts = postService.findPostByUser();
        }

        model.addAttribute("posts",posts);
        return "/admin/posts";
    }

    @GetMapping("/admin/posts/comments")
    public String postComments(Model model){
        String role = SecurityUtils.getRole();
        List<CommentDto> comments = null;

        if(ROLE.ROLE_ADMIN.name().equals(role)){
            comments = commentService.findAllComments();
        }else{
            comments = commentService.findCommentsByPost();
        }

        model.addAttribute("comments",comments);
        return "admin/comments";
    }

    @GetMapping("/admin/posts/newpost")
    public String newPostForm(Model model){
        PostDto postDto = new PostDto();
        model.addAttribute("post",postDto);
        return "/admin/create_post";
    }

    @PostMapping("/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto, BindingResult result, Model model) { //@Valid for validation
        if(result.hasErrors()) {                                                                                  //BindingResult to check error and return to UI
            model.addAttribute("post", postDto);
            return "admin/create_post";
        }
        postDto.setUrl(getUrl(postDto.getTitle()));
        postService.createPost(postDto);
        return "redirect:/admin/posts";
    }

    private static String getUrl(String postTitle) {
        String title = postTitle.trim().toLowerCase();
        String url = title.replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;
    }

    @GetMapping("/admin/posts/{postId}/edit")
    public String editRequest(@PathVariable Long postId,Model model){
        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("post",postDto);
        return "admin/edit_post";
    }

    @PostMapping("/admin/posts/{postId}")
    public String updatePost(@PathVariable Long postId,@Valid @ModelAttribute("post")PostDto post, Model model,BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("post",post);
            return "admin/edit_post";
        }
        post.setId(postId);
        postService.updatePost(post);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable String postUrl,Model model){
        PostDto postDto = postService.findPostByUrl(postUrl);
        model.addAttribute("post",postDto);
        return "admin/view_post";
    }

    @GetMapping("/admin/posts/search")
    public String searchPosts(@RequestParam String query,Model model){
        List<PostDto> posts = postService.searchPosts(query);
        model.addAttribute("posts",posts);
        return "admin/posts";
    }
}
