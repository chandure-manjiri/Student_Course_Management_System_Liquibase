package StudentCourseLiquiBase.demo.Services;

import StudentCourseLiquiBase.demo.Dto.*;
import StudentCourseLiquiBase.demo.Entity.Address;
import StudentCourseLiquiBase.demo.Entity.Course;
import StudentCourseLiquiBase.demo.Entity.Student;
import StudentCourseLiquiBase.demo.MapStruct.AddressMapper;
import StudentCourseLiquiBase.demo.MapStruct.CourseMapper;
import StudentCourseLiquiBase.demo.MapStruct.StudentCreateMapper;
import StudentCourseLiquiBase.demo.MapStruct.StudentMapper;
import StudentCourseLiquiBase.demo.Repository.CourseRepository;
import StudentCourseLiquiBase.demo.Repository.StudentRepository;
import StudentCourseLiquiBase.demo.exception.CourseExistsException;
import StudentCourseLiquiBase.demo.exception.ResourceNotFoundException;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class StudentServices {

     private StudentMapper studentMapper;

     private AddressMapper addressMapper;

     private StudentCreateMapper studentCreateMapper;

     private CourseMapper courseMapper;
     @Autowired
     public void StudentMapperService(StudentMapper studentMapper){
         this.studentMapper = studentMapper;
     }

    @Autowired
    public void AddressMapperService(AddressMapper addressMapper){
        this.addressMapper = addressMapper;
    }

    @Autowired
     public void CourseMapperService(CourseMapper courseMapper){
         this.courseMapper = courseMapper;
     }

    @Autowired
    public void StudentCreateMapperService(StudentCreateMapper studentCreateMapper){
        this.studentCreateMapper = studentCreateMapper;
    }

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

        for(Address address : student.getAddressList()){
            if(address.getId() == null){
                  address.setStudent(student);
            }
            else{ // throw error here can't update address, can't pass address id
                throw new CourseExistsException("only able to create new address, can't update address");
            }
        }

        this.studentRepository.save(student);
        return convertToDTO(student);
    }

    public StudentCreationDTO updateStudent(StudentUpdateDTO studentUpdateDTO, Integer id) throws ResourceNotFoundException{

          Student student = this.studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + id));
          studentMapper.updateEntity(studentUpdateDTO, student);
          this.studentRepository.save(student);

            StudentCreationDTO studentCreationDTO1 = convertToCDTO(student);
            studentCreationDTO1.setFullName(studentCreateMapper.convertToFullName(student));
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

        if(isCourseExists(course1, student1)){
            throw new CourseExistsException("This course is already assigned with student");
        }
        else{
            student1.getCourse().add(course1);
            studentRepository.save(student1);
            return convertToDTO(student1);
        }
     }

    public StudentDTO removeCourseFromStudent(Integer sid, Integer cid) throws ResourceNotFoundException{
        Course course = this.courseRepository.findById(cid).orElseThrow(() -> new ResourceNotFoundException("Course not found this UUID ::" + cid));
        Student student = this.studentRepository.findById(sid).orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + sid));

        if(!isCourseExists(course, student)){
            throw new CourseExistsException("This course is already removed from student");
        }
        else{
            student.getCourse().remove(course);
            this.studentRepository.save(student);
            return convertToDTO(student);
        }
     }

    private boolean isCourseExists(Course course, Student student){
        return student.getCourse().contains(course);
    }
     public Student convertToEntity(StudentCreationDTO studentDTO) throws ResourceNotFoundException{
         Student student = studentMapper.convertToEntity(studentDTO);
//         student.setFirstName(studentCreateMapper.convertToFirstName(studentDTO));
//         student.setLastName(studentCreateMapper.convertToLastName(studentDTO));
         return student;

     }


     public StudentDTO convertToDTO(Student student){

         StudentDTO studentDTO = studentMapper.convertToDTO(student);
       return studentDTO;

     }

    public StudentCreationDTO convertToCDTO(Student student){

        StudentCreationDTO studentDTO = studentMapper.convertToCDTO(student);
        return studentDTO;

    }

    public Student convertEntityToEntity(Student student){
          return  studentMapper.entityToEntity(student);
    }

    public void updateEnity(StudentUpdateDTO studentUpdateDTO, Student student){
         studentMapper.updateEntity(studentUpdateDTO, student);
    }

    public StudentDTO updateStudentAddress(StudentCreationDTO studentCreationDTO, Integer sid) throws ResourceNotFoundException{
         Student student = convertToEntity(studentCreationDTO);
         Student student1 = studentRepository.findById(sid).orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + sid));


         for(Address address : student.getAddressList()){
            if(address.getId() == null){
                address.setStudent(student1);
                student1.getAddressList().add(address);
            }
            else{
                int val = 0;
                for(Address address1 : student1.getAddressList()){
                    if(address1.getId() == address.getId()){
                        this.addressMapper.updateAddress(address, address1);
                        address1.setStudent(student1);
                        val = 1;
                    }
                }
                if(val == 0){ // not found
                  throw new CourseExistsException("this address is invalid for update, not exists or not for this student");
                }
            }
         }

        this.studentMapper.updateCreationEntity(studentCreationDTO,student1); // student address map not error may come here
         this.studentRepository.save(student1);
         return convertToDTO(student1);
    }
}
