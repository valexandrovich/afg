package ua.com.valexa.db.repository.stage;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;

import java.util.UUID;

@Repository
public interface PrivatePersonStageRowRepository extends JpaRepository<PrivatePersonStageRow, UUID> {

    Slice<PrivatePersonStageRow> findAllByIsHandled(Boolean isHandled , Pageable pageable);
    Slice<PrivatePersonStageRow> findBy(Pageable pageable);

}
