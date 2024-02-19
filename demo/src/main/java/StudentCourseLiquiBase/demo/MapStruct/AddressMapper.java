package StudentCourseLiquiBase.demo.MapStruct;

import StudentCourseLiquiBase.demo.Dto.AddressDTO;
import StudentCourseLiquiBase.demo.Entity.Address;
import StudentCourseLiquiBase.demo.Entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {


    Address convertToAddressEntity(AddressDTO addressDTO);

    AddressDTO convertToAddressDto(Address address);

    List<Address> convertToAddressList(List<AddressDTO> addressDTOList);

    List<AddressDTO> convertToAddressDtoList(List<Address> addressList);


    @Mapping(source = "id", target = "id") // no default id value for update there is always id value
    @Mapping(source = "area", target = "area", defaultExpression = "java(address.getArea())")
    @Mapping(source = "city", target = "city", defaultExpression = "java(address.getCity())")
    @Mapping(source = "pin", target = "pin", defaultExpression = "java(address.getPin())")
    @Mapping(source = "state", target = "state", defaultExpression = "java(address.getState())")
    @Mapping(source = "country", target = "country", defaultExpression = "java(address.getCountry())")
    void updateAddressfromDto(AddressDTO addressDTO, @MappingTarget Address address);
    default void updateAddressList(List<AddressDTO> addressDTOList, @MappingTarget List<Address> addressList){
        if(addressDTOList != null) {
            for (AddressDTO inputAddressDto : addressDTOList) {
                if (inputAddressDto.getId() == null) {  // address sid not updated
                    Address inputAddress = convertToAddressEntity(inputAddressDto);
                    addressList.add(inputAddress);
                } else {
                    if (addressList != null) {
                        for (Address existingAddress : addressList) {
                            if (existingAddress.getId() == inputAddressDto.getId()) {
                                updateAddressfromDto(inputAddressDto, existingAddress);
                            }
                        }
                    } //

                }
            }
        }
    }
    void updateAddress(Address address1, @MappingTarget Address address);


}
