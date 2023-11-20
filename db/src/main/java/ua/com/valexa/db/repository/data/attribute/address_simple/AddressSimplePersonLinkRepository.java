package ua.com.valexa.db.repository.data.attribute.address_simple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.address_simple.AdressSimplePersonLink;

import java.util.UUID;

@Repository
public interface AddressSimplePersonLinkRepository extends JpaRepository<AdressSimplePersonLink, UUID> {
}
