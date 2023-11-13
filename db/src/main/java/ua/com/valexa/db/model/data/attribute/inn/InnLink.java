package ua.com.valexa.db.model.data.attribute.inn;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "inn_link", indexes = {
        @Index(name = "inn_link__private_person_id__index", columnList = "private_person_id"),
        @Index(name = "inn_link__inn_id__index", columnList = "inn_id")
},
        uniqueConstraints = {
                @UniqueConstraint(name = "inn_link__full__uindex", columnNames = {"private_person_id", "inn_id", "source"})
        })
@Data
public class InnLink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "private_person_id", foreignKey = @ForeignKey(name = "inn_link__private_person_fk"))
    private PrivatePerson privatePerson;
    @ManyToOne
    @JoinColumn(name = "inn_id", foreignKey = @ForeignKey(name = "inn_link__inn_fk"))
    private Inn inn;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "source")
    private String source;

    @Override
    public String toString() {
        return privatePerson.getId().toString() + '_' +
               inn.getId().toString() + '_' +
               source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InnLink innLink = (InnLink) o;
        return Objects.equals(privatePerson, innLink.privatePerson) && Objects.equals(inn, innLink.inn) && Objects.equals(source, innLink.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privatePerson, inn, source);
    }
}
