package StudentCourseLiquiBase.demo.Services;

import StudentCourseLiquiBase.demo.Dto.AllCourseDTO;
import StudentCourseLiquiBase.demo.Dto.CourseCreationDTO;
import StudentCourseLiquiBase.demo.Dto.CourseDTO;
import StudentCourseLiquiBase.demo.Dto.StudentDTO;
import StudentCourseLiquiBase.demo.Entity.Course;
import StudentCourseLiquiBase.demo.Entity.Student;
import StudentCourseLiquiBase.demo.Repository.CourseRepository;
import StudentCourseLiquiBase.demo.Repository.StudentRepository;
import StudentCourseLiquiBase.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CourseServices {

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

    public Set<StudentDTO> getAllStudents(Integer id) throws ResourceNotFoundException{
            Course course = this.courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + id));

            Set<StudentDTO> studentDTOSet = new HashSet<>();
            for(Student student : course.getStudent()){
                StudentDTO studentDTO = convertToSDTO(student);
                studentDTOSet.add(studentDTO);
            }
            return  studentDTOSet;
    }

    public CourseDTO createCourse(CourseCreationDTO courseCreationDTO){

             Course course = convertToEntity(courseCreationDTO);
              Course course1 = this.courseRepository.save(course);
             return convertToDTO(course1);
    }

    public void deleteCourse(Integer id) throws ResourceNotFoundException{
        Course course = this.courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + id));
        this.courseRepository.delete(course);
    }

    public AllCourseDTO updateCourse(CourseCreationDTO courseCreationDTO, Integer id) throws ResourceNotFoundException{
        Course course = this.courseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + id));
        course.setName(courseCreationDTO.getName());
        this.courseRepository.save(course);

        return convertToADTO(course);
    }

    public AllCourseDTO removeStudentFromCourse(Integer sid, Integer cid) throws ResourceNotFoundException{
        Course course = this.courseRepository.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + cid));
        Student student = this.studentRepository.findById(sid).orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + sid));
        student.getCourse().remove(course);
        this.studentRepository.save(student);

        return convertToADTO(course);
    }
    public Course convertToEntity(CourseCreationDTO courseCreationDTO){
        Course course = new Course();
        course.setName(courseCreationDTO.getName());
        return course;
    }
    public AllCourseDTO convertToADTO(Course course){
        AllCourseDTO courseDTO = new AllCourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());

        Set<String> tempStudentList = new HashSet<String>();
        for(Student student : course.getStudent()){

            String temp = student.getFirstName();
            temp += " ";
            temp += student.getLastname();
            tempStudentList.add(temp);
        }

        courseDTO.setStudentName(tempStudentList);
         return  courseDTO;

    }
    public CourseDTO convertToDTO(Course course) {

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());


        return courseDTO;
    }
      // courseDTO.setStudentDTOSet(new HashSet<StudentDTO>());

//       Set<StudentDTO> tempStudentLDtoList = new HashSet<StudentDTO>();
//       for(Student student : course.getStudent()){
//           StudentDTO studentDTO = convertToSDTO(student);
//           tempStudentLDtoList.add(studentDTO);
//       }
//       courseDTO.setStudentDTOSet(tempStudentLDtoList);
//        return courseDTO;


    public StudentDTO convertToSDTO(Student student){

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastname());
        studentDTO.setGender(student.getGender());
        studentDTO.setCourseList(new HashSet<CourseDTO>());
        for(Course course : student.getCourse()){
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setName(course.getName());
            studentDTO.getCourseList().add(courseDTO);
        }

        return studentDTO;

    }


}
