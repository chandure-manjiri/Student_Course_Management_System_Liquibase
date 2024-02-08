package StudentCourseLiquiBase.demo.Services;

import StudentCourseLiquiBase.demo.Dto.AddressDTO;
import StudentCourseLiquiBase.demo.Entity.Address;
import StudentCourseLiquiBase.demo.Entity.Student;
import StudentCourseLiquiBase.demo.MapStruct.AddressMapper;
import StudentCourseLiquiBase.demo.MapStruct.StudentMapper;
import StudentCourseLiquiBase.demo.Repository.AddressRepository;
import StudentCourseLiquiBase.demo.Repository.StudentRepository;
import StudentCourseLiquiBase.demo.exception.AddressException;
import StudentCourseLiquiBase.demo.exception.CourseExistsException;
import StudentCourseLiquiBase.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AddressServices {
    private StudentMapper studentMapper;

    @Autowired
    private StudentRepository studentRepository;

    private AddressMapper addressMapper;
    @Autowired
    private AddressRepository addressRepository;


    @Autowired
    public void StudentMapperService(StudentMapper studentMapper){
        this.studentMapper = studentMapper;
    }

    @Autowired
    public void AddressMapperService(AddressMapper addressMapper){
        this.addressMapper = addressMapper;
    }
    public List<AddressDTO> createAndUpdateAddresses(List<AddressDTO> addressDTOList, Integer sid) throws ResourceNotFoundException, AddressException {
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
                    throw new AddressException("This is invalid address id");
                }
            }
            else{
                //throw exception
                throw new AddressException("This is invalid address id");
            }

        }

        return addressMapper.convertToDtoList(tempAddresses);
    }

}
