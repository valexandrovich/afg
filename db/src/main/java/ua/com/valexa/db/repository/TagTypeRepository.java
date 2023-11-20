package ua.com.valexa.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.data.tag.TagType;

@Repository
public interface TagTypeRepository extends JpaRepository<TagType, String> {
}
