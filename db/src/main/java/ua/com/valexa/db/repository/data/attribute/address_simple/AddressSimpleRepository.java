package ua.com.valexa.db.repository.data.attribute.address_simple;

import org.hibernate.boot.model.internal.JPAIndexHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimple;

import java.util.UUID;

@Repository
public interface AddressSimpleRepository extends JpaRepository<AddressSimple, UUID> {
}
