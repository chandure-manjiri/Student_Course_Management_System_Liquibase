package StudentCourseLiquiBase.demo.Repository;

import StudentCourseLiquiBase.demo.Entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
