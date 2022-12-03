package pl.sda.j133.hibernate.magazyn;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.j133.hibernate.magazyn.model.Produkt;
import pl.sda.j133.hibernate.magazyn.model.Sprzedaz;

import java.util.List;

/**
 * @author Paweł Recław, AmeN
 * @project j133-hibernate-magazyn
 * @created 03.12.2022
 */
public class ObslugaListaProduktow implements ObslugaKomendy{

    @Override
    public String getKomenda() {
        return "lista produkt";
    }

    public void obslugaKomendy() {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            TypedQuery<Produkt> zapytanie = session.createQuery("FROM Produkt", Produkt.class);
            List<Produkt> lista = zapytanie.getResultList();

            lista.forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Błąd: " + e);
        }
    }
}
