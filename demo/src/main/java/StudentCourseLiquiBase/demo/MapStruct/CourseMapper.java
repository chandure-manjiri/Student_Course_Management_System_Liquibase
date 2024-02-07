package StudentCourseLiquiBase.demo.MapStruct;

import StudentCourseLiquiBase.demo.Dto.AllCourseDTO;
import StudentCourseLiquiBase.demo.Dto.CourseCreationDTO;
import StudentCourseLiquiBase.demo.Dto.CourseDTO;
import StudentCourseLiquiBase.demo.Entity.Course;
import StudentCourseLiquiBase.demo.Entity.Student;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.Set;


@Mapper(componentModel = "spring", uses = StudentMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper {

      Set<CourseDTO> convertToCDTOSet(Set<Course> courses);
      CourseDTO convertToDTO(Course course);

      Course convertToEntity(CourseDTO courseDTO);
      @Mapping(source = "student", target = "studentName")
      AllCourseDTO convertToADTO(Course course);

      @Mapping(source = "name" , target = "name")
      void updateEntity(CourseCreationDTO courseCreationDTO, @MappingTarget Course course);

//      @Mapping(target = "id", ignore = true)
//      Course convertToEntityUpdate(CourseCreationDTO courseCreationDTO);
      Course convertToEntityCreation(CourseCreationDTO courseCreationDTO);
}
