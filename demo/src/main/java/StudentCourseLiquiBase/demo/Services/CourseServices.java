package StudentCourseLiquiBase.demo.Services;

import StudentCourseLiquiBase.demo.Dto.*;
import StudentCourseLiquiBase.demo.Entity.Course;
import StudentCourseLiquiBase.demo.Entity.Student;
import StudentCourseLiquiBase.demo.MapStruct.CourseMapper;
import StudentCourseLiquiBase.demo.MapStruct.StudentMapper;
import StudentCourseLiquiBase.demo.Repository.CourseRepository;
import StudentCourseLiquiBase.demo.Repository.StudentRepository;
import StudentCourseLiquiBase.demo.exception.CourseExistsException;
import StudentCourseLiquiBase.demo.Dto.AllCourseDTO;
import StudentCourseLiquiBase.demo.Dto.CourseCreationDTO;
import StudentCourseLiquiBase.demo.Dto.CourseDTO;
import StudentCourseLiquiBase.demo.Dto.StudentDTO;
import StudentCourseLiquiBase.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseServices {

    private StudentMapper studentMapper;

    private CourseMapper courseMapper;
    @Autowired
    public void StudentMapperService(StudentMapper studentMapper){
        this.studentMapper = studentMapper;
    }

    @Autowired
    public void CourseMapperService(CourseMapper courseMapper){
        this.courseMapper = courseMapper;
    }

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<AllCourseDTO> getAllCourses(){

        return this.courseRepository.findAll().stream().
                map(this::convertToADTO).toList();
    }

    public AllCourseDTO getCourseById(Integer id) throws ResourceNotFoundException{
        Course course = this.courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + id));
        return convertToADTO(course);
    }

    private AllCourseDTO convertToADTO(Course course) {
        return courseMapper.convertToADTO(course);
    }



    public Set<StudentNameDto> getAllStudents(Integer id) throws ResourceNotFoundException{
            Course course = this.courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + id));

            Set<StudentNameDto> studentDTOSet = new HashSet<>();
            for(Student student : course.getStudent()){
                StudentNameDto studentNameDto = convertToStudentNameDto(student);
                studentDTOSet.add(studentNameDto);
            }
            return  studentDTOSet;
    }

    public CourseDTO createCourse(CourseCreationDTO courseCreationDTO){
              Course course = convertToEntityCreation(courseCreationDTO);

              this.courseRepository.save(course);
             return convertToDTO(course);
    }


    public void deleteCourse(Integer id) throws ResourceNotFoundException{
        Course course = this.courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + id));
        this.courseRepository.delete(course);
    }

    public AllCourseDTO updateCourse(CourseCreationDTO courseCreationDTO, Integer id) throws ResourceNotFoundException{
        Course course = this.courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + id));
        this.courseMapper.updateEntity(courseCreationDTO, course);
        this.courseRepository.save(course);
        return convertToADTO(course);
    }

    public AllCourseDTO removeStudentFromCourse(Integer cid, Integer sid) throws ResourceNotFoundException, CourseExistsException{
        Course course = this.courseRepository.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + cid));
        Student student = this.studentRepository.findById(sid).orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + sid));

        if(!isCourseExists(course, student)){
            throw new CourseExistsException("This student is already is removed from this course");
        } else{
            course.getStudent().remove(student);
            this.courseRepository.save(course);
            return convertToADTO(course);
        }
    }

    private boolean isCourseExists(Course course, Student student){
        return student.getCourse().contains(course);
    }
    public Course convertToEntityCreation(CourseCreationDTO courseCreationDTO){
        return courseMapper.convertToEntityCreation(courseCreationDTO);
    }

    public StudentNameDto convertToStudentNameDto(Student student){
        StudentNameDto studentNameDto = studentMapper.convertToStudentNameDto(student);
        return  studentNameDto;
    }



    public CourseDTO convertToDTO(Course course) {
        CourseDTO courseDTO = courseMapper.convertToDTO(course);
        return courseDTO;
    }

    public StudentDTO convertToSDTO(Student student) {

        StudentDTO studentDTO = studentMapper.convertToDTO(student);
        return studentDTO;
    }
}
