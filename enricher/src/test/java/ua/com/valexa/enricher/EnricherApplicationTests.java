package ua.com.valexa.enricher;

import jakarta.transaction.Transactional;
import org.checkerframework.checker.units.qual.A;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import ua.com.valexa.db.model.data.attribute.inn.Inn;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.model.data.base_object.PrivatePerson;
import ua.com.valexa.db.model.data.tag.Tag;
import ua.com.valexa.db.model.data.tag.TagAttributeLink;
import ua.com.valexa.db.model.data.tag.TagType;
import ua.com.valexa.db.model.enums.LanguageCode;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;
import ua.com.valexa.db.model.stage.PrivatePersonStageRowArchive;
import ua.com.valexa.db.repository.*;
import ua.com.valexa.db.repository.data.attribute.inn.InnLinkRepository;
import ua.com.valexa.db.repository.data.attribute.inn.InnRepository;
import ua.com.valexa.db.repository.data.attribute.person_name.PersonNameLinkRepository;
import ua.com.valexa.db.repository.data.attribute.person_name.PersonNameRepository;
import ua.com.valexa.db.repository.data.base_object.PrivatePersonRepository;
import ua.com.valexa.db.repository.stage.PrivatePersonStageRowArchiveRepository;
import ua.com.valexa.db.repository.stage.PrivatePersonStageRowRepository;
import ua.com.valexa.db.service.data.attribute.person_name.PersonNameService;
import ua.com.valexa.db.service.data.base_object.PrivatePersonService;
import ua.com.valexa.db.utils.NoVowelsHashUtils;
import ua.com.valexa.enricher.service.PrivatePersonStageService;
import ua.com.valexa.enricher.service.TestService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
class EnricherApplicationTests {

    @Autowired
    PrivatePersonRepository privatePersonRepository;

    @Autowired
    PersonNameRepository personNameRepository;

    @Autowired
    PersonNameService personNameService;

    @Autowired
    PersonNameLinkRepository personNameLinkRepository;

    @Autowired
    PrivatePersonStageRowRepository privatePersonStageRowRepository;

    @Autowired
    PrivatePersonStageService privatePersonStageService;

    @Autowired
    InnRepository innRepository;

    @Autowired
    InnLinkRepository innLinkRepository;

    @Autowired
    TagTypeRepository tagTypeRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    TagAttributeLinkRepository tagAttributeLinkRepository;


    @Test
    void tst1() {
        PrivatePerson p = new PrivatePerson();
        p.setId(UUID.fromString("a26cb6ef-d8ed-4ae3-9025-b85cde8f2a27"));
        p = privatePersonRepository.save(p);

        PersonName pn = new PersonName();
        pn.setLastName("БЄЛОЄНКО");
        pn.setFirstName("ВАЛЕРІЙ");
        pn.setPatronymicName("ОЛЕКСАНДРОВИЧ");
        pn.setLanguageCode(LanguageCode.UA);
        pn.setNoVowelsHash("БЛНКВЛРЛКСНДРВЧ");
        pn.generateId();

//        personNameRepository.save(pn);

        PersonNameLink pnl = new PersonNameLink();
        pnl.setPrivatePerson(p);
        pnl.setPersonName(pn);
        pnl.setSource("UNIT");
        pnl.setActualDate(LocalDateTime.now());
        pnl.generateId();

        personNameLinkRepository.save(pnl);

        Inn inn = new Inn();
        inn.setCode("3435603818");
        inn.generateId();


        InnLink il = new InnLink();
        il.setPrivatePerson(p);
        il.setInn(inn);
        il.setActualDate(LocalDateTime.now());
        il.setSource("UNIT");
        il.generateId();

        innLinkRepository.save(il);


        TagType tt1 = new TagType();
        tt1.setId("TT1");
        tt1.setDescription("TAG_1_DESC");
        tagTypeRepository.save(tt1);

        TagType tt2 = new TagType();
        tt2.setId("TT2");
        tt2.setDescription("TAG_2_DESC");
        tagTypeRepository.save(tt2);

        Tag t = new Tag();
        t.setId(UUID.randomUUID());
        t.setStartAt(LocalDate.now());
        t.setTagType(tt1);
        tagRepository.save(t);


        TagAttributeLink tal = new TagAttributeLink();
        tal.setAttribute(inn);
        tal.setTag(t);
        tal.setId(UUID.randomUUID());
        tagAttributeLinkRepository.save(tal);


    }


