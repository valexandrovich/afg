package ua.com.valexa.db.model.data.base_object;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
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
public class PrivatePerson {

    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @Column(updatable = false, nullable = false)
    private UUID id;

    @OneToMany(mappedBy = "privatePerson")
    private Set<PersonNameLink> personNameLinks = new HashSet<>();

//    @OneToMany(mappedBy = "privatePerson")
//    private Set<InnLink> innLinks = new HashSet<>();

    @Override
    public String toString() {
        return id.toString();
    }

//    @PrePersist
    public void generateId(){
        this.id = UUID.randomUUID();
    }
}
