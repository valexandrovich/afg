package ua.com.valexa.enricher;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.test.annotation.Commit;
import ua.com.valexa.db.model.data.attribute.inn.Inn;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.model.data.base_object.Person;
import ua.com.valexa.db.model.data.base_object.PrivatePerson;
import ua.com.valexa.db.model.enums.LanguageCode;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;
import ua.com.valexa.db.model.stage.PrivatePersonStageRowArchive;
import ua.com.valexa.db.repository.PersonRepository;
import ua.com.valexa.db.repository.data.*;
import ua.com.valexa.db.repository.stage.PrivatePersonStageRowArchiveRepository;
import ua.com.valexa.db.repository.stage.PrivatePersonStageRowRepository;
import ua.com.valexa.db.service.PersonNameLinkService;
import ua.com.valexa.db.service.PersonNameService;
import ua.com.valexa.db.utils.NoVowelsHashUtils;

import java.time.LocalDateTime;
import java.util.*;

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
//        p.setId(UUID.fromString("77a6a232-7d0b-439e-8820-3947b400efea"));
//        privatePersonRepository.save(p);

        PersonName pn1 = new PersonName();
        pn1.setLastName("БЄЛОЄНКО");
        pn1.setFirstName("ВАЛЕРІЙ");
        pn1.setPatronymicName("ОЛЕКСАНДРОВИЧ");
        pn1.setLanguageCode(LanguageCode.UA);
        pn1.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn1));
//        pn1.generateId();
//        personNameRepository.save(pn1);
//
//        PersonName pn2 = new PersonName();
//        pn2.setLastName("БЕЛОЕНКО");
//        pn2.setFirstName("ВАЛЕРИЙ");
//        pn2.setPatronymicName("АЛЕКСАНДРОВИЧ");
//        pn2.setLanguageCode(LanguageCode.RU);
//        pn2.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn2));
//        pn2.generateId();
//        personNameRepository.save(pn2);
//
//
        PersonNameLink pnl1 = new PersonNameLink();
        pnl1.setSource("UNIT");
        pnl1.setPrivatePerson(p);
        pnl1.setPersonName(pn1);
        pnl1.setActualDate(LocalDateTime.now());
        pnl1.setIsActive(true);
//        pnl1.generateId();
        personNameLinkRepository.save(pnl1);
//
//        PersonNameLink pnl2 = new PersonNameLink();
//        pnl2.setSource("UNIT");
//        pnl2.setPrivatePerson(p);
//        pnl2.setPersonName(pn2);
//        pnl2.setActualDate(LocalDateTime.now());
//        pnl2.setIsActive(true);
//        pnl2.generateId();
//        personNameLinkRepository.save(pnl2);

//        Inn inn1 = new Inn();
//        inn1.setCode("3435603818");
//        inn1.generateId();
//        innRepository.save(inn1);
//
//        InnLink innLink1 = new InnLink();
//        innLink1.setInn(inn1);
//        innLink1.setPrivatePerson(p);
//        innLink1.setSource("UNIT");
//        innLink1.setActualDate(LocalDateTime.now());
//        innLink1.generateId();
//        innLinkRepository.save(innLink1);
//
//        Inn inn2 = new Inn();
//        inn2.setCode("77777777");
//        inn2.generateId();
//        innRepository.save(inn2);
//
//        InnLink innLink2 = new InnLink();
//        innLink2.setInn(inn2);
//        innLink2.setPrivatePerson(p);
//        innLink2.setSource("UNIT");
//        innLink2.setActualDate(LocalDateTime.now());
//        innLink2.generateId();
//        innLinkRepository.save(innLink2);
    }


    @Autowired
    PersonRepository personRepository;

    @Autowired
    EntityManager em;

    @Test
//    @Transactional
//    @Commit
    void tst11(){


        PersonName pn1 = new PersonName();
        pn1.setLastName("БЄЛОЄНКО");
        pn1.setFirstName("ВАЛЕРІЙ");
        pn1.setPatronymicName("ОЛЕКСАНДРОВИЧ");
        pn1.setLanguageCode(LanguageCode.UA);
        pn1.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn1));
        pn1.generateId();

        PersonName pn2 = new PersonName();
        pn2.setLastName("БЄЛОЄНКО");
        pn2.setFirstName("ВАЛЕРІЙ");
        pn2.setPatronymicName("ОЛЕКСАНДРОВИЧ");
        pn2.setLanguageCode(LanguageCode.UA);
        pn2.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn2));
        pn2.generateId();
