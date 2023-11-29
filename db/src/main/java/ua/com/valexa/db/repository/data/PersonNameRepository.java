package ua.com.valexa.db.repository.data;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;

import java.util.UUID;

@Repository
@DynamicInsert
@DynamicUpdate
public interface PersonNameRepository extends JpaRepository<PersonName, UUID> {
}
