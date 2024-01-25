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
    private String fullName;
    private String gender;
    private Integer numberOfCourse;
    private Set<CourseDTO> courseList;

}
