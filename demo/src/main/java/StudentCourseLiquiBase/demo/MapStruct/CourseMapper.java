package StudentCourseLiquiBase.demo.MapStruct;

import StudentCourseLiquiBase.demo.Dto.AllCourseDTO;
import StudentCourseLiquiBase.demo.Dto.CourseCreationDTO;
import StudentCourseLiquiBase.demo.Dto.CourseDTO;
import StudentCourseLiquiBase.demo.Entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.Set;


@Mapper(componentModel = "spring", uses = CourseMapper.class)
public interface CourseMapper {

      Set<CourseDTO> convertToCDTOSet(Set<Course> courses);
      CourseDTO convertToDTO(Course course);
      Course convertToEntity(CourseDTO courseDTO);

      @Mapping(source = "student", target = "studentName")
      AllCourseDTO convertToADTO(Course course);


      @Mapping(target = "id", ignore = true)
      Course convertToEntityUpdate(CourseCreationDTO courseCreationDTO);
      Course convertToEntityCreation(CourseCreationDTO courseCreationDTO);
}
