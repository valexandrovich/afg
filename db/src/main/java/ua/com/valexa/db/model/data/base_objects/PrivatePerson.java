package ua.com.valexa.db.model.data.base_objects;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.attribute.address_simple.AddressSimplePersonLink;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.model.data.attribute.birthday.BirthdayPersonLink;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;

import java.util.*;

@Entity
@Table(schema = "data", name = "private_person")
@Data
@DiscriminatorValue("PRIVATE_PERSON")
public class PrivatePerson extends BaseObject {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private UUID id;



    @OneToMany(mappedBy = "privatePerson", fetch = FetchType.EAGER)
    private Set<PersonNameLink> nameLinks = new HashSet<>();

    @OneToMany(mappedBy = "privatePerson", fetch = FetchType.EAGER)
    private Set<BirthdayPersonLink> birthdayLinks = new HashSet<>();

    @OneToMany(mappedBy = "privatePerson", fetch = FetchType.EAGER)
    private Set<InnLink> innLinks = new HashSet<>();

    @OneToMany(mappedBy = "privatePerson", fetch = FetchType.EAGER)
    private Set<BirthdayPersonLink> birthplaceLinks = new HashSet<>();

    @OneToMany(mappedBy = "privatePerson", fetch = FetchType.EAGER)
    private Set<AddressSimplePersonLink> addressSimpleLinks = new HashSet<>();

    @Override
    public String toString() {
        return "PrivatePerson{" + '\n' +
                "nameLinks=" + nameLinks + '\n' +
                "birthdayLinks=" + birthdayLinks + '\n' +
                "innLinks=" + innLinks + '\n' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivatePerson that = (PrivatePerson) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
