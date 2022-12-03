package pl.sda.j133.hibernate.magazyn;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.j133.hibernate.magazyn.model.Produkt;
import pl.sda.j133.hibernate.magazyn.model.Sprzedaz;

public class ObslugaUsunProduktZSprzedaza implements ObslugaKomendy{
    @Override
    public String getKomenda() {
            return "usun produkt";
    }

    @Override
    public void obslugaKomendy() {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            System.out.println("Podaj id produktu ktora nalezy usunac (produkt usunie sie ze sprzedaza):");
            String idString = Main.scanner.nextLine();
            Long produktId = Long.parseLong(idString);

            Produkt produkt = session.get(Produkt.class, produktId);
            if (produkt == null) {
                throw new EntityNotFoundException("Nie znaleziono produktu o id: " + produktId);
            } else {
                produkt.getSprzedaze().forEach(session::remove);
                session.remove(produkt);
                System.out.println("Usunieto produkt (wraz z jego sprzedaza) o id: " + produktId);
            }
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Błąd: " + e);
        }
    }
}
