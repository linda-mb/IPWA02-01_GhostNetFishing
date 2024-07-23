import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Geisternetz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String laengengrad;
	private String breitengrad;
	private String groesse;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
    private Person person;
	
    // Konstanten für den Status
    public static final String GEMELDET = "Gemeldet";
    public static final String BERGUNG = "Bergung bevorstehend";
    public static final String GEBORGEN = "Geborgen";
    public static final String VERSCHOLLEN = "Verschollen";
    
	
	public Geisternetz() {};
	
	public Geisternetz(int id, String laengengrad, String breitengrad, String groesse, String status) {
		this.id = id;
		this.laengengrad = laengengrad;
		this.breitengrad = breitengrad;
		this.groesse = groesse;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLaengengrad() {
		return laengengrad;
	}

	public void setLaengengrad(String laengengrad) {
		this.laengengrad = laengengrad;
	}

	public String getBreitengrad() {
		return breitengrad;
	}

	public void setBreitengrad(String breitengrad) {
		this.breitengrad = breitengrad;
	}

	public String getGroesse() {
		return groesse;
	}

	public void setGroesse(String groesse) {
		this.groesse = groesse;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
