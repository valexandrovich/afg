package ua.com.valexa.db.model.data;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.enums.LanguageCode;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "person_name", uniqueConstraints = {
        @UniqueConstraint(name = "person_name__full__uindex", columnNames = {"last", "first", "patronymic", "language_code"})
})
@Data
public class PersonName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "last")
    private String last = "";
    @Column(name = "first")
    private String first = "";
    @Column(name = "patronymic")
    private String patronymic = "";
    @Enumerated(EnumType.STRING)
    @Column(name = "language_code")
    private LanguageCode languageCode = LanguageCode.UA;

    private String someField;

    @OneToMany(mappedBy = "personName")
    private List<PersonNameLink> personNameLinks = new ArrayList<>();

    @Override
    public String toString() {
        return   (last == null ? "" : last)  + '_' +
                (first == null ? "" : first)  + '_' +
                (patronymic == null ? "" : patronymic)  + '_' +
                languageCode;
    }

//    public void generateId(){
//        this.id = UUID.nameUUIDFromBytes(toString().getBytes());
//    }
}
