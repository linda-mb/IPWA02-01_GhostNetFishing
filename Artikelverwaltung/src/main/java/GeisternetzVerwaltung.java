import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class GeisternetzVerwaltung {
    private List<Geisternetz> bestand = new ArrayList<>();
    private List<PersonGeisternetz> personGeisternetzBestand = new ArrayList<>();

    public GeisternetzVerwaltung() {
        // Add initial Geisternetz data
        bestand.add(new Geisternetz(1, 1.123456789, 45.123456789, "klein", "verloren"));
        bestand.add(new Geisternetz(2, 2.987654321, 46.987654321, "mittel", "verloren"));
        bestand.add(new Geisternetz(3, 3.456789123, 47.456789123, "groß", "verloren"));
        bestand.add(new Geisternetz(4, 4.234567891, 48.234567891, "mittel", "verloren"));
        bestand.add(new Geisternetz(5, 5.345678912, 49.345678912, "klein", "verloren"));
        bestand.add(new Geisternetz(6, 6.876543219, 50.876543219, "groß", "verloren"));
        bestand.add(new Geisternetz(7, 7.123456781, 51.123456781, "mittel", "verloren"));
        bestand.add(new Geisternetz(8, 8.234567891, 52.234567891, "klein", "verloren"));
        bestand.add(new Geisternetz(9, 9.345678912, 53.345678912, "groß", "verloren"));
        bestand.add(new Geisternetz(10, 10.987654321, 54.987654321, "mittel", "verloren"));

        // Add initial PersonGeisternetz data with a common booking number
        String commonBookingNumber = "test-booking-1234";
        Person person1 = new Person(1, "Max", "Mustermann", "0123456789");
        Person person2 = new Person(2, "Erika", "Musterfrau", "9876543210");
        
        personGeisternetzBestand.add(new PersonGeisternetz(1, person1, bestand.get(2), "Bergender", commonBookingNumber));
        personGeisternetzBestand.add(new PersonGeisternetz(2, person2, bestand.get(9), "Bergender", commonBookingNumber));
    }

    public List<Geisternetz> getBestand() {
        return bestand;
    }

    public List<PersonGeisternetz> findByBookingNumber(String bookingNumber) {
        return personGeisternetzBestand.stream()
                .filter(pg -> bookingNumber.equals(pg.getBuchungsnummer()))
                .collect(Collectors.toList());
    }

    public void updateGeisternetz(Geisternetz geisternetz) {
        for (Geisternetz netz : bestand) {
            if (netz.getId() == geisternetz.getId()) {
                netz.setStatus(geisternetz.getStatus());
                break;
            }
        }
    }
}
