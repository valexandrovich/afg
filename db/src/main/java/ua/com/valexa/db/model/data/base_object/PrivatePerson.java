package ua.com.valexa.db.model.data.base_object;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.attribute.birthday.BirthdayLink;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "private_person")
@Data
public class PrivatePerson {
    @Id
    private UUID id;

    @OneToMany(mappedBy = "privatePerson", fetch = FetchType.EAGER)
    private Set<PersonNameLink> personNameLinks = new HashSet<>();

    @OneToMany(mappedBy = "privatePerson", fetch = FetchType.EAGER)
    private Set<BirthdayLink> birthdayLinks = new HashSet<>();

    @OneToMany(mappedBy = "privatePerson", fetch = FetchType.EAGER)
    private Set<InnLink> innLinks = new HashSet<>();

    @Override
    public String toString() {
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivatePerson that = (PrivatePerson) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
