package ua.com.valexa.db.repository.data.attribute.birthplace;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.birthplace.Birthplace;
import ua.com.valexa.db.model.data.attribute.inn.Inn;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BirthplaceRepository extends JpaRepository<Birthplace, UUID> {
    Optional<Birthplace> findByBirthplace(String birthplace);
}
