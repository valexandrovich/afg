package ua.com.valexa.db.model.data.attribute.birthday;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.base_object.PrivatePerson;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "birthday_person_link", indexes = {
        @Index(name = "birthday_person_link__private_person_id__index", columnList = "private_person_id"),
        @Index(name = "birthday_person_link__birthday_id__index", columnList = "birthday_id")
},
        uniqueConstraints = {
                @UniqueConstraint(name = "birthday_person_link__full__uindex", columnNames = {"private_person_id", "birthday_id", "source"})
        })
@Data
public class BirthdayLink {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "private_person_id", foreignKey = @ForeignKey(name = "birthday_link__private_person__fk"))
    private PrivatePerson privatePerson;
    @ManyToOne
    @JoinColumn(name = "birthday_id", foreignKey = @ForeignKey(name = "birthday_link__birthday__fk"))
    private Birthday birthday;
    private LocalDateTime actualDate;
    private Boolean isActive = true;
    private String source;

    @Override
    public String toString() {
        return privatePerson.getId().toString() + '_' +
                birthday.getId().toString() + '_' +
                source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BirthdayLink that = (BirthdayLink) o;
        return Objects.equals(privatePerson, that.privatePerson) && Objects.equals(birthday, that.birthday) && Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privatePerson, birthday, source);
    }

    public void generateId(){
        setId(UUID.nameUUIDFromBytes(toString().getBytes()));
    }
}
