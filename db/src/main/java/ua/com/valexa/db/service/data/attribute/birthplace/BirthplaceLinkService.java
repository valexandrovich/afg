package ua.com.valexa.db.service.data.attribute.birthplace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.birthplace.BithplaceLink;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.repository.data.attribute.birthplace.BithplaceLinkRepository;

import java.util.Optional;

@Service
public class BirthplaceLinkService {
    @Autowired
    BithplaceLinkRepository bithplaceLinkRepository;

    public BithplaceLink save(BithplaceLink bithplaceLink) {
        try {
            return bithplaceLinkRepository.save(bithplaceLink);
        } catch (DataIntegrityViolationException e) {
            Optional<BithplaceLink> stored = bithplaceLinkRepository.findByPrivatePersonIdAndBirthplaceIdAndSource(
                    bithplaceLink.getPrivatePerson().getId(),
                    bithplaceLink.getBirthplace().getId(),
                    bithplaceLink.getSource()
            );
            return stored.orElse(null);
        }
        // Other exceptions can be caught and handled as well
    }
}
