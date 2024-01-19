package StudentCourseLiquiBase.demo.Controller;



import StudentCourseLiquiBase.demo.Entity.Course;
import StudentCourseLiquiBase.demo.Entity.Student;
import StudentCourseLiquiBase.demo.Repository.CourseRepository;
import StudentCourseLiquiBase.demo.Repository.StudentRepository;
import StudentCourseLiquiBase.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/students_Courses")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    //get all student
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(){

        try{
            List<Student> studentList = this.studentRepository.findAll();
            return ResponseEntity.ok(studentList);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //get student by id
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(name = "id") Integer id) throws ResourceNotFoundException {

        Student student1 = this.studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + id));

        return ResponseEntity.ok().body(student1);

    }

    //add new student
    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student student1 = this.studentRepository.save(student);
        return new ResponseEntity<>(student1, HttpStatus.CREATED);
    }



    //update student
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable(name = "id") Integer stu_id) throws ResourceNotFoundException {
        Student student1 = this.studentRepository.findById(stu_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + stu_id));

        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastname());
        student1.setAge(student.getAge());
        student1.setGender(student.getGender());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setCourse(student.getCourse());

        return ResponseEntity.ok(this.studentRepository.save(student1));

    }

    @DeleteMapping("/students/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable (value = "id") Integer id) throws ResourceNotFoundException {


        Student student1 = this.studentRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("studnet not found by id :: "+ id));

        this.studentRepository.deleteById(id);
        Map<String, Boolean> respoce = new HashMap<>();
        respoce.put("deleted", Boolean.TRUE);
        return  respoce;
    }

    @PostMapping("/students/{stud_id}/assign_course/{cour_id}")
    public ResponseEntity<Student> AssignCourseToStudent(@PathVariable(name = "stud_id") Integer stu_id, @PathVariable(name = "cour_id") Integer cour_id) throws ResourceNotFoundException {
        Student student1 = this.studentRepository.findById(stu_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + stu_id));

        Course course1 = this.courseRepository.findById(cour_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + cour_id));

        student1.getCourse().add(course1);

        return ResponseEntity.ok(this.studentRepository.save(student1));

    }

    @PostMapping("/students/{stud_id}/remove_course/{cour_id}")
    public ResponseEntity<Student> RemoveCourseToStudent(@PathVariable(name = "stud_id") Integer stu_id, @PathVariable(name = "cour_id") Integer cour_id) throws ResourceNotFoundException {
        Student student1 = this.studentRepository.findById(stu_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + stu_id));

        Course course1 = this.courseRepository.findById(cour_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + cour_id));

        student1.getCourse().remove(course1);

        return ResponseEntity.ok(this.studentRepository.save(student1));

    }
}
