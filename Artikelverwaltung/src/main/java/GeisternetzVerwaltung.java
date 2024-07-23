import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

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
	
	 public List<Geisternetz> getGemeldeteGeisternetze() {
		 EntityManager em = emf.createEntityManager();
	        TypedQuery<Geisternetz> query = em.createQuery(
	            "SELECT g FROM Geisternetz g WHERE g.status = :status", Geisternetz.class);
	        query.setParameter("status", Geisternetz.GEMELDET);
	        return query.getResultList();
	    }
	 
	 public List<Geisternetz> getGeisternetzeByNachname(String nachname) {
		 EntityManager em = emf.createEntityManager();
	        TypedQuery<Geisternetz> query = em.createQuery(
	            "SELECT g FROM Geisternetz g WHERE g.person.nachname = :nachname AND g.status = :status", Geisternetz.class);
	        query.setParameter("nachname", nachname);
	        query.setParameter("status", Geisternetz.BERGUNG);
	        return query.getResultList();
	    }
	 
	    public List<Geisternetz> getGeisternetzeMitStatus() {
	    	EntityManager em = emf.createEntityManager();
	        TypedQuery<Geisternetz> query = em.createQuery(
	            "SELECT g FROM Geisternetz g WHERE g.status = :status", Geisternetz.class);
	        query.setParameter("status", Geisternetz.BERGUNG);
	        return query.getResultList();
	    }

	public void saveGeisternetz(Geisternetz neuesGeisternetz) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(neuesGeisternetz);
		t.commit();
		em.close();
	}

	 public boolean savePersonWithGeisternetze(Person person, List<Geisternetz> geisternetze) {
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction t = em.getTransaction();

	        t.begin();
	        em.persist(person);
	        for (Geisternetz geisternetz : geisternetze) {
	            geisternetz.setPerson(person);
	            geisternetz.setStatus(Geisternetz.BERGUNG);
	            em.merge(geisternetz);
	        }
	        t.commit();
	        return true;
	    }
	 
	 public Person findPersonByNachnameAndTelefonnummer(String nachname, String telefonnummer) {
	        EntityManager em = emf.createEntityManager();
	        TypedQuery<Person> query = em.createQuery(
	            "SELECT p FROM Person p WHERE p.nachname = :nachname AND p.telefonnummer LIKE :telefonnummer", Person.class);
	        query.setParameter("nachname", nachname);
	        query.setParameter("telefonnummer", "%" + telefonnummer);
	        List<Person> result = query.getResultList();
	        em.close();
	        return result.isEmpty() ? null : result.get(0);
	    }

	    public List<Geisternetz> getGeisternetzeByPersonAndStatus(Person person, String status) {
	        EntityManager em = emf.createEntityManager();
	        TypedQuery<Geisternetz> query = em.createQuery(
	            "SELECT g FROM Geisternetz g WHERE g.person = :person AND g.status = :status", Geisternetz.class);
	        query.setParameter("person", person);
	        query.setParameter("status", status);
	        List<Geisternetz> result = query.getResultList();
	        em.close();
	        return result;
	    }
	 
	 public void updateGeisternetz(Geisternetz geisternetz) {
	        EntityManager em = emf.createEntityManager();
	        EntityTransaction t = em.getTransaction();

	        t.begin();
	        em.merge(geisternetz);
	        t.commit();
	        em.close();
	    }
	
}
