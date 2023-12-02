package ua.com.valexa.enricher.service;

import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.valexa.db.model.data.attribute.address.Address;
import ua.com.valexa.db.model.data.attribute.address.AdressPersonLink;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimple;
import ua.com.valexa.db.model.data.attribute.address_simple.AdressSimplePersonLink;
import ua.com.valexa.db.model.data.attribute.birthday.Birthday;
import ua.com.valexa.db.model.data.attribute.birthday.BirthdayLink;
import ua.com.valexa.db.model.data.attribute.birthplace.Birthplace;
import ua.com.valexa.db.model.data.attribute.birthplace.BirthplaceLink;
import ua.com.valexa.db.model.data.attribute.inn.Inn;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.model.data.attribute.local_passport.LocalPassport;
import ua.com.valexa.db.model.data.attribute.local_passport.LocalPassportLink;
import ua.com.valexa.db.model.data.attribute.person_name.PersonName;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;
import ua.com.valexa.db.model.data.base_object.PrivatePerson;
import ua.com.valexa.db.model.enums.LanguageCode;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;
import ua.com.valexa.db.service.data.attribute.address.AddressPersonLinkService;
import ua.com.valexa.db.service.data.attribute.address.AddressService;
import ua.com.valexa.db.service.data.attribute.address_simple.AddressSimplePersonLinkService;
import ua.com.valexa.db.service.data.attribute.address_simple.AddressSimpleService;
import ua.com.valexa.db.service.data.attribute.birthday.BirthdayLinkService;
import ua.com.valexa.db.service.data.attribute.birthday.BirthdayService;
import ua.com.valexa.db.service.data.attribute.birthplace.BirthplaceLinkService;
import ua.com.valexa.db.service.data.attribute.birthplace.BirthplaceService;
import ua.com.valexa.db.service.data.attribute.inn.InnLinkService;
import ua.com.valexa.db.service.data.attribute.inn.InnService;
import ua.com.valexa.db.service.data.attribute.local_passport.LocalPassportLinkService;
import ua.com.valexa.db.service.data.attribute.local_passport.LocalPassportService;
import ua.com.valexa.db.service.data.attribute.person_name.PersonNameLinkService;
import ua.com.valexa.db.service.data.attribute.person_name.PersonNameService;
import ua.com.valexa.db.service.data.base_object.PrivatePersonService;
import ua.com.valexa.db.utils.NoVowelsHashUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class PrivatePersonStageService {

    @Autowired
    PrivatePersonService privatePersonService;

    @Autowired
    PersonNameService personNameService;

    @Autowired
    PersonNameLinkService personNameLinkService;

    @Autowired
    InnService innService;

    @Autowired
    InnLinkService innLinkService;

    @Autowired
    BirthdayService birthdayService;

    @Autowired
    BirthdayLinkService birthdayLinkService;

    @Autowired
    BirthplaceService birthplaceService;

    @Autowired
    BirthplaceLinkService birthplaceLinkService;

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


    @Transactional
    public void enrichRows2(List<PrivatePersonStageRow> rows) {
        System.out.println(rows.size());
    }

    @Transactional
    public void enrichRows(List<PrivatePersonStageRow> rows) {
        Set<PrivatePerson> personsToSave = new HashSet<>();

        Set<PersonName> personNamesToSave = new HashSet<>();
        Set<PersonNameLink> personNameLinksToSave = new HashSet<>();

        Set<Inn> innsToSave = new HashSet<>();
        Set<InnLink> innLinksToSave = new HashSet<>();

        Set<Birthday> birthdaysToSave = new HashSet<>();
        Set<BirthdayLink> birthdayLinksToSave = new HashSet<>();

        Set<Birthplace> birthplacesToSave = new HashSet<>();
        Set<BirthplaceLink> birthplacesLinksToSave = new HashSet<>();

        Set<AddressSimple> addressSimplesToSave = new HashSet<>();
        Set<AdressSimplePersonLink> adressSimplePersonLinksToSave = new HashSet<>();

        Set<Address> addressesToSave = new HashSet<>();
        Set<AdressPersonLink> adressPersonLinksToSave = new HashSet<>();

        Set<LocalPassport> localPassportsToSave = new HashSet<>();
        Set<LocalPassportLink> localPassportLinksToSave = new HashSet<>();


        long startTime = System.currentTimeMillis();
        for (PrivatePersonStageRow row : rows) {

            PrivatePerson candidate = findCandidate(row);
            if (candidate == null) {
                candidate = findCandidatesInPreSave(row, personNameLinksToSave);
                if (candidate == null){
                    candidate = new PrivatePerson();
                    candidate.setId(UUID.randomUUID());
                    personsToSave.add(candidate);
                }
            }


            if (row.hasUaName()) {
                PersonName pn = new PersonName();
                pn.setLastName(row.getLastNameUa());
                pn.setFirstName(row.getFirstNameUa());
                pn.setPatronymicName(row.getPatronymicNameUa());
                pn.setLanguageCode(LanguageCode.UA);
                pn.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn));
                pn.generateId();
                personNamesToSave.add(pn);

                PersonNameLink pnl = new PersonNameLink();
                pnl.setPrivatePerson(candidate);
                pnl.setPersonName(pn);
                pnl.setActualDate(LocalDateTime.now());
                pnl.setSource(row.getSource());
                pnl.generateId();
                candidate.getPersonNameLinks().add(pnl);
                personNameLinksToSave.add(pnl);
            }

            if (row.hasRuName()) {
                PersonName pn = new PersonName();
                pn.setLastName(row.getLastNameRu());
                pn.setFirstName(row.getFirstNameRu());
                pn.setPatronymicName(row.getPatronymicNameRu());
                pn.setLanguageCode(LanguageCode.RU);
                pn.setNoVowelsHash(NoVowelsHashUtils.calcNoVowelsHash(pn));
                pn.generateId();
                personNamesToSave.add(pn);

                PersonNameLink pnl = new PersonNameLink();
                pnl.setPrivatePerson(candidate);
                pnl.setPersonName(pn);
                pnl.setActualDate(LocalDateTime.now());
                pnl.setSource(row.getSource());
                pnl.generateId();
                candidate.getPersonNameLinks().add(pnl);
                personNameLinksToSave.add(pnl);
            }

            if (row.hasEnName()) {
                PersonName pn = new PersonName();
                pn.setLastName(row.getLastNameEn());
                pn.setFirstName(row.getFirstNameEn());
                pn.setPatronymicName(row.getPatronymicNameEn());
                pn.setLanguageCode(LanguageCode.EN);
                pn.generateId();
                personNamesToSave.add(pn);

                PersonNameLink pnl = new PersonNameLink();
                pnl.setPrivatePerson(candidate);
                pnl.setPersonName(pn);
                pnl.setActualDate(LocalDateTime.now());
                pnl.setSource(row.getSource());
                pnl.generateId();
                candidate.getPersonNameLinks().add(pnl);
                personNameLinksToSave.add(pnl);
            }

            if (row.getInn() != null && !row.getInn().isEmpty()) {
                Inn inn = new Inn();
                inn.setCode(row.getInn());
                inn.generateId();
                innsToSave.add(inn);

                InnLink innLink = new InnLink();
                innLink.setPrivatePerson(candidate);
                innLink.setInn(inn);
                innLink.setSource(row.getSource());
                innLink.setActualDate(LocalDateTime.now());
                innLink.generateId();
                candidate.getInnLinks().add(innLink);
                innLinksToSave.add(innLink);
            }

            if (row.getBirthday() != null) {
                Birthday birthday = new Birthday();
                birthday.setBirthday(row.getBirthday());
                birthday.generateId();
                birthdaysToSave.add(birthday);

                BirthdayLink birthdayLink = new BirthdayLink();
                birthdayLink.setPrivatePerson(candidate);
                birthdayLink.setBirthday(birthday);
                birthdayLink.setActualDate(LocalDateTime.now());
                birthdayLink.setSource(row.getSource());
                birthdayLink.generateId();
                candidate.getBirthdayLinks().add(birthdayLink);
                birthdayLinksToSave.add(birthdayLink);
            }

            if (row.getBirthplace() != null && !row.getBirthplace().isEmpty()) {
                Birthplace birthplace = new Birthplace();
                birthplace.setBirthplace(row.getBirthplace());
                birthplace.generateId();
                birthplacesToSave.add(birthplace);

                BirthplaceLink birthplaceLink = new BirthplaceLink();
                birthplaceLink.setActualDate(LocalDateTime.now());
                birthplaceLink.setSource(row.getSource());
                birthplaceLink.setPrivatePerson(candidate);
                birthplaceLink.setBirthplace(birthplace);
                birthplaceLink.generateId();
                birthplacesLinksToSave.add(birthplaceLink);
            }

            if (row.getAddressSimple() != null && !row.getAddressSimple().isEmpty()) {
                AddressSimple addressSimple = new AddressSimple();
                addressSimple.setAddress(row.getAddressSimple().substring(0, Math.min(254, row.getAddressSimple().length())));
                addressSimple.generateId();
                addressSimplesToSave.add(addressSimple);

                AdressSimplePersonLink addressSimplePersonLink = new AdressSimplePersonLink();
                addressSimplePersonLink.setSource(row.getSource());
                addressSimplePersonLink.setActualDate(LocalDateTime.now());
                addressSimplePersonLink.setPrivatePerson(candidate);
                addressSimplePersonLink.setAddressSimple(addressSimple);
                addressSimplePersonLink.generateId();
                adressSimplePersonLinksToSave.add(addressSimplePersonLink);
            }


            if (
                    (row.getAddressCountry() != null && !row.getAddressCountry().isEmpty()) ||
                            (row.getAddressRegion() != null && !row.getAddressRegion().isEmpty()) ||
                            (row.getAddressCounty() != null && !row.getAddressCounty().isEmpty()) ||
                            (row.getAddressCityType() != null && !row.getAddressCityType().isEmpty()) ||
                            (row.getAddressCity() != null && !row.getAddressCity().isEmpty()) ||
                            (row.getAddressStreetType() != null && !row.getAddressStreetType().isEmpty()) ||
                            (row.getAddressStreet() != null && !row.getAddressStreet().isEmpty()) ||
                            (row.getAddressBuildingNumber() != null && !row.getAddressBuildingNumber().isEmpty()) ||
                            (row.getAddressBuildingLetter() != null && !row.getAddressBuildingLetter().isEmpty()) ||
                            (row.getAddressBuildingPart() != null && !row.getAddressBuildingPart().isEmpty()) ||
                            (row.getAddressApartment() != null && !row.getAddressApartment().isEmpty())
            ) {
                Address address = new Address();
                address.setCountry(row.getAddressCountry());
                address.setRegion(row.getAddressRegion());
                address.setCounty(row.getAddressCounty());
                address.setCityType(row.getAddressCityType());
                address.setCity(row.getAddressCity());
                address.setStreetType(row.getAddressStreetType());
                address.setStreet(row.getAddressStreet());
                address.setBuildingNumber(row.getAddressBuildingNumber());
                address.setBuildingLetter(row.getAddressBuildingLetter());
                address.setBuildingPart(row.getAddressBuildingPart());
                address.setApartment(row.getAddressApartment());
                address.generateId();
                addressesToSave.add(address);

                AdressPersonLink addressPersonLink = new AdressPersonLink();
                addressPersonLink.setAddress(address);
                addressPersonLink.setPrivatePerson(candidate);
                addressPersonLink.setActualDate(LocalDateTime.now());
                addressPersonLink.setSource(row.getSource());
                addressPersonLink.generateId();
                adressPersonLinksToSave.add(addressPersonLink);
            }


            if (
                    (row.getLocalPassportSerial() != null && !row.getLocalPassportSerial().isEmpty()) &&
                            (row.getLocalPassportNumber() != null && !row.getLocalPassportNumber().isEmpty())
            ) {
                LocalPassport localPassport = new LocalPassport();
                localPassport.setSerial(row.getLocalPassportSerial());
                localPassport.setNumber(row.getLocalPassportNumber());
                localPassport.setIssuedAt(row.getLocalPassportIssueDate());
                localPassport.setIssuerName(row.getLocalPassportIssuerName());
                localPassport.generateId();
                localPassportsToSave.add(localPassport);

                LocalPassportLink localPassportLink = new LocalPassportLink();
                localPassportLink.setLocalPassport(localPassport);
                localPassportLink.setPrivatePerson(candidate);
                localPassportLink.setActualDate(LocalDateTime.now());
                localPassportLink.setSource(row.getSource());
                localPassportLink.generateId();
                localPassportLinksToSave.add(localPassportLink);
            }


        }

        for (PrivatePerson privatePerson : personsToSave){
            privatePerson.setPersonNameLinks(new HashSet<>());
            privatePerson.setBirthdayLinks(new HashSet<>());
            privatePerson.setInnLinks(new HashSet<>());
        }

        long endTime = System.currentTimeMillis(); // Get end time
        long duration = endTime - startTime;
        long minutes = duration / 60000;
        long seconds = (duration % 60000) / 1000;
        long milliseconds = duration % 1000;
        System.out.printf("Prepare data: " + "%02d:%02d:%03d%n", minutes, seconds, milliseconds);


        startTime = System.currentTimeMillis();
        CompletableFuture<Void> personsTask = privatePersonService.saveAll(personsToSave);
        personsTask.join();
        endTime = System.currentTimeMillis(); // Get end time
        duration = endTime - startTime;
        minutes = duration / 60000;
        seconds = (duration % 60000) / 1000;
        milliseconds = duration % 1000;
        System.out.printf("Saving PP: " + "%02d:%02d:%03d%n", minutes, seconds, milliseconds);


        startTime = System.currentTimeMillis();
        CompletableFuture<Void> attributeTask = CompletableFuture.allOf(
                innService.saveAll(innsToSave),
                personNameService.saveAll(personNamesToSave),
                birthdayService.saveAll(birthdaysToSave),
                birthplaceService.saveAll(birthplacesToSave),
                addressSimpleService.saveAll(addressSimplesToSave),
                addressService.saveAll(addressesToSave),
                localPassportService.saveAll(localPassportsToSave)
        );
        attributeTask.join();
        personsTask.join();
        endTime = System.currentTimeMillis(); // Get end time
        duration = endTime - startTime;
        minutes = duration / 60000;
        seconds = (duration % 60000) / 1000;
        milliseconds = duration % 1000;
        System.out.printf("Saving Attributes: " + "%02d:%02d:%03d%n", minutes, seconds, milliseconds);

        startTime = System.currentTimeMillis();
        CompletableFuture<Void> attributeLinkTask = CompletableFuture.allOf(
                innLinkService.saveAll(innLinksToSave),
                personNameLinkService.saveAll(personNameLinksToSave),
                birthdayLinkService.saveAll(birthdayLinksToSave),
                birthplaceLinkService.saveAll(birthplacesLinksToSave),
                addressSimplePersonLinkService.saveAll(adressSimplePersonLinksToSave),
                addressPersonLinkService.saveAll(adressPersonLinksToSave),
                localPassportLinkService.saveAll(localPassportLinksToSave)
        );
        attributeLinkTask.join();
        endTime = System.currentTimeMillis(); // Get end time
        duration = endTime - startTime;
        minutes = duration / 60000;
        seconds = (duration % 60000) / 1000;
        milliseconds = duration % 1000;
        System.out.printf("Saving Attributes Links: " + "%02d:%02d:%03d%n", minutes, seconds, milliseconds);

    }

    @Transactional
    public PrivatePerson findCandidatesInPreSave(PrivatePersonStageRow row,
                                                       Set<PersonNameLink> personNameLinks
                                                       ){

        Set<String> noVowelsHashes = new HashSet<>();
        if (row.hasUaName()) {
            PersonName nameUa = new PersonName();
            nameUa.setLastName(row.getLastNameUa());
            nameUa.setFirstName(row.getFirstNameUa());
            nameUa.setPatronymicName(row.getPatronymicNameUa());
            nameUa.setLanguageCode(LanguageCode.UA);
            String noVowelsHashUa = NoVowelsHashUtils.calcNoVowelsHash(nameUa);
            noVowelsHashes.add(noVowelsHashUa);
        }

        if (row.hasRuName()) {
            PersonName nameRu = new PersonName();
            nameRu.setLastName(row.getLastNameRu());
            nameRu.setFirstName(row.getFirstNameRu());
            nameRu.setPatronymicName(row.getPatronymicNameRu());
            nameRu.setLanguageCode(LanguageCode.RU);
            String noVowelsHashRu = NoVowelsHashUtils.calcNoVowelsHash(nameRu);
            noVowelsHashes.add(noVowelsHashRu);
        }

        //TODO search candidate also by EN name

        Set<PrivatePerson> candidates =  personNameLinks.stream().filter(personNameLink -> noVowelsHashes.contains(personNameLink.getPersonName().getNoVowelsHash()))
                .collect(Collectors.toSet()).stream().map(PersonNameLink::getPrivatePerson).collect(Collectors.toSet());

        if (candidates.isEmpty()) {
            return null;
        } else {
            return filterCandidates(candidates, row);
        }
    }

    @Transactional
    public PrivatePerson findCandidate(PrivatePersonStageRow row) {
        Set<String> noVowelsHashes = new HashSet<>();

        PrivatePerson result;

        if (row.hasUaName()) {
            PersonName nameUa = new PersonName();
            nameUa.setLastName(row.getLastNameUa());
            nameUa.setFirstName(row.getFirstNameUa());
            nameUa.setPatronymicName(row.getPatronymicNameUa());
            nameUa.setLanguageCode(LanguageCode.UA);
            String noVowelsHashUa = NoVowelsHashUtils.calcNoVowelsHash(nameUa);
            noVowelsHashes.add(noVowelsHashUa);
        }

        if (row.hasRuName()) {
            PersonName nameRu = new PersonName();
            nameRu.setLastName(row.getLastNameRu());
            nameRu.setFirstName(row.getFirstNameRu());
            nameRu.setPatronymicName(row.getPatronymicNameRu());
            nameRu.setLanguageCode(LanguageCode.RU);
            String noVowelsHashRu = NoVowelsHashUtils.calcNoVowelsHash(nameRu);
            noVowelsHashes.add(noVowelsHashRu);
        }

        //TODO search candidate also by EN name

        CompletableFuture<Set<PrivatePerson>> privatePersonCandidatesFuture = privatePersonService.findCandidatesByNoVowelsHashes(noVowelsHashes);
        Set<PrivatePerson> candidates = privatePersonCandidatesFuture.join();


        // TODO search in presaving results

        if (candidates.isEmpty()) {
            return null;
//            result = new PrivatePerson();
//            result.setId(UUID.randomUUID());
//            return privatePersonService.save(result).join();
        } else {
            return filterCandidates(candidates, row);
        }
    }
    @Transactional
    public PrivatePerson filterCandidates(Set<PrivatePerson> candidates, PrivatePersonStageRow row) {

        List<LocalDate> birthdays = new ArrayList<>();
        Set<PrivatePerson> filteredBirthday = new HashSet<>();
        if (row.getBirthday() != null) {
            birthdays.add(row.getBirthday());
        }

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
            return privatePersonService.save(p).join();
        }
    }
    @Transactional
    public Set<PrivatePerson> filterCandidatesByBirthday(Set<PrivatePerson> candidates, List<LocalDate> birthdays) {
//        candidates.forEach(candidate -> Hibernate.initialize(candidate.getBirthdayLinks()));
        return candidates.stream()
                .filter(privatePerson ->  privatePerson.getBirthdayLinks().stream()
                        .anyMatch(birthdayLink -> birthdays.contains(birthdayLink.getBirthday().getBirthday())))
                .collect(Collectors.toSet());
    }
    @Transactional
    public Set<PrivatePerson> filterCandidatesByInn(Set<PrivatePerson> candidates, String inn) {
        return candidates.stream()
                .filter(privatePerson -> privatePerson.getInnLinks().stream()
                        .anyMatch(innLink -> innLink.getInn().getCode().equals(inn)))
                .collect(Collectors.toSet());
    }


}
