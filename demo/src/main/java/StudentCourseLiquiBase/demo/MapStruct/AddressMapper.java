package StudentCourseLiquiBase.demo.MapStruct;

import StudentCourseLiquiBase.demo.Dto.AddressDTO;
import StudentCourseLiquiBase.demo.Entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {


    Address convertToAddressEntity(AddressDTO addressDTO);

    AddressDTO convertToAddressDto(Address address);

    List<Address> convertToAddressList(List<AddressDTO> addressDTOList);

    List<AddressDTO> convertToAddressDtoList(List<Address> addressList);

    void updateAddress(Address address1, @MappingTarget Address address);
}
