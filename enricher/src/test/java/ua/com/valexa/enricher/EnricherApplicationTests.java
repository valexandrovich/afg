package ua.com.valexa.enricher;

import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ua.com.valexa.db.model.User;
import ua.com.valexa.db.model.UserAt;
import ua.com.valexa.db.model.UserIn;
import ua.com.valexa.db.model.UserOn;
import ua.com.valexa.db.model.data.attribute.inn.Inn;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.model.data.base_object.PrivatePerson;
import ua.com.valexa.db.model.enums.LanguageCode;
import ua.com.valexa.db.repository.UserAtRepository;
import ua.com.valexa.db.repository.UserRepository;
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


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAtRepository userAtRepository;

    @Autowired
    UserOnRepository userOnRepository;

    @Autowired
    UserInRepository userInRepository;


    @Test
    void tst10(){

        User u = new User();
        u.setName("NAME");
        u = userRepository.save(u);

        UserAt ua = new UserAt();
        ua.setUser(u);
        ua.setValue(555);
        ua = userAtRepository.save(ua);

        UserAt ua2 = new UserAt();
        ua2.setUser(u);
        ua2.setValue(777);
        ua2 = userAtRepository.save(ua2);

        UserAt ua3 = new UserAt();
        ua3.setUser(u);
        ua3.setValue(333);
        ua3 = userAtRepository.save(ua3);

        UserOn uo = new UserOn();
        uo.setValue(1);
        uo.setUser(u);
        uo = userOnRepository.save(uo);

        UserOn uo1 = new UserOn();
        uo1.setValue(1);
        uo1.setUser(u);
        uo1 = userOnRepository.save(uo1);

        UserOn uo2 = new UserOn();
        uo2.setValue(1);
        uo2.setUser(u);
        uo2 = userOnRepository.save(uo2);

        UserIn ui = new UserIn();
        ui.setValue(1111);
        ui.setUser(u);
        ui = userInRepository.save(ui);

        UserIn ui1 = new UserIn();
        ui1.setValue(2222);
        ui1.setUser(u);
        ui1 = userInRepository.save(ui1);



    }

    @Test
//    @Transactional
    void tst11(){
        User u = userRepository.findById(202L).orElse(null);
        System.out.println(u.getName());
    }







}
