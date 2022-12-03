package pl.sda.j133.hibernate.magazyn.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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
public class Sprzedaz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double cena;
    private double ilosc;

    @CreationTimestamp
    private LocalDateTime dataCzasSprzedazy;

    @ManyToOne()
    @EqualsAndHashCode.Exclude
    private Produkt produkt;
}
