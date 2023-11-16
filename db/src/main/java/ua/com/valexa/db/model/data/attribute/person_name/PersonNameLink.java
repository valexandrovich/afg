package ua.com.valexa.db.model.data.attribute.person_name;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "person_name_link", indexes = {
        @Index(name = "person_name_link__private_person_id__index", columnList = "private_person_id"),
        @Index(name = "person_name_link__person_name_id__index", columnList = "person_name_id")
},
        uniqueConstraints = {
                @UniqueConstraint(name = "person_name_link__full__uindex", columnNames = {"private_person_id", "person_name_id", "source"})
        })
@Data
public class PersonNameLink {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "private_person_id", foreignKey = @ForeignKey(name = "person_name_link__private_person_fk"))
    private PrivatePerson privatePerson;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "person_name_id", foreignKey = @ForeignKey(name = "person_name_link__person_name_fk"))
    private PersonName personName;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "source", nullable = false)
    private String source;
//    @ManyToOne
//    @JoinColumn(name = "stage_row_id", foreignKey = @ForeignKey(name = "person_name_link_pp_stage_fk"))
//    private PrivatePersonStageRow stageRow;

    @Override
    public String toString() {
        return privatePerson.getId().toString() + '_' +
                personName.getId().toString() + '_' +
                source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonNameLink that = (PersonNameLink) o;
        return Objects.equals(privatePerson, that.privatePerson) && Objects.equals(personName, that.personName) && Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privatePerson, personName, source);
    }

    public void generateId() {
        setId(UUID.nameUUIDFromBytes(toString().getBytes(StandardCharsets.UTF_8)));
    }
}
