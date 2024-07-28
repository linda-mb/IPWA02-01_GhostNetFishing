import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class GeisternetzDAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostNetFishing");

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public List<Geisternetz> findAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Geisternetz> abfrage = em.createQuery("SELECT a FROM Geisternetz a", Geisternetz.class);
		List<Geisternetz> alleGeisternetze = abfrage.getResultList();
		em.close();
		return alleGeisternetze;
	}

	public List<Geisternetz> findGemeldet() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Geisternetz> abfrage = em.createQuery("SELECT g FROM Geisternetz g WHERE g.status = :status",
				Geisternetz.class);
		abfrage.setParameter("status", Geisternetz.GEMELDET);
		List<Geisternetz> gemeldeteGeisternetze = abfrage.getResultList();
		em.close();
		return gemeldeteGeisternetze;
	}

	public List<Geisternetz> findBergungBevorstehend() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Geisternetz> abfrage = em.createQuery("SELECT g FROM Geisternetz g WHERE g.status = :status",
				Geisternetz.class);
		abfrage.setParameter("status", Geisternetz.BERGUNG);
		List<Geisternetz> geisternetzeInBergung = abfrage.getResultList();
		em.close();
		return geisternetzeInBergung;
	}

	public List<Geisternetz> findByPersonAndStatus(Person person, String status) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Geisternetz> abfrage = em.createQuery(
				"SELECT g FROM Geisternetz g WHERE g.person = :person AND g.status = :status", Geisternetz.class);
		abfrage.setParameter("person", person);
		abfrage.setParameter("status", status);
		List<Geisternetz> gebuchteGeisternetze = abfrage.getResultList();
		em.close();
		return gebuchteGeisternetze;
	}

	public void save(Geisternetz neuesGeisternetz) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(neuesGeisternetz);
		t.commit();
		em.close();
	}

	public void update(Geisternetz geisternetz) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.merge(geisternetz);
		t.commit();
		em.close();
	}

}
