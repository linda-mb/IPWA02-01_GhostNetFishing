import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class PersonDAO {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ghostNetFishing");
	
	public List<Person> findAll() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<Person> abfrage = em.createQuery("SELECT p FROM Person p", Person.class);
		List<Person> allePersonen = abfrage.getResultList();
		em.close();
		return allePersonen;
	}
	
	public void save(Person neuePerson) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(neuePerson);
		t.commit();
		em.close();
	}
}
