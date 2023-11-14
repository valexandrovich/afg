package ua.com.valexa.db.service.data.attribute.inn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.inn.Inn;
import ua.com.valexa.db.repository.data.attribute.inn.InnRepository;

import java.util.Optional;

@Service
public class InnService {
        @Autowired
        InnRepository innRepository;

        public Inn save(Inn inn) {
            try {
                return innRepository.save(inn);
            } catch (DataIntegrityViolationException e) {
                Optional<Inn> storedInn = innRepository.findByCode(
                        inn.getCode()
                );
                return storedInn.orElse(null);
            }
        }

    public Inn save2(Inn inn) {
       return innRepository.save(inn);
    }
}
