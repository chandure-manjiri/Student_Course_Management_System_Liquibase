package StudentCourseLiquiBase.demo.Repository;

import StudentCourseLiquiBase.demo.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CourseRepository extends JpaRepository<Course, Integer> {
}
