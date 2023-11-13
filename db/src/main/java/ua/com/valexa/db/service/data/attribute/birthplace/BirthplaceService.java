package ua.com.valexa.db.service.data.attribute.birthplace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.birthplace.Birthplace;
import ua.com.valexa.db.model.data.attribute.inn.Inn;
import ua.com.valexa.db.repository.data.attribute.birthplace.BirthplaceRepository;

import java.util.Optional;

@Service
public class BirthplaceService {

    @Autowired
    BirthplaceRepository birthplaceRepository;

    public Birthplace save(Birthplace birthplace) {
        try {
            return birthplaceRepository.save(birthplace);
        } catch (DataIntegrityViolationException e) {
            Optional<Birthplace> stored = birthplaceRepository.findByBirthplace(
                    birthplace.getBirthplace()
            );
            return stored.orElse(null);
        }
    }
}
