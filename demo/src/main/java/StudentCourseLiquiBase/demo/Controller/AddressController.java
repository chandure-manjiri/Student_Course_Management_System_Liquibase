package StudentCourseLiquiBase.demo.Controller;

import StudentCourseLiquiBase.demo.Dto.AddressDTO;
import StudentCourseLiquiBase.demo.Dto.StudentCreationDTO;
import StudentCourseLiquiBase.demo.Dto.StudentDTO;
import StudentCourseLiquiBase.demo.Entity.Address;
import StudentCourseLiquiBase.demo.Entity.Student;
import StudentCourseLiquiBase.demo.MapStruct.AddressMapper;
import StudentCourseLiquiBase.demo.MapStruct.StudentMapper;
import StudentCourseLiquiBase.demo.Repository.AddressRepository;
import StudentCourseLiquiBase.demo.Repository.StudentRepository;
import StudentCourseLiquiBase.demo.Services.AddressServices;
import StudentCourseLiquiBase.demo.exception.CourseExistsException;
import StudentCourseLiquiBase.demo.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/students_Courses")
public class AddressController {

        @Autowired
        private StudentRepository studentRepository;

        @Autowired
        private AddressRepository addressRepository;

        @Autowired
        private AddressMapper addressMapper;

        @Autowired
        private StudentMapper studentMapper;

        @Autowired
        private AddressServices addressServices;

    @PutMapping("/students/{id}/addresses")
    public ResponseEntity<List<AddressDTO>> createAddress(@RequestBody List<AddressDTO> addressDTOList, @PathVariable(name = "id") Integer sid) throws ResourceNotFoundException, CourseExistsException {

        //list addressList list addressDtoList
        List<AddressDTO> addressDTOList1 = addressServices.createAndUpdateAddresses(addressDTOList, sid);

        return new ResponseEntity<>(addressDTOList1, HttpStatus.CREATED);
    }
}
