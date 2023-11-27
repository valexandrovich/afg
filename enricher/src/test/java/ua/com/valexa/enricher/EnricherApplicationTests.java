package ua.com.valexa.enricher;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.valexa.db.model.data.attribute.inn.Inn;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.model.data.base_object.PrivatePerson;
import ua.com.valexa.db.model.enums.LanguageCode;
import ua.com.valexa.db.repository.data.*;
import ua.com.valexa.db.service.PersonNameLinkService;
import ua.com.valexa.db.service.PersonNameService;
import ua.com.valexa.db.utils.NoVowelsHashUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
class EnricherApplicationTests {


    @Autowired
    PrivatePersonRepository privatePersonRepository;

    @Autowired
    PersonNameRepository personNameRepository;

    @Autowired
    PersonNameLinkRepository personNameLinkRepository;

    @Autowired
    InnRepository innRepository;

    @Autowired
    InnLinkRepository innLinkRepository;

    @Test
    @Transactional
    void tst1(){
        PrivatePerson p =  privatePersonRepository.findById(UUID.fromString("5d58c589-e34c-4f99-bec8-b94de7a22c0b")).orElse(null);
        System.out.println("p");
    }




    @Test
    void fillDevData(){

        PrivatePerson p1 = new PrivatePerson();
//        p1.generateId();

        PersonName pn1 = new PersonName();
        pn1.setLastName("БЄЛОЄНКО");
        pn1.setFirstName("ВАЛЕРІЙ");
        pn1.setPatronymicName("ОЛЕКСАНДРОВИЧ");
        pn1.setLanguageCode(LanguageCode.UA);
        pn1.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn1));
        pn1.generateId();

        PersonNameLink pnl1 = new PersonNameLink();
        pnl1.setPrivatePerson(p1);
        pnl1.setPersonName(pn1);
        pnl1.setSource("UNIT");
        pnl1.setActualDate(LocalDateTime.now());
        pnl1.setIsActive(true);
//        pnl1.generateId();


        PersonName pn2 = new PersonName();
        pn2.setLastName("БЕЛОЕНКО");
        pn2.setFirstName("ВАЛЕРИЙ");
        pn2.setPatronymicName("АЛЕКСАНДРОВИЧ");
        pn2.setLanguageCode(LanguageCode.RU);
        pn2.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn2));
//        pn2.generateId();

        PersonNameLink pnl2 = new PersonNameLink();
        pnl2.setPrivatePerson(p1);
        pnl2.setPersonName(pn2);
        pnl2.setSource("UNIT");
        pnl2.setActualDate(LocalDateTime.now());
        pnl2.setIsActive(true);
//        pnl2.generateId();

        personNameRepository.save(pn1);
        personNameRepository.save(pn2);

        privatePersonRepository.save(p1);


        personNameLinkRepository.save(pnl1);
        personNameLinkRepository.save(pnl2);

        System.out.println("");
    }

    @Autowired
    PersonNameService personNameService;

    @Autowired
    PersonNameLinkService personNameLinkService;
    @Test
    void tst8(){
        PrivatePerson p = new PrivatePerson();
//        p.generateId();
        p.setId(UUID.fromString("77a6a232-7d0b-439e-8820-3947b400efea"));
        privatePersonRepository.save(p);

        PersonName pn1 = new PersonName();
        pn1.setLastName("БЄЛОЄНКО");
        pn1.setFirstName("ВАЛЕРІЙ");
        pn1.setPatronymicName("ОЛЕКСАНДРОВИЧ");
        pn1.setLanguageCode(LanguageCode.UA);
        pn1.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn1));
        pn1.generateId();
        personNameRepository.save(pn1);

        PersonName pn2 = new PersonName();
        pn2.setLastName("БЕЛОЕНКО");
        pn2.setFirstName("ВАЛЕРИЙ");
        pn2.setPatronymicName("АЛЕКСАНДРОВИЧ");
        pn2.setLanguageCode(LanguageCode.RU);
        pn2.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn2));
        pn2.generateId();
        personNameRepository.save(pn2);


        PersonNameLink pnl1 = new PersonNameLink();
        pnl1.setSource("UNIT");
        pnl1.setPrivatePerson(p);
        pnl1.setPersonName(pn1);
        pnl1.setActualDate(LocalDateTime.now());
        pnl1.setIsActive(true);
        pnl1.generateId();
        personNameLinkRepository.save(pnl1);

        PersonNameLink pnl2 = new PersonNameLink();
        pnl2.setSource("UNIT");
        pnl2.setPrivatePerson(p);
        pnl2.setPersonName(pn2);
        pnl2.setActualDate(LocalDateTime.now());
        pnl2.setIsActive(true);
        pnl2.generateId();
        personNameLinkRepository.save(pnl2);

        Inn inn1 = new Inn();
        inn1.setCode("3435603818");
        inn1.generateId();
        innRepository.save(inn1);

        InnLink innLink1 = new InnLink();
        innLink1.setInn(inn1);
        innLink1.setPrivatePerson(p);
        innLink1.setSource("UNIT");
        innLink1.setActualDate(LocalDateTime.now());
        innLink1.generateId();
        innLinkRepository.save(innLink1);

        Inn inn2 = new Inn();
        inn2.setCode("77777777");
        inn2.generateId();
        innRepository.save(inn2);

        InnLink innLink2 = new InnLink();
        innLink2.setInn(inn2);
        innLink2.setPrivatePerson(p);
        innLink2.setSource("UNIT");
        innLink2.setActualDate(LocalDateTime.now());
        innLink2.generateId();
        innLinkRepository.save(innLink2);
    }

    @Test
    @Transactional
    void tst9(){
        PrivatePerson p = privatePersonRepository.findById(UUID.fromString("77a6a232-7d0b-439e-8820-3947b400efea")).orElse(null);
        System.out.println(p);
    }













}
