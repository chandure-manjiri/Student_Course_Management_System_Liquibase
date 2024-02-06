package StudentCourseLiquiBase.demo.Services;

import StudentCourseLiquiBase.demo.Dto.CourseDTO;
import StudentCourseLiquiBase.demo.Dto.StudentCreationDTO;
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
public class StudentServices {

     @Autowired
        private StudentRepository studentRepository;

     @Autowired
        private CourseRepository courseRepository;

     public List<StudentDTO> getAllStudents(){
         List<StudentDTO> studentList = this.studentRepository.findAll().stream().
                                   map(this::convertToDTO).toList();

         return studentList;
     }

    public StudentDTO getStudentByID(Integer id) throws ResourceNotFoundException {
        Student student = this.studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + id));

        StudentDTO studentDTO = convertToDTO(student);
        return studentDTO;
    }

    public StudentDTO createStudent(StudentCreationDTO studentdto)  throws ResourceNotFoundException{
           Student student = convertToEntity(studentdto);

        Set<Course> tempcourselist = student.getCourse();
        Set<Course> tempcourselist2 = new HashSet<>();
        student.setCourse(tempcourselist2);
        for(Course tempcourse : tempcourselist){
            int cid = tempcourse.getId();
            System.out.println("cid:"+cid);
            Course validcourse = this.courseRepository.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + cid));
            student.getCourse().add(validcourse);
        }
        this.studentRepository.save(student);
        return convertToDTO(student);
    }

    public StudentCreationDTO updateStudent(StudentCreationDTO studentCreationDTO, Integer id) throws ResourceNotFoundException{
            Student student = this.studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + id));
          //  Student student1 = convertToEntity(studentCreationDTO);

            student.setGender(studentCreationDTO.getGender());
            student.setAge(studentCreationDTO.getAge());
            student.setLastName(studentCreationDTO.getLastName());
            student.setFirstName(studentCreationDTO.getFirstName());
            student.setPhoneNumber(studentCreationDTO.getPhoneNumber());

           // Set<CourseDTO> cdt = new HashSet<>();
            Set<CourseDTO> cdt1 = studentCreationDTO.getCourse();

            Set<Course> tempcourselist = new HashSet<>();
            // course dto to course
            for(CourseDTO courseDTO : cdt1){
                Integer cid = courseDTO.getId();
                Course validcourse = this.courseRepository.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + cid));
                tempcourselist.add(validcourse);
            }
            student.setCourse(tempcourselist);
            //student.setCourse(studentCreationDTO.getCourse());

            this.studentRepository.save(student);

            StudentCreationDTO studentCreationDTO1 = convertToCDTO(student);

            return  studentCreationDTO1;

    }

    public void removeStudent(Integer id) throws ResourceNotFoundException{
        Student student = this.studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + id));
             this.studentRepository.deleteById(id);
    }

    public StudentDTO assignCourseToStudent(Integer stu_id, Integer cour_id) throws ResourceNotFoundException{
        Student student1 = this.studentRepository.findById(stu_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + stu_id));

        Course course1 = this.courseRepository.findById(cour_id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + cour_id));


        student1.getCourse().add(course1);
        studentRepository.save(student1);
        return convertToDTO(student1);
    }
     public Student convertToEntity(StudentCreationDTO studentDTO) throws ResourceNotFoundException{
         Student student = new Student();
         student.setId(studentDTO.getId());
         student.setAge(studentDTO.getAge());
         student.setGender(studentDTO.getGender());
         student.setFirstName(studentDTO.getFirstName());
         student.setLastName(studentDTO.getLastName());
         student.setPhoneNumber(studentDTO.getPhoneNumber());

         Set<Course> tempCourseSet = new HashSet<>();
         for(CourseDTO courseDTO : studentDTO.getCourse()){
             Course course1 = this.courseRepository.findById(courseDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + courseDTO.getId()));
             tempCourseSet.add(course1);
         }

         student.setCourse(tempCourseSet);
         return student;

     }
     public StudentDTO convertToDTO(Student student){

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

    public StudentCreationDTO convertToCDTO(Student student){

        StudentCreationDTO studentDTO = new StudentCreationDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastname());
        studentDTO.setGender(student.getGender());
        studentDTO.setPhoneNumber(student.getPhoneNumber());
        studentDTO.setAge(student.getAge());
        studentDTO.setCourse(new HashSet<CourseDTO>());
        for(Course course : student.getCourse()){
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setName(course.getName());
            studentDTO.getCourse().add(courseDTO);
        }

        return studentDTO;

    }

}
