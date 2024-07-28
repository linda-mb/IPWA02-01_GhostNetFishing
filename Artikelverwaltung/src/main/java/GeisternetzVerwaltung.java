import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

@Named
@ApplicationScoped
public class GeisternetzVerwaltung {

	private GeisternetzDAO geisternetzDao;
	private PersonDAO personDao;

	public GeisternetzVerwaltung() {
		geisternetzDao = new GeisternetzDAO();
		personDao = new PersonDAO();
	}

	public List<Geisternetz> getBestand() {
		return geisternetzDao.findAll();
	}

	public List<Geisternetz> getGemeldeteGeisternetze() {
		return geisternetzDao.findGemeldet();
	}

	public List<Geisternetz> getGeisternetzeInBergung() {
		return geisternetzDao.findBergungBevorstehend();
	}

	public List<Geisternetz> getGeisternetzeByPersonAndStatus(Person person, String status) {
		return geisternetzDao.findByPersonAndStatus(person, status);
	}

	public void saveGeisternetz(Geisternetz neuesGeisternetz) {
		geisternetzDao.save(neuesGeisternetz);
	}

	public void updateGeisternetz(Geisternetz geisternetz) {
		geisternetzDao.update(geisternetz);
	}

	public boolean savePersonWithGeisternetze(Person neuePerson, List<Geisternetz> geisternetze) {
		EntityManager em = geisternetzDao.getEntityManager();
		EntityTransaction t = em.getTransaction();

		t.begin();
		personDao.save(neuePerson);
		for (Geisternetz geisternetz : geisternetze) {
			geisternetz.setPerson(neuePerson);
			geisternetz.setStatus(Geisternetz.BERGUNG);
			geisternetzDao.update(geisternetz);
		}
		t.commit();
		return true;
	}

	public List<Person> getPersonen() {
		return personDao.findAll();
	}
	
}

