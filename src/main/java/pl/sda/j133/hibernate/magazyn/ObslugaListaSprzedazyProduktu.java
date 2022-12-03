package pl.sda.j133.hibernate.magazyn;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import pl.sda.j133.hibernate.magazyn.model.Sprzedaz;

import java.util.List;

/**
 * @author Paweł Recław, AmeN
 * @project j133-hibernate-magazyn
 * @created 03.12.2022
 */
public class ObslugaListaSprzedazyProduktu implements ObslugaKomendy{

    @Override
    public String getKomenda() {
        return "lista sprzedazy produktu";
    }

    public void obslugaKomendy() {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            TypedQuery<Sprzedaz> zapytanie = session.createQuery("FROM Sprzedaz", Sprzedaz.class);
            List<Sprzedaz> lista = zapytanie.getResultList();

            lista.forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Błąd: " + e);
        }
    }
}
