package ua.com.valexa.db.model.data.attribute.address;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;

import java.time.LocalDateTime;
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
public class AddressPersonLink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "private_person_id", foreignKey = @ForeignKey(name = "address_person_link__private_person_fk"))
    private PrivatePerson privatePerson;
    @ManyToOne
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "address_person_link__address_fk"))
    private Address address;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "source")
    private String source;
}
