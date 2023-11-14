package ua.com.valexa.db.service.data.attribute.birthday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.birthday.Birthday;
import ua.com.valexa.db.repository.data.attribute.birthday.BirthdayRepository;

import java.util.Optional;

@Service
public class BirthdayService {
    @Autowired
    BirthdayRepository birthdayRepository;

    public Birthday save(Birthday birthday) {
        try {
            return birthdayRepository.save(birthday);
        } catch (DataIntegrityViolationException e) {
            Optional<Birthday> storedDates = birthdayRepository.findByBirthday(
                    birthday.getBirthday()
            );
            return storedDates.orElse(null);
        }
    }

    public Birthday save2(Birthday birthday) {
      return birthdayRepository.save(birthday);
    }
}
