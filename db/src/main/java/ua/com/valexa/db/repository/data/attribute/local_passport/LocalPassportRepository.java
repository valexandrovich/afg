package ua.com.valexa.db.repository.data.attribute.local_passport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.inn.Inn;
import ua.com.valexa.db.model.data.attribute.local_passport.LocalPassport;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LocalPassportRepository extends JpaRepository<LocalPassport, UUID> {

    Optional<LocalPassport> findBySerialAndNumber(
            String serial,
            String number
    );

}
