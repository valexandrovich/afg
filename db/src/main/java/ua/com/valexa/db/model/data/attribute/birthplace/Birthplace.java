package ua.com.valexa.db.model.data.attribute.birthplace;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.attribute.Attribute;

@Entity
@Table(schema = "data", name = "birthplace",
        uniqueConstraints = @UniqueConstraint(name = "birthplace__full__uindex", columnNames = {
                "birthplace"
        })
)
@DiscriminatorValue("BIRTHPLACE")
@Data
public class Birthplace extends Attribute {
    @Column(name = "birthplace", length = 255)
    private String birthplace;
}
