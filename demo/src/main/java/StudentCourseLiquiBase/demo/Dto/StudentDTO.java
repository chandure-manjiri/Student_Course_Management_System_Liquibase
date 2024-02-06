package StudentCourseLiquiBase.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Integer Id;
    private String firstName;
    private String lastName;
    private String gender;
    private Set<CourseDTO> courseList;
    private String fullName;
    private Integer numberOfCourse;
}
