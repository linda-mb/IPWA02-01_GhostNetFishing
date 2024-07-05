import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class GeisternetzVerwaltung {
	private List<Geisternetz> bestand = new ArrayList<Geisternetz>();
	
	public GeisternetzVerwaltung() {
		bestand.add(new Geisternetz(1, 10.435346745, 29.345435345, "klein", "gemeldet"));
		bestand.add(new Geisternetz(2, 3.345345353, 44.345345435, "mittel", "geborgen"));	
	}

	public List<Geisternetz> getBestand() {
		return bestand;
	}
}
