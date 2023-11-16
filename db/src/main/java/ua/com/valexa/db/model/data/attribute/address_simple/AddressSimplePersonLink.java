package ua.com.valexa.db.model.data.attribute.address_simple;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;

import java.time.LocalDateTime;
import java.util.Objects;
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
//        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private UUID id;
        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "private_person_id", foreignKey = @ForeignKey(name = "address_simple_person_link__private_person_fk"))
        private PrivatePerson privatePerson;
        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "address_simple_id", foreignKey = @ForeignKey(name = "address_simple_person_link__address_simple_fk"))
        private AddressSimple addressSimple;
        @Column(name = "created_at")
        private LocalDateTime createdAt;
        @Column(name = "source")
        private String source;

//        @ManyToOne
//        @JoinColumn(name = "stage_row_id", foreignKey = @ForeignKey(name = "address_simple_person_link_pp_stage_fk"))
//        private PrivatePersonStageRow stageRow;

        @Override
        public String toString() {
                return privatePerson.getId().toString() + '_' +
                        addressSimple.getId().toString() + '_' +
                        source;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                AddressSimplePersonLink that = (AddressSimplePersonLink) o;
                return Objects.equals(privatePerson, that.privatePerson) && Objects.equals(addressSimple, that.addressSimple) && Objects.equals(source, that.source);
        }

        @Override
        public int hashCode() {
                return Objects.hash(privatePerson, addressSimple, source);
        }

        public void generateId(){
                setId(UUID.nameUUIDFromBytes(toString().getBytes()));
        }
}
