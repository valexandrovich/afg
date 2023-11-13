package ua.com.valexa.db.service.data.attribute.local_passport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.inn.Inn;
import ua.com.valexa.db.model.data.attribute.local_passport.LocalPassport;
import ua.com.valexa.db.repository.data.attribute.inn.InnRepository;
import ua.com.valexa.db.repository.data.attribute.local_passport.LocalPassportLinkRepository;
import ua.com.valexa.db.repository.data.attribute.local_passport.LocalPassportRepository;

import java.util.Optional;

@Service
public class LocalPassportService {
    @Autowired
    LocalPassportRepository localPassportRepository;

    public LocalPassport save(LocalPassport localPassport) {
        try {
            return localPassportRepository.save(localPassport);
        } catch (DataIntegrityViolationException e) {
            Optional<LocalPassport> stored = localPassportRepository.findBySerialAndNumber(
                    localPassport.getSerial(),
                    localPassport.getNumber()
            );
            return stored.orElse(null);
        }
    }
}
