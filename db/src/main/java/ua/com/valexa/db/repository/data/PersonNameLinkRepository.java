package ua.com.valexa.db.repository.data;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;

import java.util.UUID;

@Repository
//@DynamicInsert
public interface PersonNameLinkRepository extends JpaRepository<PersonNameLink, UUID> {



//    @Query(value = "INSERT INTO person_name ")
//    PersonName save(PersonName personName);

}
