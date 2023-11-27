package ua.com.valexa.db.model.data.attribute.person_name;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.com.valexa.db.model.data.attribute.Attribute;
import ua.com.valexa.db.model.enums.LanguageCode;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "person_name", indexes = {
        @Index(name = "last_name__index", columnList = "last_name"),
        @Index(name = "first_name__index", columnList = "first_name"),
        @Index(name = "patronymic_name__index", columnList = "patronymic_name"),
        @Index(name = "no_vowels_hash__index", columnList = "no_vowels_hash")
},
        uniqueConstraints = @UniqueConstraint(name = "person_name__full__uindex", columnNames = {
                "last_name", "first_name", "patronymic_name", "language_code"
        })
)
@Data
@EqualsAndHashCode(of = {"lastName", "firstName","patronymicName","languageCode"})
public class PersonName extends Attribute {
//    @Id
//    private UUID id;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "patronymic_name")
    private String patronymicName;
    @Column(name = "language_code", nullable = false)
    @Enumerated(EnumType.STRING)
    private LanguageCode languageCode = LanguageCode.UA;
    @Column(name = "no_vowels_hash")
    private String noVowelsHash;

//    @OneToMany(mappedBy = "personName")
//    private Set<PersonNameLink> personNameLinks = new HashSet<>();

    @Override
    public String toString() {
        return (lastName == null ? "" : lastName) + '_' +
                (firstName == null ? "" : firstName) + '_' +
                (patronymicName == null ? "" : patronymicName) + '_' +
                languageCode;
    }

//    @PrePersist
    public void generateId(){
        setId(UUID.nameUUIDFromBytes(toString().getBytes()));
    }
}