    @Test
    void tstLoop() {

        int batchSize = 1000;

        for (int i = 1; i <= 1000; i++) {

            long startTime = System.currentTimeMillis();
            tst2(batchSize);
            long endTime = System.currentTimeMillis();
            long elapsedTimeMillis = endTime - startTime;

            // Convert milliseconds to minutes, seconds, and milliseconds
            long minutes = (elapsedTimeMillis / 1000) / 60;
            long seconds = (elapsedTimeMillis / 1000) % 60;
            long milliseconds = elapsedTimeMillis % 1000;
            System.out.printf("I( " + (i * batchSize) + " ): " + i + " :   %02d:%02d:%03d\n", minutes, seconds, milliseconds);

        }
    }


    @Autowired
    PrivatePersonStageRowArchiveRepository privatePersonStageRowArchiveRepository;

    @Test
    void tst2(Integer batchSize) {

        long startTime = System.currentTimeMillis();
        Slice<PrivatePersonStageRow> rows = privatePersonStageRowRepository.findBy(PageRequest.of(0, batchSize));
        long endTime = System.currentTimeMillis();
        long elapsedTimeMillis = endTime - startTime;
        long minutes = (elapsedTimeMillis / 1000) / 60;
        long seconds = (elapsedTimeMillis / 1000) % 60;
        long milliseconds = elapsedTimeMillis % 1000;
        System.out.printf( "FETCHING STAGE"      + " :   %02d:%02d:%03d\n", minutes, seconds, milliseconds);


        if (rows.hasContent()) {

            List<PrivatePersonStageRowArchive> archiveRows = new ArrayList<>();

            startTime = System.currentTimeMillis();
            privatePersonStageService.enrichRows(rows.getContent());
            endTime = System.currentTimeMillis();
             elapsedTimeMillis = endTime - startTime;
             minutes = (elapsedTimeMillis / 1000) / 60;
             seconds = (elapsedTimeMillis / 1000) % 60;
             milliseconds = elapsedTimeMillis % 1000;
            System.out.printf( "ENRICH ROWS"      + " :   %02d:%02d:%03d\n", minutes, seconds, milliseconds);

            for (PrivatePersonStageRow row : rows.getContent()) {
                archiveRows.add(new PrivatePersonStageRowArchive(row));
            }
            startTime = System.currentTimeMillis();
            privatePersonStageRowArchiveRepository.saveAll(archiveRows);
            endTime = System.currentTimeMillis();
            elapsedTimeMillis = endTime - startTime;
            minutes = (elapsedTimeMillis / 1000) / 60;
            seconds = (elapsedTimeMillis / 1000) % 60;
            milliseconds = elapsedTimeMillis % 1000;
            System.out.printf( "SAVE ARCHIVE ROWS"      + " :   %02d:%02d:%03d\n", minutes, seconds, milliseconds);

            startTime = System.currentTimeMillis();
            privatePersonStageRowRepository.deleteAll(rows.getContent());
            endTime = System.currentTimeMillis();
            elapsedTimeMillis = endTime - startTime;
            minutes = (elapsedTimeMillis / 1000) / 60;
            seconds = (elapsedTimeMillis / 1000) % 60;
            milliseconds = elapsedTimeMillis % 1000;
            System.out.printf( "DELETE STAGE ROWS"      + " :   %02d:%02d:%03d\n", minutes, seconds, milliseconds);


        }


    }

    @Autowired
    PrivatePersonService privatePersonService;

    @Autowired
    TestService testService;

