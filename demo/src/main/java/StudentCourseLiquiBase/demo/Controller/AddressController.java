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

    @PutMapping("/students/{id}/addresses")
    public ResponseEntity<List<AddressDTO>> createAddress(@RequestBody List<AddressDTO> addressDTOList, @PathVariable(name = "id") Integer sid) throws ResourceNotFoundException, CourseExistsException {
        Student student = this.studentRepository.findById(sid).orElseThrow(() -> new ResourceNotFoundException("Student not found this UUID ::" + sid));

        //tempAddresses for storing list of address for responce purpose
        List<Address> tempAddresses = new ArrayList<>();
        for(AddressDTO addressDto : addressDTOList){
            if(addressDto.getId() == null){ // new address
                System.out.println("in");
                Address address = this.addressMapper.convertToAddress(addressDto);
                address.setStudent(student);
                this.addressRepository.save(address);
                tempAddresses.add(address);
                System.out.println("out");
            }
            else if(this.addressRepository.existsById(addressDto.getId())) { //address already exists
                // UpdateAddresses.add(addressDto);
                Address address = this.addressRepository.findById(addressDto.getId()).get();
                if (Objects.equals(address.getStudent().getId(), student.getId())) {
                    //update
                    this.addressMapper.updateAddress(addressDto, address);
                    address.setStudent(student);
                    this.addressRepository.save(address);
                    // Address Taddress = this.addressRepository.findById(addressDto.getId()).get();
                    tempAddresses.add(address);
                } else {
                    //throw exception
                    throw new CourseExistsException("This is invalid address id");
                }
            }
           else{
                //throw exception
                throw new CourseExistsException("This is invalid address id");
            }

        }
        //list addressList list addressDtoList
        List<AddressDTO> addressDTOList1 = addressMapper.convertToDtoList(tempAddresses);

        return new ResponseEntity<>(addressDTOList1, HttpStatus.CREATED);
    }
}
