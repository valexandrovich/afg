package ua.com.valexa.enricher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.inn.Inn;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.model.data.attribute.birthday.Birthday;
import ua.com.valexa.db.model.data.attribute.birthday.BirthdayPersonLink;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;
import ua.com.valexa.db.model.data.enums.LanguageCode;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;
import ua.com.valexa.db.repository.stage.PrivatePersonRowStageRepository;
import ua.com.valexa.db.service.data.attribute.address.AddressPersonLinkService;
import ua.com.valexa.db.service.data.attribute.address.AddressService;
import ua.com.valexa.db.service.data.attribute.address_simple.AddressSimplePersonLinkService;
import ua.com.valexa.db.service.data.attribute.address_simple.AddressSimpleService;
import ua.com.valexa.db.service.data.attribute.birthplace.BirthplaceLinkService;
import ua.com.valexa.db.service.data.attribute.birthplace.BirthplaceService;
import ua.com.valexa.db.service.data.attribute.birthday.BirthdayService;
import ua.com.valexa.db.service.data.attribute.birthday.PersonBirthdayLinkService;
import ua.com.valexa.db.service.data.attribute.inn.InnLinkService;
import ua.com.valexa.db.service.data.attribute.inn.InnService;
import ua.com.valexa.db.service.data.attribute.local_passport.LocalPassportLinkService;
import ua.com.valexa.db.service.data.attribute.local_passport.LocalPassportService;
import ua.com.valexa.db.service.data.attribute.person_name.PersonNameLinkService;
import ua.com.valexa.db.service.data.attribute.person_name.PersonNameService;
import ua.com.valexa.db.service.data.base_objects.PrivatePersonService;
import ua.com.valexa.db.utils.NoVowelsHashUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class PrivatePersonStageService {

    @Autowired
    PrivatePersonRowStageRepository privatePersonRowStageRepository;

    @Autowired
    PrivatePersonService privatePersonService;

    @Autowired
    PersonNameService personNameService;

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

    @Autowired
    BirthplaceService birthplaceService;

    @Autowired
    BirthplaceLinkService  birthplaceLinkService;

    @Autowired
    AddressSimpleService addressSimpleService;

    @Autowired
    AddressSimplePersonLinkService addressSimplePersonLinkService;

    @Autowired
    AddressService addressService;

    @Autowired
    AddressPersonLinkService addressPersonLinkService;

    @Autowired
    LocalPassportService localPassportService;

    @Autowired
    LocalPassportLinkService localPassportLinkService;


    public void enrichStageRow(UUID id) {
        PrivatePersonStageRow row = privatePersonRowStageRepository.findById(id).orElseThrow(() -> new RuntimeException("Private Person Stage Row not found!"));
        PrivatePerson candidate = findCandidate(row);
        System.out.println(candidate);


//        if (row.isPresent()){
//
//            System.out.println(candidates);
//        }
    }


    public void enrichStageRow(PrivatePersonStageRow row) {
        PrivatePerson candidate = findCandidate(row);

        CompletableFuture<Void> uaNameTask = null;
        CompletableFuture<Void> ruNameTask = null;
        CompletableFuture<Void> enNameTask = null;

        if (row.hasUaName()){
            uaNameTask = CompletableFuture.runAsync(() -> {
                PersonName pnUa = saveName(
                        row.getLastNameUa(),
                        row.getFirstNameUa(),
                        row.getPatronymicNameUa(),
                        LanguageCode.UA
                );

                PersonNameLink personNameLinkUa = new PersonNameLink();
                personNameLinkUa.setSource(row.getSource());
                personNameLinkUa.setCreatedAt(LocalDateTime.now());
                personNameLinkUa.setPrivatePerson(candidate);
                personNameLinkUa.setPersonName(pnUa);
                personNameLinkUa.generateId();
                personNameLinkService.save2(personNameLinkUa);
            });


        }

        if (row.hasRuName()){
            ruNameTask = CompletableFuture.runAsync(()->{
                PersonName pnRu = saveName(
                        row.getLastNameRu(),
                        row.getFirstNameRu(),
                        row.getPatronymicNameRu(),
                        LanguageCode.RU
                );

                PersonNameLink personNameLinkRu = new PersonNameLink();
                personNameLinkRu.setSource(row.getSource());
                personNameLinkRu.setCreatedAt(LocalDateTime.now());
                personNameLinkRu.setPrivatePerson(candidate);
                personNameLinkRu.setPersonName(pnRu);
                personNameLinkRu.generateId();
                personNameLinkService.save2(personNameLinkRu);
            });

        }

        if (row.hasEnName()){
            enNameTask = CompletableFuture.runAsync(()->{
                PersonName pnEn = saveName(
                        row.getLastNameEn(),
                        row.getFirstNameEn(),
                        row.getPatronymicNameEn(),
                        LanguageCode.EN
                );

                PersonNameLink personNameLinkEn = new PersonNameLink();
                personNameLinkEn.setSource(row.getSource());
                personNameLinkEn.setCreatedAt(LocalDateTime.now());
                personNameLinkEn.setPrivatePerson(candidate);
                personNameLinkEn.setPersonName(pnEn);
                personNameLinkEn.generateId();
                personNameLinkService.save2(personNameLinkEn);
            });
        }

        CompletableFuture<Void> allTasks = CompletableFuture.allOf(
                uaNameTask != null ? uaNameTask : CompletableFuture.completedFuture(null),
                ruNameTask != null ? ruNameTask : CompletableFuture.completedFuture(null),
                enNameTask != null ? enNameTask : CompletableFuture.completedFuture(null)
        );

        allTasks.join();

        if (row.getBirthday() != null){
            Birthday birthday = new Birthday();
            birthday.setBirthday(row.getBirthday());
            birthday.generateId();
            birthday = birthdayService.save2(birthday);

            BirthdayPersonLink pbl = new BirthdayPersonLink();
            pbl.setBirthday(birthday);
            pbl.setSource(row.getSource());
            pbl.setCreatedAt(LocalDateTime.now());
            pbl.setPrivatePerson(candidate);
            pbl.generateId();
            personBirthdayLinkService.save2(pbl);
        }

        if (row.getInn() != null && !row.getInn().isEmpty()){
            Inn inn = new Inn();
            inn.setCode(row.getInn());
            inn.generateId();
            inn = innService.save2(inn);

            InnLink il = new InnLink();
            il.setCreatedAt(LocalDateTime.now());
            il.setSource(row.getSource());
            il.setPrivatePerson(candidate);
            il.setInn(inn);
            il.generateId();
            innLinkService.save2(il);

        }

//        if (row.getBirthplace() != null && !row.getBirthplace().isEmpty()){
//            Birthplace birthplace = new Birthplace();
//            birthplace.setBirthplace(row.getBirthplace());
//            birthplace = birthplaceService.save(birthplace);
//
//            BithplaceLink bithplaceLink = new BithplaceLink();
//            bithplaceLink.setCreatedAt(LocalDateTime.now());
//            bithplaceLink.setSource(row.getSource());
//            bithplaceLink.setPrivatePerson(candidate);
//            bithplaceLink.setBirthplace(birthplace);
//            birthplaceLinkService.save(bithplaceLink);
//        }
//
//        if (row.getAddressSimple() != null && !row.getAddressSimple().isEmpty()){
//            AddressSimple addressSimple = new AddressSimple();
//            addressSimple.setAddress(row.getAddressSimple());
//            addressSimple = addressSimpleService.save(addressSimple);
//
//            AddressSimplePersonLink addressSimplePersonLink = new AddressSimplePersonLink();
//            addressSimplePersonLink.setSource(row.getSource());
//            addressSimplePersonLink.setCreatedAt(LocalDateTime.now());
//            addressSimplePersonLink.setPrivatePerson(candidate);
//            addressSimplePersonLink.setAddressSimple(addressSimple);
//            addressSimplePersonLinkService.save(addressSimplePersonLink);
//        }
//
//
//        if (
//                (row.getAddressCountry() != null && !row.getAddressCountry().isEmpty()) ||
//                (row.getAddressRegion() != null && !row.getAddressRegion().isEmpty()) ||
//                (row.getAddressCounty() != null && !row.getAddressCounty().isEmpty()) ||
//                (row.getAddressCityType() != null && !row.getAddressCityType().isEmpty()) ||
//                (row.getAddressCity() != null && !row.getAddressCity().isEmpty()) ||
//                (row.getAddressStreetType() != null && !row.getAddressStreetType().isEmpty()) ||
//                (row.getAddressStreet() != null && !row.getAddressStreet().isEmpty()) ||
//                (row.getAddressBuildingNumber() != null && !row.getAddressBuildingNumber().isEmpty()) ||
//                (row.getAddressBuildingLetter() != null && !row.getAddressBuildingLetter().isEmpty()) ||
//                (row.getAddressBuildingPart() != null && !row.getAddressBuildingPart().isEmpty()) ||
//                (row.getAddressApartment() != null && !row.getAddressApartment().isEmpty())
//        ){
//            Address address = new Address();
//            address.setCountry(row.getAddressCountry());
//            address.setRegion(row.getAddressRegion());
//            address.setCounty(row.getAddressCounty());
//            address.setCityType(row.getAddressCityType());
//            address.setCity(row.getAddressCity());
//            address.setStreetType(row.getAddressStreetType());
//            address.setStreet(row.getAddressStreet());
//            address.setBuildingNumber(row.getAddressBuildingNumber());
//            address.setBuildingLetter(row.getAddressBuildingLetter());
//            address.setBuildingPart(row.getAddressBuildingPart());
//            address.setApartment(row.getAddressApartment());
//
//            address = addressService.save(address);
//
//
//            AddressPersonLink addressPersonLink = new AddressPersonLink();
//            addressPersonLink.setAddress(address);
//            addressPersonLink.setPrivatePerson(candidate);
//            addressPersonLink.setCreatedAt(LocalDateTime.now());
//            addressPersonLink.setSource(row.getSource());
//            addressPersonLinkService.save(addressPersonLink);
//        }
//
//
//        if (
//                (row.getLocalPassportSerial() != null && !row.getLocalPassportSerial().isEmpty()) &&
//                        (row.getLocalPassportNumber() != null && !row.getLocalPassportNumber().isEmpty())
//        ){
//
//            System.out.println("SAVING LOCAL PASS");
//
//            LocalPassport localPassport = new LocalPassport();
//            localPassport.setSerial(row.getLocalPassportSerial());
//            localPassport.setNumber(row.getLocalPassportNumber());
//            localPassport.setIssuedAt(row.getLocalPassportIssueDate());
//            localPassport.setIssuerName(row.getLocalPassportIssuerName());
//            localPassport = localPassportService.save(localPassport);
//
//            LocalPassportLink localPassportLink = new LocalPassportLink();
//            localPassportLink.setLocalPassport(localPassport);
//            localPassportLink.setPrivatePerson(candidate);
//            localPassportLink.setCreatedAt(LocalDateTime.now());
//            localPassportLink.setSource(row.getSource());
//            localPassportLinkService.save(localPassportLink);
//
//        }


    }

    private PrivatePerson findCandidate(PrivatePersonStageRow row) {

        Set<PrivatePerson> candidates = new HashSet<>();
        PrivatePerson result;

        Set<String> noVowelsHashes = new HashSet<>();

        if (row.hasUaName()){
            PersonName nameUa = new PersonName();
            nameUa.setLastName(row.getLastNameUa());
            nameUa.setFirstName(row.getFirstNameUa());
            nameUa.setPatronymicName(row.getPatronymicNameUa());
            nameUa.setLanguageCode(LanguageCode.UA);
            String noVowelsHashUa = NoVowelsHashUtils.calcNoVowelsHash(nameUa);
            noVowelsHashes.add(noVowelsHashUa);
        }

        if (row.hasRuName()){
            PersonName nameRu = new PersonName();
            nameRu.setLastName(row.getLastNameRu());
            nameRu.setFirstName(row.getFirstNameRu());
            nameRu.setPatronymicName(row.getPatronymicNameRu());
            nameRu.setLanguageCode(LanguageCode.RU);
            String noVowelsHashRu = NoVowelsHashUtils.calcNoVowelsHash(nameRu);
            noVowelsHashes.add(noVowelsHashRu);
        }

        for (String noVowelsHash : noVowelsHashes){
            candidates.addAll(privatePersonService.findPersonsByNoVowelsHash(noVowelsHash));
        }

        if (candidates.isEmpty()) {
            result = new PrivatePerson();
            result.setId(UUID.randomUUID());
            return privatePersonService.save(result);
        } else {
            return filterCandidates(candidates, row);
        }
    }

    private PrivatePerson filterCandidates(Set<PrivatePerson> candidates, PrivatePersonStageRow row) {

        List<LocalDate> birthdays = new ArrayList<>();
        Set<PrivatePerson> filteredBirthday = new HashSet<>();
        if (row.getBirthday() != null) {
            birthdays.add(row.getBirthday());
        }
//        if (row.getCalculatedBirthday() != null) {
//            birthdays.add(row.getCalculatedBirthday());
//        }
        if (!birthdays.isEmpty()) {
            filteredBirthday = filterCandidatesByBirthday(candidates, birthdays);
        }

        Set<PrivatePerson> filteredInn = new HashSet<>();
        if (row.getInn() != null && !row.getInn().isEmpty()) {
            filteredInn = filterCandidatesByInn(candidates, row.getInn());
        }

        Set<PrivatePerson> filteredInnAndBirthday = new HashSet<>(filteredInn);
        filteredInnAndBirthday.retainAll(filteredBirthday);

        if (filteredInnAndBirthday.size() == 1) {
            return filteredInnAndBirthday.stream().findFirst().get();
        } else if (filteredBirthday.size() == 1) {
            return filteredBirthday.stream().findFirst().get();
        } else if (filteredInn.size() == 1) {
            return filteredInn.stream().findFirst().get();
        } else {
            PrivatePerson p = new PrivatePerson();
            p.setId(UUID.randomUUID());
            return privatePersonService.save(p);
        }
    }

    private Set<PrivatePerson> filterCandidatesByBirthday(Set<PrivatePerson> candidates, List<LocalDate> birthdays) {
        return candidates.stream()
                .filter(privatePerson -> privatePerson.getBirthdayLinks().stream()
                        .anyMatch(birthdayLink -> birthdays.contains(birthdayLink.getBirthday().getBirthday())))
                .collect(Collectors.toSet());
    }

    private Set<PrivatePerson> filterCandidatesByInn(Set<PrivatePerson> candidates, String inn) {
        return candidates.stream()
                .filter(privatePerson -> privatePerson.getInnLinks().stream()
                        .anyMatch(innLink -> innLink.getInn().getCode().equals(inn)))
                .collect(Collectors.toSet());
    }

    private PersonName saveName(String lastName, String firstName, String patronymicName, LanguageCode languageCode) {
        PersonName pn = new PersonName();
        pn.setLastName(lastName == null ? "" : lastName);
        pn.setFirstName(firstName== null ? "" :firstName);
        pn.setPatronymicName(patronymicName== null ? "" :patronymicName);
        pn.setLanguageCode(languageCode== null ? LanguageCode.UA :languageCode);
        pn.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn));
        pn.generateId();
        return personNameService.save2(pn);
    }

}
