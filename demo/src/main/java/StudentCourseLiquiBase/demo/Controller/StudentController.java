package StudentCourseLiquiBase.demo.Controller;



import StudentCourseLiquiBase.demo.Dto.StudentCreationDTO;
import StudentCourseLiquiBase.demo.Dto.StudentDTO;
import StudentCourseLiquiBase.demo.Entity.Course;
import StudentCourseLiquiBase.demo.Entity.Student;
import StudentCourseLiquiBase.demo.Repository.CourseRepository;
import StudentCourseLiquiBase.demo.Repository.StudentRepository;
import StudentCourseLiquiBase.demo.Services.StudentServices;
import StudentCourseLiquiBase.demo.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/students-Courses")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentServices studentServices;

    //get all student
    @GetMapping("/students")
    public ResponseEntity<List<StudentDTO>> getStudents(){

        try{
            List<StudentDTO> studentList = this.studentServices.getAllStudents();
            return ResponseEntity.ok(studentList);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //get student by id
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable(name = "id") Integer id) throws ResourceNotFoundException {

        StudentDTO student1 = studentServices.getStudentByID(id);
        return ResponseEntity.ok().body(student1);

    }

    //add new student
    @PostMapping("/students")
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentCreationDTO student) throws ResourceNotFoundException{
         StudentDTO studentDTO = studentServices.createStudent(student);
        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);

    }

    //update student
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentCreationDTO> updateStudent(@Valid @RequestBody StudentCreationDTO student, @PathVariable(name = "id") Integer stu_id) throws ResourceNotFoundException {

        StudentCreationDTO studentCreationDTO = this.studentServices.updateStudent(student, stu_id);
        return ResponseEntity.ok().body(studentCreationDTO);


    }

    @DeleteMapping("/students/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable (value = "id") Integer id) throws ResourceNotFoundException {


        this.studentServices.removeStudent(id);
        Map<String, Boolean> respoce = new HashMap<>();
        respoce.put("deleted", Boolean.TRUE);
        return  respoce;
    }

    @PutMapping("/students/{sid}/courses/{cid}")
    public ResponseEntity<StudentDTO> AssignCourseToStudent(@PathVariable(name = "sid") Integer stu_id, @PathVariable(name = "cid") Integer cour_id) throws ResourceNotFoundException {
        Student student1 = this.studentRepository.findById(stu_id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + stu_id));


        StudentDTO studentDTO = this.studentServices.assignCourseToStudent(stu_id, cour_id);
        return ResponseEntity.ok().body(studentDTO);

    }



}
