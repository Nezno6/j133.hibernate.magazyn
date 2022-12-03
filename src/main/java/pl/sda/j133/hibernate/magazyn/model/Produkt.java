package pl.sda.j133.hibernate.magazyn.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.util.Set;

/**
 * @author Paweł Recław, AmeN
 * @project j133-hibernate-magazyn
 * @created 03.12.2022
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;

    @Enumerated(EnumType.STRING)
    private Kategoria kategoria;

    @Formula(value = "(select sum(s.cena * s.ilosc)/sum(s.ilosc) FROM Sprzedaz s)")
    private Double sredniaCenaSprzedazy;

    @OneToMany(mappedBy = "produkt")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Sprzedaz> sprzedaze;
}
