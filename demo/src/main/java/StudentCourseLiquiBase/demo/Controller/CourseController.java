package StudentCourseLiquiBase.demo.Controller;


import StudentCourseLiquiBase.demo.Dto.*;
import StudentCourseLiquiBase.demo.Services.CourseServices;
import StudentCourseLiquiBase.demo.exception.CourseExistsException;
import StudentCourseLiquiBase.demo.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/students_Courses")
public class CourseController {



    @Autowired
    private CourseServices courseServices;

    @GetMapping("/courses")
    public ResponseEntity<List<AllCourseDTO>> getCourses(){

        try{
            List<AllCourseDTO> courseList = this.courseServices.getAllCourses();
            return ResponseEntity.ok(courseList);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<AllCourseDTO> getCourseById(@PathVariable(name = "id") Integer id) throws ResourceNotFoundException {

        AllCourseDTO course = this.courseServices.getCourseById(id);

        return ResponseEntity.ok().body(course);

    }


    @GetMapping("/courses/{id}/getstudents")
    public ResponseEntity<Set<StudentNameDto>> getStudentsByCourseId(@PathVariable(name = "id") Integer id) throws ResourceNotFoundException {

        Set<StudentNameDto> studentSet = this.courseServices.getAllStudents(id);
        return ResponseEntity.ok().body(studentSet);

    }

    @PostMapping("/courses")
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseCreationDTO course){
        CourseDTO course1 = this.courseServices.createCourse(course);
        return new ResponseEntity<>(course1, HttpStatus.CREATED);
    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<AllCourseDTO> updateCourse(@Valid @RequestBody CourseCreationDTO courseCreationDTO, @PathVariable(name = "id") Integer id) throws ResourceNotFoundException {

        AllCourseDTO allCourseDTO = this.courseServices.updateCourse(courseCreationDTO, id);

        return ResponseEntity.ok().body(allCourseDTO);

    }

    @DeleteMapping("/courses/{cid}/student/{sid}")
    public ResponseEntity<AllCourseDTO> RemoveCourseFromStudent(@PathVariable(name = "cid") Integer cid, @PathVariable(name = "sid") Integer sid) throws ResourceNotFoundException , CourseExistsException {
         AllCourseDTO allCourseDTO = this.courseServices.removeStudentFromCourse(cid, sid);

        return ResponseEntity.ok().body(allCourseDTO);

    }

    @DeleteMapping("/courses/{id}")
    public Map<String, Boolean> deleteCourse(@PathVariable (value = "id") Integer id) throws ResourceNotFoundException {

        this.courseServices.deleteCourse(id);
        Map<String, Boolean> respoce = new HashMap<>();
        respoce.put("deleted", Boolean.TRUE);
        return  respoce;
    }


}