//
        PersonName pn3 = new PersonName();
        pn3.setLastName("БЕЛОЕНКО");
        pn3.setFirstName("ВАЛЕРИЙ");
        pn3.setPatronymicName("АЛЕКСАНДРОВИЧ");
        pn3.setLanguageCode(LanguageCode.RU);
        pn3.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn3));
        pn3.generateId();

        PersonName pn4 = new PersonName();
        pn4.setLastName("БЕЛОЕНКОCC");
        pn4.setFirstName("ВАЛЕРИЙ");
        pn4.setPatronymicName("АЛЕКСАНДРОВИЧ");
        pn4.setLanguageCode(LanguageCode.RU);
//        pn4.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn4));
        pn4.setNoVowelsHash("АТАТАТАТАТАТАТ");
        pn4.generateId();
//
        Set<PersonName> personNames = new HashSet<>(Arrays.asList(pn1, pn2, pn3, pn4));
//
        personNameService.saveAll(personNames);
//
//        personNameRepository.saveAll(personNames);

//        UUID id = UUID.randomUUID();



//        privatePersonRepository.saveAll(persons);




    }

    @Autowired
    PrivatePersonStageRowRepository privatePersonStageRowRepository;


    @Autowired
    PrivatePersonStageRowArchiveRepository privatePersonStageRowArchiveRepository;
    @Test
    void tst12(){
        int batchSize = 1000;
        for (int i = 0; i < 1000; i++) {
            long startTime = System.currentTimeMillis();
            Slice<PrivatePersonStageRow> rows = privatePersonStageRowRepository.findAll(PageRequest.of(0, batchSize));

            if (rows.hasContent()){
                List<PrivatePersonStageRowArchive> archiveRows = new ArrayList<>();
                Set<PersonName> personNames = new HashSet<>();
                for(PrivatePersonStageRow row: rows.getContent()){

                    if (row.hasUaName()){
                        PersonName pn = new PersonName();
                        pn.setLanguageCode(LanguageCode.UA);
                        pn.setLastName(row.getLastNameUa());
                        pn.setFirstName(row.getFirstNameUa());
                        pn.setPatronymicName(row.getPatronymicNameUa());
                        pn.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn));
                        pn.generateId();
                        personNames.add(pn);
                    }

                    if (row.hasRuName()){
                        PersonName pn = new PersonName();
                        pn.setLanguageCode(LanguageCode.RU);
                        pn.setLastName(row.getLastNameRu());
                        pn.setFirstName(row.getFirstNameRu());
                        pn.setPatronymicName(row.getPatronymicNameRu());
                        pn.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn));
                        pn.generateId();
                        personNames.add(pn);
                    }

                    if (row.hasEnName()){
                        PersonName pn = new PersonName();
                        pn.setLanguageCode(LanguageCode.EN);
                        pn.setLastName(row.getLastNameEn());
                        pn.setFirstName(row.getFirstNameEn());
                        pn.setPatronymicName(row.getPatronymicNameEn());
                        pn.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn));
                        pn.generateId();
                        personNames.add(pn);
                    }


                }

                for (PrivatePersonStageRow row : rows.getContent()) {
                    archiveRows.add(new PrivatePersonStageRowArchive(row));
                }
                privatePersonStageRowArchiveRepository.saveAll(archiveRows);
                privatePersonStageRowRepository.deleteAll(rows.getContent());



                personNameService.saveAll(personNames);
            }

            long endTime = System.currentTimeMillis();
            long elapsedTimeMillis = endTime - startTime;
            long minutes = (elapsedTimeMillis / 1000) / 60;
            long seconds = (elapsedTimeMillis / 1000) % 60;
            long milliseconds = elapsedTimeMillis % 1000;
            System.out.printf( "I: " + i      + " :   %02d:%02d:%03d\n", minutes, seconds, milliseconds);

        }
    }

















}
