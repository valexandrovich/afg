package ua.com.valexa.db.repository.data;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.base_object.PrivatePerson;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrivatePersonRepository extends JpaRepository<PrivatePerson, UUID> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "personNameLinks")
    Optional<PrivatePerson> findById(UUID id);

}
