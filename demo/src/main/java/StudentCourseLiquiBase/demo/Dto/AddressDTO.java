package StudentCourseLiquiBase.demo.Dto;

import StudentCourseLiquiBase.demo.Entity.Student;
import jakarta.persistence.Column;

public class AddressDTO {
    private Integer id;
    private String area;

    private String city;

    private Integer pin;

    private String state;
    private String country;

    private Student student;

}
