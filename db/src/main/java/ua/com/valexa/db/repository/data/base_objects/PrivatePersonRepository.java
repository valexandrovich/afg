package ua.com.valexa.db.repository.data.base_objects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;

import java.util.UUID;

@Repository
public interface PrivatePersonRepository extends JpaRepository<PrivatePerson, UUID> {
}
