package ua.com.valexa.db.model.data.attribute.address;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.base_object.PrivatePerson;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "address_person_link", indexes = {
        @Index(name = "address_person_link__private_person_id__index", columnList = "private_person_id"),
        @Index(name = "address_person_link__address_id__index", columnList = "address_id")
},
        uniqueConstraints = {
                @UniqueConstraint(name = "address_person_link__full__uindex", columnNames = {"private_person_id", "address_id", "source"})
        })
@Data
public class AdressPersonLink {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "private_person_id", foreignKey = @ForeignKey(name = "address_person_link__private_person__fk"))
    private PrivatePerson privatePerson;
    @ManyToOne
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "address_person_link__address__fk"))
    private Address address;
    private LocalDateTime actualDate;
    private Boolean isActive = true;
    private String source;

    @Override
    public String toString() {
        return privatePerson.getId().toString() + '_' +
                address.getId().toString() + '_' +
                source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdressPersonLink that = (AdressPersonLink) o;
        return Objects.equals(privatePerson, that.privatePerson) && Objects.equals(address, that.address) && Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privatePerson, address, source);
    }

    public void generateId(){
        setId(UUID.nameUUIDFromBytes(toString().getBytes()));
    }
}

