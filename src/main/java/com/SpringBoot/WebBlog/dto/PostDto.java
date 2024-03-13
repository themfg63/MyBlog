package com.SpringBoot.WebBlog.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;

    @NotEmpty(message = "Gönderi Başlığı boş bırakılmamalıdır")
    private String title;

    private String url;

    @NotEmpty(message = "Gönderi içeriği boş olmamalıdır")
    private String content;

    @NotEmpty(message = "Kısa açıklama yazısı boş bırakılmamalıdır")
    private String shortDescription;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

    private Set<CommentDto> comments;
}