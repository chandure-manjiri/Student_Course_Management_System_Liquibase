package StudentCourseLiquiBase.demo.MapStruct;

import StudentCourseLiquiBase.demo.Dto.CourseDTO;
import StudentCourseLiquiBase.demo.Dto.StudentCreationDTO;
import StudentCourseLiquiBase.demo.Dto.StudentDTO;
import StudentCourseLiquiBase.demo.Dto.StudentNameDto;
import StudentCourseLiquiBase.demo.Entity.Course;
import StudentCourseLiquiBase.demo.Entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;


@Mapper(componentModel = "spring", uses = CourseMapper.class)
public interface StudentMapper {
    @Mapping(source = "lastname", target = "lastName")
    @Mapping(source = "id", target = "id")
    StudentNameDto convertToStudentNameDto(Student student);
//    @Mapping(source = "student", target = "studentName")

    Set<StudentNameDto> convertToStudentList(Set<Student> students);
    @Mapping(source = "lastname", target = "lastName")
    @Mapping(source = "course", target = "courseList")
    @Mapping(source = "course",target = "numberOfCourse", qualifiedByName = "numberOfCoursesOfStudent")
    @Mapping(target = "fullName", expression = "java(convertToFullName(student.getFirstName(), student.getLastname()))")
    StudentDTO convertToDTO(Student student);
    @Named("toFullName")
    default String convertToFullName(String firstName, String lastname){
        String fullName = firstName;
        fullName += " ";
        fullName += lastname;
        return  fullName;
    }
    @Named("numberOfCoursesOfStudent")
    default Integer converToSizeOfCourseList(Set<Course> courses){
        return courses.size();
    }
    StudentCreationDTO convertToCDTO(Student student);

    Student convertToEntity(StudentCreationDTO studentCreationDTO);

}
