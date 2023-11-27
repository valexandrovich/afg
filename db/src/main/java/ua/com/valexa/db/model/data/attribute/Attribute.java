package ua.com.valexa.db.model.data.attribute;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class Attribute {
    @Id
    private UUID id;

//    @OneToMany(mappedBy = "attribute", fetch = FetchType.EAGER)
//    private List<TagAttributeLink> tagAttributeLinks = new ArrayList<>();

}
