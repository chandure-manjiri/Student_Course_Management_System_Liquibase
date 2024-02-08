package StudentCourseLiquiBase.demo.MapStruct;

import StudentCourseLiquiBase.demo.Dto.AddressDTO;
import StudentCourseLiquiBase.demo.Entity.Address;
import StudentCourseLiquiBase.demo.Entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {
    List<Address> convertToEntityList(List<AddressDTO> addressDTOList);


    Address convertToAddress(AddressDTO addressDTO);

    @Mapping(target = "area", defaultExpression = "java(address.getArea())")
    @Mapping(target = "flat", defaultExpression = "java(address.getFlat())")
    @Mapping(target = "city", defaultExpression = "java(address.getCity())")
    @Mapping(target = "pin", defaultExpression = "java(address.getPin())")
    @Mapping(target = "state", defaultExpression = "java(address.getState())")
    @Mapping(target = "country", defaultExpression = "java(address.getCountry())")
   // @Mapping(target = "sid", defaultExpression = "java(address.getStudent().)")
    void updateAddress(AddressDTO addressDTO, @MappingTarget Address address);

    List<AddressDTO> convertToDtoList(List<Address> addressList);

    AddressDTO convertToDto(Address address);





}
