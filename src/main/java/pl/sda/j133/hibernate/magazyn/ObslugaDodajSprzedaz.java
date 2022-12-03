package pl.sda.j133.hibernate.magazyn;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.j133.hibernate.magazyn.model.Kategoria;
import pl.sda.j133.hibernate.magazyn.model.Produkt;
import pl.sda.j133.hibernate.magazyn.model.Sprzedaz;

/**
 * @author Paweł Recław, AmeN
 * @project j133-hibernate-magazyn
 * @created 03.12.2022
 */
public class ObslugaDodajSprzedaz implements ObslugaKomendy{

    @Override
    public String getKomenda() {
        return "dodaj sprzedaz";
    }

    public void obslugaKomendy() {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            System.out.println("Podaj id produktu ktory sprzedano:");
            String idString = Main.scanner.nextLine();
            Long produktId = Long.parseLong(idString);

            Produkt produkt = session.get(Produkt.class, produktId);
            if (produkt == null) {
                throw new EntityNotFoundException("Nie znaleziono produktu o id: " + produktId);
            }

            System.out.println("Podaj cene sprzedazy:");
            String cenaString = Main.scanner.nextLine();
            Double cena = Double.parseDouble(cenaString);

            System.out.println("Podaj ilosc sprzedazy:");
            String iloscString = Main.scanner.nextLine();
            Double ilosc = Double.parseDouble(iloscString);

            Sprzedaz sprzedaz = Sprzedaz.builder()
                    .cena(cena)
                    .ilosc(ilosc)
                    .produkt(produkt)
                    .build();

            session.persist(sprzedaz);

            transaction.commit();
        } catch (Exception e) {
            System.err.println("Błąd: " + e);
        }
    }
}
