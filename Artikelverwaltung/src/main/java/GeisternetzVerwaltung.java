import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class GeisternetzVerwaltung {
	private List<Geisternetz> bestand = new ArrayList<Geisternetz>();

	public GeisternetzVerwaltung() {
		bestand.add(new Geisternetz(1, 1.123456789, 45.123456789, "klein", "geborgen"));
		bestand.add(new Geisternetz(2, 2.987654321, 46.987654321, "mittel", "verloren"));
		bestand.add(new Geisternetz(3, 3.456789123, 47.456789123, "groß", "geborgen"));
		bestand.add(new Geisternetz(4, 4.234567891, 48.234567891, "mittel", "verloren"));
		bestand.add(new Geisternetz(5, 5.345678912, 49.345678912, "klein", "geborgen"));
		bestand.add(new Geisternetz(6, 6.876543219, 50.876543219, "groß", "verloren"));
		bestand.add(new Geisternetz(7, 7.123456781, 51.123456781, "mittel", "geborgen"));
		bestand.add(new Geisternetz(8, 8.234567891, 52.234567891, "klein", "verloren"));
		bestand.add(new Geisternetz(9, 9.345678912, 53.345678912, "groß", "geborgen"));
		bestand.add(new Geisternetz(10, 10.987654321, 54.987654321, "mittel", "verloren"));
	}

	public List<Geisternetz> getBestand() {
		return bestand;
	}
}
