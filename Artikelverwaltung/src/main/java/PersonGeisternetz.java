import java.util.UUID;

public class PersonGeisternetz {
	private int id;
	private Person person;
	private Geisternetz geisternetz;
	private String rolle; //Meldender, Bergender, Beobachter
	private String buchungsnummer;
	
	public PersonGeisternetz() {};

	public PersonGeisternetz(int id, Person person, Geisternetz geisternetz, String rolle, String buchungsnummer) {
		this.id = id;
		this.person = person;
		this.geisternetz = geisternetz;
		this.rolle = rolle;
		this.setBuchungsnummer(buchungsnummer);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Geisternetz getGeisternetz() {
		return geisternetz;
	}

	public void setGeisternetz(Geisternetz geisternetz) {
		this.geisternetz = geisternetz;
	}

	public String getRolle() {
		return rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}

	public String getBuchungsnummer() {
		return buchungsnummer;
	}

	public void setBuchungsnummer(String buchungsnummer) {
		this.buchungsnummer = buchungsnummer;
	}
	
	public void generiereBuchungsnummer () {
		this.buchungsnummer = UUID.randomUUID().toString();
	}
	
	
}