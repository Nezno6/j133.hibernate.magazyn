package pl.sda.j133.hibernate.magazyn;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sda.j133.hibernate.magazyn.model.Kategoria;
import pl.sda.j133.hibernate.magazyn.model.Produkt;

/**
 * @author Paweł Recław, AmeN
 * @project j133-hibernate-magazyn
 * @created 03.12.2022
 */
public class ObslugaDodajProdukt implements ObslugaKomendy {

    @Override
    public String getKomenda() {
        return "dodaj produkt";
    }

    public void obslugaKomendy() {
        System.out.println("Podaj nazwe produktu:");
        String nazwa = Main.scanner.nextLine();

        System.out.println("Podaj kategorię produktu: (ZYWNOSC, NAPOJE, INNE)");
        String kategoriaString = Main.scanner.nextLine();
        Kategoria kategoria = Kategoria.valueOf(kategoriaString.toUpperCase());

        Produkt produkt = Produkt.builder()
                .kategoria(kategoria)
                .nazwa(nazwa)
                .build();

        try (Session session = HibernateUtil.INSTANCE.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.persist(produkt);

            transaction.commit();
        } catch (Exception e) {
            System.err.println("Błąd: " + e);
        }
    }
}
