package ua.com.valexa.db.model.stage;


import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.enums.Country;
import ua.com.valexa.db.model.enums.Sex;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(schema="stage", name="pp_stage")
@Data
public class PrivatePersonStageRow {
    @Id
    private UUID id;
    @Column(name = "portion_id")
    private UUID portionId;
    @Column(name = "last_name_ua", length = 64)
    private String lastNameUa;
    @Column(name = "first_name_ua", length = 64)
    private String firstNameUa;
    @Column(name = "patronymic_name_ua", length = 64)
    private String patronymicNameUa;
    @Column(name = "last_name_ru", length = 64)
    private String lastNameRu;
    @Column(name = "first_name_ru", length = 64)
    private String firstNameRu;
    @Column(name = "patronymic_name_ru", length = 64)
    private String patronymicNameRu;
    @Column(name = "last_name_en", length = 64)
    private String lastNameEn;
    @Column(name = "first_name_en", length = 64)
    private String firstNameEn;
    @Column(name = "patronymic_name_en", length = 64)
    private String patronymicNameEn;
    @Column(name = "birthday")
    private LocalDate birthday;
    @Column(name = "inn", length = 15)
    private String inn;
    @Column(name = "local_passport_serial", length = 2)
    private String localPassportSerial;
    @Column(name = "local_passport_number", length = 6)
    private String localPassportNumber;
    @Column(name = "local_passport_issue_date")
    private LocalDate localPassportIssueDate;
    @Column(name = "local_passport_issuer_name", length = 255)
    private String localPassportIssuerName;
    @Column(name = "id_passport_number", length = 14)
    private String idPassportNumber;
    @Column(name = "id_passport_record", length = 9)
    private String idPassportRecord;
    @Column(name = "id_passport_issue_date")
    private LocalDate idPassportIssueDate;
    @Column(name = "id_passport_issuer_code", length = 5)
    private String idPassportIssuerCode;
    @Column(name = "int_passport_serial", length = 2)
    private String intPassportSerial;
    @Column(name = "int_passport_number", length = 6)
    private String intPassportNumber;
    @Column(name = "int_passport_issue_date")
    private LocalDate intPassportIssueDate;
    @Column(name = "int_passport_issuer_code", length = 255)
    private String intPassportIssuer;
    @Enumerated(EnumType.STRING)
    private Country citizenship;
    @Column(name = "birthplace", length = 255)
    private String birthplace;
    @Enumerated(EnumType.STRING)
    @Column(name = "sex", length = 32)
    private Sex sex;
    @Column(name = "phone", length = 13)
    private String phone;
    @Column(name = "email", length = 128)
    private String email;

    @Column(name = "address_simple", length = 255)
    private String addressSimple;
    @Column(name = "address_country", length = 255)
    private String addressCountry;
    @Column(name = "address_region", length = 255)
    private String addressRegion;
    @Column(name = "address_county", length = 255)
    private String addressCounty;
    @Column(name = "address_city_type", length = 255)
    private String addressCityType;
    @Column(name = "address_city", length = 255)
    private String addressCity;
    @Column(name = "address_street_type", length = 255)
    private String addressStreetType;
    @Column(name = "address_street", length = 255)
    private String addressStreet;
    @Column(name = "address_building_number", length = 255)
    private String addressBuildingNumber;
    @Column(name = "address_building_letter", length = 255)
    private String addressBuildingLetter;
    @Column(name = "address_building_part", length = 255)
    private String addressBuildingPart;
    @Column(name = "address_apartment", length = 255)
    private String addressApartment;
    @Column(name = "mk_code", length = 10)
    private String mkCode;
    @Column(name = "mk_event_date")
    private LocalDate mkEventDate;
    @Column(name = "mk_start_date")
    private LocalDate mkStartDate;
    @Column(name = "mk_end_date")
    private LocalDate mkEndDate;
    @Column(name = "mk_text_value", length = 255)
    private String mkTextValue;
    @Column(name = "mk_number_value")
    private Integer mkNumberValue;
    @Column(name = "mk_comment", length = 255)
    private String mkComment;
    @Column(name = "source", length = 255, nullable = false)
    private String source;
    @Column(name = "is_handled", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isHandled;
    @Column(name = "input_date", columnDefinition = "DATE DEFAULT now()")
    private LocalDate inputDate;
//    @Column(name = "no_vowels_hash_ua", length = 255)
//    private String noVowelsHashUa;
//    @Column(name = "no_vowels_hash_ru", length = 255)
//    private String noVowelsHashRu;
//    @Column(name = "is_no_vowels_hash_equal")
//    private Boolean isNoVowelsHashEqual;
//    @Column(name = "calculated_birthday")
//    private LocalDate calculatedBirthday;
//    @Column(name = "birthday_check")
//    private Boolean birthdayCheck;


    public boolean hasUaName() {
        return lastNameUa != null && !lastNameUa.isEmpty() &&
                firstNameUa != null && !firstNameUa.isEmpty();
    }

    public boolean hasRuName() {
        return lastNameRu != null && !lastNameRu.isEmpty() &&
                firstNameRu != null && !firstNameRu.isEmpty();
    }

    public boolean hasEnName() {
        return lastNameEn != null && !lastNameEn.isEmpty() &&
                firstNameEn != null && !firstNameEn.isEmpty();
    }
}
