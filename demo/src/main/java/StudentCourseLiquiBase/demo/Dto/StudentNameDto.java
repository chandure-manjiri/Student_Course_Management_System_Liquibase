package StudentCourseLiquiBase.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentNameDto {
    private Integer id;
    private String firstName;
    private String lastName;
}
