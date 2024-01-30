package StudentCourseLiquiBase.demo.MapStruct;

import StudentCourseLiquiBase.demo.Dto.AllCourseDTO;
import StudentCourseLiquiBase.demo.Dto.CourseCreationDTO;
import StudentCourseLiquiBase.demo.Dto.CourseDTO;
import StudentCourseLiquiBase.demo.Entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring", uses = CourseMapper.class)
public interface CourseMapper {
      CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

      CourseDTO convertToDTO(Course course);
      Course convertToEntity(CourseDTO courseDTO);

      AllCourseDTO convertToADTO(Course course);

      Course convertToEntity(CourseCreationDTO courseCreationDTO);
}
