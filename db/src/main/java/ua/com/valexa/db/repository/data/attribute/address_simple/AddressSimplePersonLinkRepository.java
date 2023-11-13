package ua.com.valexa.db.repository.data.attribute.address_simple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimplePersonLink;
import ua.com.valexa.db.model.data.attribute.birthday.BirthdayPersonLink;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressSimplePersonLinkRepository extends JpaRepository<AddressSimplePersonLink, UUID> {
        Optional<AddressSimplePersonLink> findByPrivatePersonIdAndAddressSimpleIdAndSource(
                UUID privatePersonId,
                UUID addressSimpleId,
                String source
        );
}
