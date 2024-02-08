package StudentCourseLiquiBase.demo.Dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    private Integer flat;

    private String area;

    private String city;

    private Integer pin;

    private String state;

    private String country;
}
