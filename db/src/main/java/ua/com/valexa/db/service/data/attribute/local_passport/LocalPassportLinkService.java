package ua.com.valexa.db.service.data.attribute.local_passport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.model.data.attribute.local_passport.LocalPassportLink;
import ua.com.valexa.db.repository.data.attribute.inn.InnLinkRepository;
import ua.com.valexa.db.repository.data.attribute.local_passport.LocalPassportLinkRepository;

import java.util.Optional;

@Service
public class LocalPassportLinkService {
    @Autowired
    LocalPassportLinkRepository localPassportLinkRepository;

    public LocalPassportLink save(LocalPassportLink localPassportLink) {
        try {
            return localPassportLinkRepository.save(localPassportLink);
        } catch (DataIntegrityViolationException e) {
            Optional<LocalPassportLink> stored = localPassportLinkRepository.findByPrivatePersonIdAndLocalPassportIdAndSource(
                    localPassportLink.getPrivatePerson().getId(),
                    localPassportLink.getLocalPassport().getId(),
                    localPassportLink.getSource()
            );
            return stored.orElse(null);
        }
        // Other exceptions can be caught and handled as well
    }
}
