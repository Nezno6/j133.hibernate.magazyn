package pl.sda.j133.hibernate.magazyn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Paweł Recław, AmeN
 * @project j133-hibernate-magazyn
 * @created 03.12.2022
 */
public class Main {
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<ObslugaKomendy> dostepneKomendy = new ArrayList<>(Arrays.asList(
                new ObslugaDodajProdukt(),
                new ObslugaDodajSprzedaz(),
                new ObslugaListaProduktow(),
                new ObslugaListaSprzedazy()
        ));
        System.out.println("Podaj komendę: (dodaj produkt, dodaj sprzedaz)");
        String komenda = scanner.nextLine();

//        if (komenda.equalsIgnoreCase("dodaj produkt")) {
//            obslugaKomendy = new ObslugaDodajProdukt();
//        } else if (komenda.equalsIgnoreCase("dodaj sprzedaz")) {
//            obslugaKomendy = new ObslugaDodajSprzedaz();
//        } else if (komenda.equalsIgnoreCase("lista produkt")) {
//            obslugaKomendy = new ObslugaListaProduktow();
//        }

        for (ObslugaKomendy obsluga : dostepneKomendy) {
            if (obsluga.getKomenda().equalsIgnoreCase(komenda)){
                obsluga.obslugaKomendy();
            }
        }

    }
}
