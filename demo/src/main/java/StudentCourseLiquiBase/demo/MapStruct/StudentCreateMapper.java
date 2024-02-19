package StudentCourseLiquiBase.demo.MapStruct;

import StudentCourseLiquiBase.demo.Dto.StudentCreationDTO;
import StudentCourseLiquiBase.demo.Entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentCreateMapper {

    default String convertToFullName(Student student){
        String name = student.getFirstName();
        name += " ";
        name += student.getLastname();
        return name;
    }

}
