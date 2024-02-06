package StudentCourseLiquiBase.demo.Dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreationDTO {
    @NotBlank(message = "Course name can't be blank")
    private String name;
}
