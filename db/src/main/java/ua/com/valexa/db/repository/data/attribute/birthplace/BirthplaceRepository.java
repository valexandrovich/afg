package ua.com.valexa.db.repository.data.attribute.birthplace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.birthplace.Birthplace;

import java.util.UUID;

@Repository
public interface BirthplaceRepository extends JpaRepository<Birthplace, UUID> {
}
