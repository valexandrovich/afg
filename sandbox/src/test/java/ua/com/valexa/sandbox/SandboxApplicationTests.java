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

//    @Autowired
//    PersonNameRepository personNameRepository;

    @Autowired
    PersonNameService personNameService;

    @Autowired
    PrivatePersonRepository privatePersonRepository;

    @Autowired
    PersonNameLinkService personNameLinkService;

    @Autowired
    BirthdayService birthdayService;

    @Autowired
    PersonBirthdayLinkService personBirthdayLinkService;

    @Autowired
    InnService innService;

    @Autowired
    InnLinkService innLinkService;

    @Test
    void contextLoads() {

        Optional<PrivatePerson> p2 = privatePersonRepository.findById(UUID.fromString("f173c796-c404-4d51-acab-247bdaeadaeb"));
        PrivatePerson p = p2.get();

        PersonName pn = new PersonName();
        pn.setLastName("БЄЛОЄНКО");
        pn.setFirstName("ВАЛЕРІЙ");
        pn.setPatronymicName("");
        pn.setLanguageCode(LanguageCode.UA);
        pn.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn));
        pn = personNameService.savePersonName(pn);

        Birthday birthday = new Birthday();
        birthday.setBirthday(LocalDate.of(1994,1,23));
        birthday = birthdayService.saveDates(birthday);

        BirthdayPersonLink pbl = new BirthdayPersonLink();
        pbl.setPrivatePerson(p);
        pbl.setBirthday(birthday);
        pbl.setSource("UNIT");
        pbl.setCreatedAt(LocalDateTime.now());

        pbl = personBirthdayLinkService.savePersonBirthdayLink(pbl);

        Inn inn = new Inn();
        inn.setCode("3435603818");
        inn = innService.saveInn(inn);

        InnLink il = new InnLink();
        il.setInn(inn);
        il.setPrivatePerson(p);
        il.setSource("UNIT");
        il.setCreatedAt(LocalDateTime.now());
        il = innLinkService.saveInnLink(il);





        PersonNameLink pnl = new PersonNameLink();
        pnl.setCreatedAt(LocalDateTime.now());
        pnl.setPrivatePerson(p);
        pnl.setPersonName(pn);
        pnl.setSource("UNIT2");
        pnl = personNameLinkService.savePersonNameLink(pnl);

        Optional<PrivatePerson> p3 = privatePersonRepository.findById(p.getId());
        System.out.println(p3);




//        System.out.println(p2);


    }

    @Autowired
    PrivatePersonService privatePersonService;

    @Autowired
    PrivatePersonRowStageRepository privatePersonRowStageRepository;

    @Test
    void tst1(){

        Slice<PrivatePersonStageRow> batch = privatePersonRowStageRepository.findAllByIsHandled(false, PageRequest.of(0, 1));

        PrivatePersonStageRow row = batch.getContent().get(0);



//        PrivatePersonStageRow row = privatePersonRowStageRepository.findById(UUID.fromString("a0973e21-fbd0-4f5d-88a2-29bcd07b0cca")).orElseGet(null);
//        System.out.println(row);
    }

}
