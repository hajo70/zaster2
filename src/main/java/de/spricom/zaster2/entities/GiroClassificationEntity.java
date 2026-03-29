package de.spricom.zaster2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "giro_classification")
@Getter
@Setter
@ToString
public class GiroClassificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(64)")
    private ClassificationCategory category;

    @OneToMany
    @JoinTable(name = "postbank_giro_classification",
            joinColumns = @JoinColumn(name = "classification_id"),
            inverseJoinColumns = @JoinColumn(name = "postbank_giro_id"))
    private Set<PostbankGiroEntity> postbankBookings = new HashSet<>();

    @OneToMany
    @JoinTable(name = "olb_giro_classification",
            joinColumns = @JoinColumn(name = "classification_id"),
            inverseJoinColumns = @JoinColumn(name = "olb_giro_id"))
    private Set<OlbGiroEntity> olbBookings = new HashSet<>();
}
