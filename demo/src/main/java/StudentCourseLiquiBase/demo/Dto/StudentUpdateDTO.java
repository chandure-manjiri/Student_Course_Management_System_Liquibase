package StudentCourseLiquiBase.demo.Dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateDTO {
    @NotBlank(message = "Full name can't be blank")
    private String fullName;
    @Min(15)
    @Max(24)
    private Integer age;
    @NotNull
    @Pattern(regexp="^\\d{10}$", message = "Ivalid number, enter 10 digit only")
    private String phoneNumber;
    //private Set<CourseDTO> course;
}
