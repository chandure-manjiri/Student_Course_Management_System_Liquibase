package StudentCourseLiquiBase.demo.MapStruct;

import StudentCourseLiquiBase.demo.Dto.*;
import StudentCourseLiquiBase.demo.Entity.Course;
import StudentCourseLiquiBase.demo.Entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.Set;


@Mapper(componentModel = "spring", uses = CourseMapper.class)
public interface StudentMapper {
    @Mapping(source = "lastname", target = "lastName")
    @Mapping(source = "id", target = "id")
    StudentNameDto convertToStudentNameDto(Student student);
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

    Student entityToEntity(Student student);

   @Mapping(target = "firstName", expression = "java(convertToFirstName(studentCreationDTO))")
   @Mapping(target = "lastName", expression = "java(convertToLastName(studentCreationDTO))")
   Student convertToEntity(StudentCreationDTO studentCreationDTO);

    @Named("toFirstName")
    default String convertToFirstName(StudentCreationDTO studentCreationDTO){
        return studentCreationDTO.getFullName().substring(0, studentCreationDTO.getFullName().indexOf(" "));
    }
    @Named("toLastName")
    default String convertToLastName(StudentCreationDTO studentCreationDTO){
        return studentCreationDTO.getFullName().substring(studentCreationDTO.getFullName().indexOf(" ") + 1);
    }

    @Mapping(source = "age", target = "age", defaultExpression = "java(student.getAge())")
    @Mapping(source = "phoneNumber", target = "phoneNumber", defaultExpression = "java(student.getPhoneNumber())")
    @Mapping(source = "fullName",target = "firstName", qualifiedByName = "toFirstName", defaultExpression = "java(student.getFirstName())")
    @Mapping(source = "fullName", target = "lastName", qualifiedByName = "toLastName", defaultExpression = "java(student.getLastname())")
   // @Mapping(target = "lastName", expression = "java(convertToLastNameUp(studentUpdateDTO))")
    void updateEntity(StudentUpdateDTO studentUpdateDTO, @MappingTarget Student student);
    @Named("toFirstName")
    default String convertToFirstNameUp(String fullName){
        return fullName.substring(0, fullName.indexOf(" "));
    }
    @Named("toLastName")
    default String convertToLastNameUp(String fullName){
        return fullName.substring(fullName.indexOf(" ") + 1);
    }


}

