package com.SpringBoot.WebBlog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty(message="E-posta boş veya null olmamalıdır")
    @Email
    private String email;

    @NotEmpty(message="Mesaj gövdesi boş bırakılmamalıdır")
    private String content;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;
}