    @Test
//    @Transactional
    void tst3() {

        List<PrivatePersonStageRow> rows = new ArrayList<>();

//        rows.add(rowNameUaInn());

        rows.add(rowFull());
//        rows.add(rowTemp());
//        rows.add(rowUaNameInn());
//        rows.add(rowUaNameBirthday());
        privatePersonStageService.enrichRows(rows);


    }

    PrivatePersonStageRow rowTemp(){
        PrivatePersonStageRow row = new PrivatePersonStageRow();
        row.setId(UUID.randomUUID());
        row.setSource("UNIT");

//        row.setLastNameUa("БІЛОЄНКО");
//        row.setFirstNameUa("ВOЛЕРІЙ");
//        row.setPatronymicNameUa("АЛЕКСАНДРОВИЧ");

        row.setLastNameRu("БЕЛОЕНКО");
        row.setFirstNameRu("ВАЛЕРИЙ");
        row.setPatronymicNameRu("АЛЕКСАНДРОВИЧ");

//        row.setLastNameEn("BIELOIENKO");
//        row.setFirstNameEn("VALERII");
//        row.setPatronymicNameEn("OLEKSANDROVICH");

//        row.setBirthday(LocalDate.of(1994,1,23));

//        row.setBirthplace("УКРАЇНА, ДОНЕЦЬКА ОБЛ., М. ГОРЛІВКА");

        row.setInn("3435603818");

        row.setLocalPassportSerial("AA");
        row.setLocalPassportNumber("123456");

//        row.setAddressSimple("УКРАЇНА, ДОНЕЦЬКА ОБЛ., М. ГОРЛІВКА, ВУЛ. ХАСАНІВСЬКА, Б. 10, КВ. 3");

//        row.setAddressCountry("УКРАЇНА");
//        row.setAddressRegion("ДОНЕЦЬКА");
//        row.setAddressCounty("ЯСИНУВАТСЬКИЙ");
//        row.setAddressCityType("М");
//        row.setAddressCity("ГОРЛОІВКА");
//        row.setAddressStreetType("ВУЛ");
//        row.setAddressStreet("ХАСАНІВСЬКА");
//        row.setAddressBuildingNumber("10");
//        row.setAddressBuildingLetter("А");
//        row.setAddressBuildingPart("1");
//        row.setAddressApartment("3");

        return row;



    }

    PrivatePersonStageRow rowFull(){
        PrivatePersonStageRow row = new PrivatePersonStageRow();
        row.setId(UUID.randomUUID());
        row.setSource("UNIT");

        row.setLastNameUa("БЄЛОЄНКО");
        row.setFirstNameUa("ВАЛЕРІЙ");
        row.setPatronymicNameUa("ОЛЕКСАНДРОВИЧ");

        row.setLastNameRu("БЕЛОЕНКО");
        row.setFirstNameRu("ВАЛЕРИЙ");
        row.setPatronymicNameRu("АЛЕКСАНДРОВИЧ");

        row.setLastNameEn("BIELOIENKO");
        row.setFirstNameEn("VALERII");
        row.setPatronymicNameEn("OLEKSANDROVICH");

        row.setBirthday(LocalDate.of(1994,1,23));

        row.setBirthplace("УКРАЇНА, ДОНЕЦЬКА ОБЛ., М. ГОРЛІВКА");

        row.setInn("3435603818");

        row.setLocalPassportSerial("AA");
        row.setLocalPassportNumber("123456");

        row.setAddressSimple("УКРАЇНА, ДОНЕЦЬКА ОБЛ., М. ГОРЛІВКА, ВУЛ. ХАСАНІВСЬКА, Б. 10, КВ. 3");

        row.setAddressCountry("УКРАЇНА");
        row.setAddressRegion("ДОНЕЦЬКА");
        row.setAddressCounty("ЯСИНУВАТСЬКИЙ");
        row.setAddressCityType("М");
        row.setAddressCity("ГОРЛОІВКА");
        row.setAddressStreetType("ВУЛ");
        row.setAddressStreet("ХАСАНІВСЬКА");
        row.setAddressBuildingNumber("10");
        row.setAddressBuildingLetter("А");
        row.setAddressBuildingPart("1");
        row.setAddressApartment("3");

        return row;



    }


