package ua.com.valexa.db.model.data.attribute.person_name;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.enums.LanguageCode;
import ua.com.valexa.db.model.data.attribute.Attribute;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
@DiscriminatorValue("PERSON_NAME")
public class PersonName extends Attribute {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private UUID id;
    @Column(name = "last_name")
    private String lastName ="";
    @Column(name = "first_name")
    private String firstName ="";
    @Column(name = "patronymic_name")
    private String patronymicName ="";
    @Column(name = "language_code")
    @Enumerated(EnumType.STRING)
    private LanguageCode languageCode=LanguageCode.UA;
    @Column(name = "no_vowels_hash")
    private String noVowelsHash ="";

    @OneToMany(mappedBy = "personName", fetch = FetchType.EAGER)
    private Set<PersonNameLink> nameLinks = new HashSet<>();

    @Override
    public String toString() {
        return lastName + '_' +
               firstName + '_' +
               patronymicName + '_' +
               languageCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonName that = (PersonName) o;
        return Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName) && Objects.equals(patronymicName, that.patronymicName) && languageCode == that.languageCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, patronymicName, languageCode);
    }
}
