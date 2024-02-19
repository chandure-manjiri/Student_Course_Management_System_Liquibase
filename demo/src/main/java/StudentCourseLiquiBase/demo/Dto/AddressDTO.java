package StudentCourseLiquiBase.demo.Dto;

import StudentCourseLiquiBase.demo.Entity.Student;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Integer id;
    private String area;
    private String city;
    private Integer pin;
    private String state;
    private String country;

}