    PrivatePersonStageRow rowNameUaInn(){
        PrivatePersonStageRow row = new PrivatePersonStageRow();
        row.setId(UUID.randomUUID());
        row.setSource("UNIT");

        row.setLastNameUa("БЄЛОЄНКО");
        row.setFirstNameUa("ВАЛЕРІЙ");
        row.setPatronymicNameUa("ОЛЕКСАНДРОВИЧ");


//        row.setBirthday(LocalDate.of(1994,1,23));


        row.setInn("3435603818");



        return row;



    }

    PrivatePersonStageRow rowUaNameInn(){
        PrivatePersonStageRow row = new PrivatePersonStageRow();
        row.setId(UUID.randomUUID());
        row.setSource("UNIT");
        row.setLastNameUa("БЄЛОЄНКО");
        row.setFirstNameUa("ВАЛЕРІЙ");
        row.setPatronymicNameUa("ОЛЕКСАНДРОВИЧ");
        row.setInn("3435603818");
        return row;
    }

    PrivatePersonStageRow rowUaNameBirthday(){
        PrivatePersonStageRow row = new PrivatePersonStageRow();
        row.setId(UUID.randomUUID());
        row.setSource("UNIT");
        row.setLastNameUa("БЄЛОЄНКО");
        row.setFirstNameUa("ВАЛЕРІЙ");
        row.setPatronymicNameUa("ОЛЕКСАНДРОВИЧ");
        row.setBirthday(LocalDate.of(1994,1,23));
        return row;
    }



    @Test
    void tst5(){
        PersonName pn = new PersonName();
        pn.setLastName("БЄЛОЄНКО");
        pn.setFirstName("ВАЛЕРІЙ");
        pn.setPatronymicName("ОЛЕКСАНДРОВИЧ");
        pn.setLanguageCode(LanguageCode.UA);
        pn.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn));
        pn.generateId();

        System.out.println(pn.getId());
        System.out.println(pn);
    }


    @Test
    void tst6(){
        int batchSize = 1000;
        Slice<PrivatePersonStageRow> rows = privatePersonStageRowRepository.findBy(PageRequest.of(0, batchSize));

        System.out.println(rows.getContent().size());


        Set<String> hashes = new HashSet<>();

        for (PrivatePersonStageRow row : rows.getContent()){

            if (row.hasUaName()){
                PersonName pn = new PersonName();
                pn.setLanguageCode(LanguageCode.UA);
                pn.setLastName(row.getLastNameUa());
                pn.setFirstName(row.getFirstNameUa());
                pn.setPatronymicName(row.getPatronymicNameUa());
                pn.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn));
                hashes.add(pn.getNoVowelsHash());
            }

            if (row.hasRuName()){
                PersonName pn = new PersonName();
                pn.setLanguageCode(LanguageCode.RU);
                pn.setLastName(row.getLastNameRu());
                pn.setFirstName(row.getFirstNameRu());
                pn.setPatronymicName(row.getPatronymicNameRu());
                pn.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn));
                hashes.add(pn.getNoVowelsHash());
            }
        }

        System.out.println("HASHES: " + hashes.size());

        CompletableFuture<Set<PersonName>> personNameFuture =  personNameService.findByNoVowelsHashes(hashes);
        Set<PersonName> personNames = personNameFuture.join();
        System.out.println("PERSON NAMES CANDIDATES: " + personNames.size());

//        CompletableFuture<Set<PrivatePerson>> allCandidatesFuture =  privatePersonService.findCandidatesByNoVowelsHashes(hashes);
//        Set<PrivatePerson> allCandidates = allCandidatesFuture.join();
//
//        System.out.println("CANDIDATES: " + allCandidates.size());



    }




}
