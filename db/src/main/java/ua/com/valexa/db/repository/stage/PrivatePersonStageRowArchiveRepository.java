package ua.com.valexa.db.repository.stage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;
import ua.com.valexa.db.model.stage.PrivatePersonStageRowArchive;

import java.util.UUID;

@Repository
public interface PrivatePersonStageRowArchiveRepository extends JpaRepository<PrivatePersonStageRowArchive, UUID> {


}
