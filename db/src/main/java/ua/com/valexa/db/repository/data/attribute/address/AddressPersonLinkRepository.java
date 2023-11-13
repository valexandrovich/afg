package ua.com.valexa.db.repository.data.attribute.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.address.AddressPersonLink;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimplePersonLink;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressPersonLinkRepository extends JpaRepository<AddressPersonLink, UUID> {


    Optional<AddressPersonLink> findByPrivatePersonIdAndAddressIdAndSource(
            UUID privatePersonId,
            UUID addressId,
            String source
    );

}
