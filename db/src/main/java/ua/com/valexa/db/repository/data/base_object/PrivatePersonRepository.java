package ua.com.valexa.db.repository.data.base_object;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.base_object.PrivatePerson;

import java.util.UUID;

@Repository
public interface PrivatePersonRepository extends JpaRepository<PrivatePerson, UUID> {
}
