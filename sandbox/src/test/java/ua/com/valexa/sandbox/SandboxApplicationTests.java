package ua.com.valexa.sandbox;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import ua.com.valexa.db.model.data.attribute.inn.Inn;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.model.data.attribute.birthday.Birthday;
import ua.com.valexa.db.model.data.attribute.birthday.BirthdayPersonLink;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;
import ua.com.valexa.db.model.data.enums.LanguageCode;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;
import ua.com.valexa.db.repository.data.base_objects.PrivatePersonRepository;
import ua.com.valexa.db.repository.stage.PrivatePersonRowStageRepository;
import ua.com.valexa.db.service.data.attribute.birthday.BirthdayService;
import ua.com.valexa.db.service.data.attribute.birthday.PersonBirthdayLinkService;
import ua.com.valexa.db.service.data.attribute.inn.InnLinkService;
import ua.com.valexa.db.service.data.attribute.inn.InnService;
import ua.com.valexa.db.service.data.attribute.person_name.PersonNameLinkService;
import ua.com.valexa.db.service.data.attribute.person_name.PersonNameService;
import ua.com.valexa.db.service.data.base_objects.PrivatePersonService;
import ua.com.valexa.db.utils.NoVowelsHashUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest
class SandboxApplicationTests {


}
