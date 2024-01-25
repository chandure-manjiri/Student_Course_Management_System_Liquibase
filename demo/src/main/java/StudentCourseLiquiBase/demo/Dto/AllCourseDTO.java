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
public class AllCourseDTO {
    private Integer id;
    private String name;
    private Set<StudentNameDto> studentName;

}
