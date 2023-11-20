package ua.com.valexa.db.model.data.tag;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(schema = "data", name = "tag")
@Data
public class Tag {
    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "tag_type_id", referencedColumnName = "id")
    TagType tagType;

    private LocalDate startAt;

}
