package ua.com.valexa.db.model.data.attribute.birthplace;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.attribute.birthday.Birthday;
import ua.com.valexa.db.model.data.base_object.PrivatePerson;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "birthplace_link", indexes = {
        @Index(name = "birthplace_link__private_person_id__index", columnList = "private_person_id"),
        @Index(name = "birthplace_link__birthplace_id__index", columnList = "birthplace_id")
},
        uniqueConstraints = {
                @UniqueConstraint(name = "birthplace_link__full__uindex", columnNames = {"private_person_id", "birthplace_id", "source"})
        })
@Data
public class BirthplaceLink {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "private_person_id", foreignKey = @ForeignKey(name = "birthplace_link__private_person__fk"))
    private PrivatePerson privatePerson;
    @ManyToOne
    @JoinColumn(name = "birthplace_id", foreignKey = @ForeignKey(name = "birthplace_link__birthplace__fk"))
    private Birthplace birthplace;
    private LocalDateTime actualDate;
    private Boolean isActive = true;
    private String source;

    @Override
    public String toString() {
        return privatePerson.getId().toString() + '_' +
                birthplace.getId().toString() + '_' +
                source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BirthplaceLink that = (BirthplaceLink) o;
        return Objects.equals(privatePerson, that.privatePerson) && Objects.equals(birthplace, that.birthplace) && Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privatePerson, birthplace, source);
    }

    public void generateId(){
        setId(UUID.nameUUIDFromBytes(toString().getBytes()));
    }
}
