package StudentCourseLiquiBase.demo.MapStruct;

import StudentCourseLiquiBase.demo.Dto.StudentCreationDTO;
import StudentCourseLiquiBase.demo.Entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentCreateMapper {

    default String convertToFirstName(StudentCreationDTO studentCreationDTO){
           return studentCreationDTO.getFullName().substring(0, studentCreationDTO.getFullName().indexOf(" "));
    }

    default String convertToLastName(StudentCreationDTO studentCreationDTO){
        return studentCreationDTO.getFullName().substring(studentCreationDTO.getFullName().indexOf(" ") + 1);
    }

    default String convertToFullName(Student student){
        String name = student.getFirstName();
        name += " ";
        name += student.getLastname();
        return name;
    }

}
