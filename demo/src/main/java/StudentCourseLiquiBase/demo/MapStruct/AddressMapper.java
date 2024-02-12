package StudentCourseLiquiBase.demo.MapStruct;

import StudentCourseLiquiBase.demo.Dto.AddressDTO;
import StudentCourseLiquiBase.demo.Entity.Address;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface AddressMapper {

    Address convertToAddressEntity(AddressDTO addressDTO);

    AddressDTO convertToAddressDto(Address address);

    List<Address> convertToAddressList(List<AddressDTO> addressDTOList);

    List<AddressDTO> convertToAddressDtoList(List<Address> addressList);

    void updateAddress(AddressDTO addressDTO, @MappingTarget Address address);
}
