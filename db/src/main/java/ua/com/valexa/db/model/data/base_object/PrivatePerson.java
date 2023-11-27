package ua.com.valexa.db.model.data.base_object;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ua.com.valexa.db.model.data.attribute.inn.InnLink;
import ua.com.valexa.db.model.data.attribute.person_name.PersonNameLink;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "private_person")
@Data
@EqualsAndHashCode(of = {"id"})

//@NamedEntityGraph(
//        name = "private-person-names",
//        attributeNodes = {
//                @NamedAttributeNode("personNameLinks"),
//        }
//)

public class PrivatePerson {

    @Id
    private UUID id;

    @OneToMany(mappedBy = "privatePerson")
    private Set<PersonNameLink> personNameLinks = new HashSet<>();


//    @OneToMany(mappedBy = "privatePerson")
//    private Set<InnLink> innLinks = new HashSet<>();

    @Override
    public String toString() {
        return id.toString();
    }

//    public PrivatePerson(){
//        this.id = UUID.randomUUID();
//    }

//    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
