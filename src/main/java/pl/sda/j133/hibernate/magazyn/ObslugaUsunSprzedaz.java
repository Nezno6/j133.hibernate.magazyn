package pl.sda.j133.hibernate.magazyn;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.j133.hibernate.magazyn.model.Produkt;
import pl.sda.j133.hibernate.magazyn.model.Sprzedaz;

public class ObslugaUsunSprzedaz implements ObslugaKomendy{
    @Override
    public String getKomenda() {
        return "usun sprzedaz";
    }

    @Override
    public void obslugaKomendy() {
        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            System.out.println("Podaj id sprzedazy ktora nalezy usunac:");
            String idString = Main.scanner.nextLine();
            Long sprzedazId = Long.parseLong(idString);

            Sprzedaz sprzedaz = session.get(Sprzedaz.class, sprzedazId);
            if (sprzedaz == null) {
                throw new EntityNotFoundException("Nie znaleziono sprzedazy o id: " + sprzedazId);
            } else {
                session.remove(sprzedaz);
                System.out.println("Usunieto sprzedaz o id: " + sprzedazId);
            }
            transaction.commit();
        } catch (Exception e) {
            System.err.println("Błąd: " + e);
        }
    }
}
