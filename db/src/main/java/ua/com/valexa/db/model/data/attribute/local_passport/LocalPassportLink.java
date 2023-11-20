package ua.com.valexa.db.model.data.attribute.local_passport;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.attribute.inn.Inn;
import ua.com.valexa.db.model.data.base_object.PrivatePerson;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "local_passport_link", indexes = {
        @Index(name = "local_passport_link__private_person_id__index", columnList = "private_person_id"),
        @Index(name = "local_passport_link__local_passport_id__index", columnList = "local_passport_id")
},
        uniqueConstraints = {
                @UniqueConstraint(name = "local_passport_link__full__uindex", columnNames = {"private_person_id", "local_passport_id", "source"})
        })
@Data
public class LocalPassportLink {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "private_person_id", foreignKey = @ForeignKey(name = "local_passport_link__private_person__fk"))
    private PrivatePerson privatePerson;
    @ManyToOne
    @JoinColumn(name = "local_passport_id", foreignKey = @ForeignKey(name = "local_passport_link__local_passport__fk"))
    private LocalPassport localPassport;
    @Column(name = "actual_date")
    private LocalDateTime actualDate;
    @Column(name = "is_active")
    private Boolean isActive = true;
    @Column(name = "source")
    private String source;

    @Override
    public String toString() {
        return privatePerson.getId().toString() + '_' +
                localPassport.getId().toString() + '_' +
                source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalPassportLink that = (LocalPassportLink) o;
        return Objects.equals(privatePerson, that.privatePerson) && Objects.equals(localPassport, that.localPassport) && Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privatePerson, localPassport, source);
    }

    public void generateId() {
        this.id = UUID.nameUUIDFromBytes(toString().getBytes());
    }
}
