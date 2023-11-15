package ua.com.valexa.db.model.data.attribute.birthplace;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "birthplace_link", indexes = {
        @Index(name = "birthplace_link__private_person_id__index", columnList = "private_person_id"),
        @Index(name = "birthplace_link__birthplace_id__index", columnList = "birthplace_id")
},
        uniqueConstraints = {
                @UniqueConstraint(name = "birthplace_link__full__uindex", columnNames = {"private_person_id", "birthplace_id", "source"})
        })
@Data
public class BithplaceLink {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "private_person_id", foreignKey = @ForeignKey(name = "birthplace_link__private_person_fk"))
    private PrivatePerson privatePerson;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "birthplace_id", foreignKey = @ForeignKey(name = "birthplace_link__birthplace_fk"))
    private Birthplace birthplace;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "source")
    private String source;

    @Override
    public String toString() {
        return privatePerson.getId().toString() + '_' +
                birthplace.getId().toString() + '_' +
                source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BithplaceLink that = (BithplaceLink) o;
        return Objects.equals(privatePerson, that.privatePerson) && Objects.equals(birthplace, that.birthplace) && Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privatePerson, birthplace, source);
    }

    public void generateId(){
        setId(UUID.nameUUIDFromBytes(toString().getBytes()));
    }
}
