package ua.com.valexa.db.repository.data.attribute.address_simple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimple;
import ua.com.valexa.db.model.data.attribute.birthday.Birthday;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressSimpleRepository extends JpaRepository<AddressSimple, UUID> {
    Optional<AddressSimple> findByAddress(String address);
}
