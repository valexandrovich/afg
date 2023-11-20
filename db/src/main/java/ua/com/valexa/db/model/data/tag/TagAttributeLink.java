package ua.com.valexa.db.model.data.tag;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.attribute.Attribute;

import java.util.UUID;

@Entity
@Table(schema = "data", name = "tag_attribue_link")
@Data
public class TagAttributeLink {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;


    @ManyToOne
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

}
