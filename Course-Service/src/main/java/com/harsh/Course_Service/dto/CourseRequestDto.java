package com.harsh.Course_Service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequestDto {

    @NotBlank(message = "Course title is required")
    private String title;

    @NotBlank(message = "Course code is required")
    private String code;

    private Long studentId;
}
