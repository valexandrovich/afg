package ua.com.valexa.db.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
//            value = "user-graph"
//    )

//    @Query("SELECT  u from User u LEFT JOIN fetch u.usersOn LEFt join fetch u.usersAt where u.id = :id")
//    Optional<User> findByIdWithNames(Long id);

    @EntityGraph(attributePaths = {"usersAt", "usersOn"})
    Optional<User> findById(Long id);

}
