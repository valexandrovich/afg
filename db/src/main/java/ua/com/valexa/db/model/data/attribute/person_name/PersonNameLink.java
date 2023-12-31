package ua.com.valexa.db.model.data.attribute.person_name;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "person_name_link", indexes = {
        @Index(name = "person_name_link__private_person_id__index", columnList = "private_person_id"),
        @Index(name = "person_name_link__person_name_id__index", columnList = "person_name_id")
},
uniqueConstraints = {
        @UniqueConstraint(name = "person_name_link__full__uindex", columnNames = {"private_person_id", "person_name_id", "source"})
})
@Data
public class PersonNameLink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "private_person_id", foreignKey = @ForeignKey(name = "person_name_link__private_person_fk"))
    private PrivatePerson privatePerson;
    @ManyToOne
    @JoinColumn(name = "person_name_id", foreignKey = @ForeignKey(name = "person_name_link__person_name_fk"))
    private PersonName personName;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "source")
    private String source;

    @Override
    public String toString() {
        return privatePerson.getId().toString() + '_' +
                personName.getId().toString() + '_' +
                createdAt.toString() + '_' +
                source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonNameLink that = (PersonNameLink) o;
        return Objects.equals(privatePerson, that.privatePerson) && Objects.equals(personName, that.personName) && Objects.equals(createdAt, that.createdAt) && Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privatePerson, personName, createdAt, source);
    }
}
