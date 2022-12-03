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
public class ObslugaListaSprzedazy implements ObslugaKomendy {

    @Override
    public String getKomenda() {
        return "lista sprzedaz";
    }

    public void obslugaKomendy() {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            System.out.println("Podaj id produktu ktorego chcemy wyswietlic sprzedaze:");
            String idString = Main.scanner.nextLine();
            Long produktId = Long.parseLong(idString);

            Produkt produkt = session.get(Produkt.class, produktId);
            if (produkt == null) {
                throw new EntityNotFoundException("Nie znaleziono produktu o id: " + produktId);
            }

            TypedQuery<Sprzedaz> zapytanie = session.createQuery("FROM Sprzedaz", Sprzedaz.class);
            List<Sprzedaz> lista = zapytanie.getResultList();

            for (Sprzedaz sprzedaz : lista) {
                if (sprzedaz.getProdukt().getId() == produktId) {
                    System.out.println(sprzedaz);
                }
            }


        } catch (Exception e) {
            System.err.println("Błąd: " + e);
        }
    }
}
