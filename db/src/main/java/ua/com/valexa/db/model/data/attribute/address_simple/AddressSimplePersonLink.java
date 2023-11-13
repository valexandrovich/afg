package ua.com.valexa.db.model.data.attribute.address_simple;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "address_simple_person_link", indexes = {
        @Index(name = "address_simple_person_link__private_person_id__index", columnList = "private_person_id"),
        @Index(name = "address_simple_person_link__address_simple_id__index", columnList = "address_simple_id")
},
        uniqueConstraints = {
                @UniqueConstraint(name = "address_simple_person_link__full__uindex", columnNames = {"private_person_id", "address_simple_id", "source"})
        })
@Data
public class AddressSimplePersonLink {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private UUID id;
        @ManyToOne
        @JoinColumn(name = "private_person_id", foreignKey = @ForeignKey(name = "address_simple_person_link__private_person_fk"))
        private PrivatePerson privatePerson;
        @ManyToOne
        @JoinColumn(name = "address_simple_id", foreignKey = @ForeignKey(name = "address_simple_person_link__address_simple_fk"))
        private AddressSimple addressSimple;
        @Column(name = "created_at")
        private LocalDateTime createdAt;
        @Column(name = "source")
        private String source;
}
