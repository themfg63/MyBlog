package com.SpringBoot.WebBlog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String url;

    @Lob
    @Column(nullable = false,columnDefinition = "longtext")
    private String content;             //içerik

    @Lob
    @Column(nullable = false,columnDefinition = "longtext")
    private String shortDescription;    //kısa açıklama

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @ManyToOne
    @JoinColumn(name = "createdBy",nullable = false)
    private User createdBy;

    @OneToMany(mappedBy = "post",cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();
}
