package ua.com.valexa.db.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(schema = "data", name = "user")
@Data
//@NamedEntityGraph(
//        name = "user-graph",
//        attributeNodes = {
//                @NamedAttributeNode("usersOn"),
//                @NamedAttributeNode("usersAt")
//
//        }
//)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    private Set<UserAt> usersAt = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<UserOn> usersOn = new HashSet<>();


    @OneToMany(mappedBy = "user")
    private Set<UserIn> usersIn = new HashSet<>();

}
