package ua.com.valexa.db.model.data.attribute.address_simple;

import jakarta.persistence.*;
import lombok.Data;
import ua.com.valexa.db.model.data.attribute.Attribute;

@Entity
@Table(schema = "data", name = "address_simple",
        uniqueConstraints = @UniqueConstraint(name = "address_simple__full__uindex", columnNames = {
                "address"
        })
)
@Data
@DiscriminatorValue("ADDRESS_SIMPLE")
public class AddressSimple extends Attribute {
    @Column(name = "address")
    private String address;
}
