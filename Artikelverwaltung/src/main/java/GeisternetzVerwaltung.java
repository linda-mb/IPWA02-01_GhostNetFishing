import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class GeisternetzVerwaltung {

	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostNetFishing");

	private List<Geisternetz> bestand = new ArrayList<>();
	private List<PersonGeisternetz> personGeisternetzBestand = new ArrayList<>();

	public GeisternetzVerwaltung() {

	}

	public List<Geisternetz> getBestand() {
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select a from Geisternetz a");
		List<Geisternetz> bestand = q.getResultList();
		return bestand;
	}

	public void saveGeisternetz(Geisternetz neuesGeisternetz) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(neuesGeisternetz);
		t.commit();
		em.close();
	}

	public List<PersonGeisternetz> findByBookingNumber(String bookingNumber) {
		return personGeisternetzBestand.stream().filter(pg -> bookingNumber.equals(pg.getBuchungsnummer()))
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
