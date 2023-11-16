package ua.com.valexa.db.model.data.attribute.local_passport;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.base_objects.PrivatePerson;
import ua.com.valexa.db.model.stage.PrivatePersonStageRow;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "local_passport_link", indexes = {
        @Index(name = "local_passport_link__private_person_id__index", columnList = "private_person_id"),
        @Index(name = "local_passport_link__local_passport_id__index", columnList = "local_passport_id")
},
        uniqueConstraints = {
                @UniqueConstraint(name = "local_passport_link__full__uindex", columnNames = {"private_person_id", "local_passport_id", "source"})
        })
@Data
public class LocalPassportLink {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "private_person_id", foreignKey = @ForeignKey(name = "local_passport_link__private_person_fk"))
    private PrivatePerson privatePerson;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "local_passport_id", foreignKey = @ForeignKey(name = "local_passport_link__local_passport_fk"))
    private LocalPassport localPassport;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "source")
    private String source;

//    @ManyToOne
//    @JoinColumn(name = "stage_row_id", foreignKey = @ForeignKey(name = "local_passport_link_pp_stage_fk"))
//    private PrivatePersonStageRow stageRow;

    @Override
    public String toString() {
        return privatePerson.getId().toString() + '_' +
                localPassport.getId().toString() + '_' +
                source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocalPassportLink that = (LocalPassportLink) o;
        return Objects.equals(privatePerson, that.privatePerson) && Objects.equals(localPassport, that.localPassport) && Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(privatePerson, localPassport, source);
    }

    public void generateId(){
        setId(UUID.nameUUIDFromBytes(toString().getBytes()));
    }
}
