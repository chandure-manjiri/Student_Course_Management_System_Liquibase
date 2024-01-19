package StudentCourseLiquiBase.demo.Repository;

import StudentCourseLiquiBase.demo.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
