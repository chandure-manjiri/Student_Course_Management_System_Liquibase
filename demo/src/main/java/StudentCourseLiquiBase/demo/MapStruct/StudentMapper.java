package StudentCourseLiquiBase.demo.MapStruct;

import StudentCourseLiquiBase.demo.Dto.StudentCreationDTO;
import StudentCourseLiquiBase.demo.Dto.StudentDTO;
import StudentCourseLiquiBase.demo.Dto.StudentNameDto;
import StudentCourseLiquiBase.demo.Entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;


@Mapper(componentModel = "spring", uses = CourseMapper.class)
public interface StudentMapper {
   // @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastname", target = "lastName")
    StudentNameDto convertToStudentNameDto(Student student);
//    @Mapping(source = "student", target = "studentName")

    Set<StudentNameDto> convertToStudentList(Set<Student> students);
    @Mapping(source = "lastname", target = "lastName")
    @Mapping(source = "course", target = "courseList")
    StudentDTO convertToDTO(Student student);
    StudentCreationDTO convertToCDTO(Student student);

    Student convertToEntity(StudentCreationDTO studentCreationDTO);

}
