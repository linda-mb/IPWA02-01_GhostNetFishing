import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Geisternetz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double laengengrad;
	private double breitengrad;
	private String groesse;
	private String status;
	
	@ManyToOne
    private Person person;
	
	public Geisternetz() {};
	
	public Geisternetz(int id, double laengengrad, double breitengrad, String groesse, String status) {
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

	public double getLaengengrad() {
		return laengengrad;
	}

	public void setLaengengrad(double laengengrad) {
		this.laengengrad = laengengrad;
	}

	public double getBreitengrad() {
		return breitengrad;
	}

	public void setBreitengrad(double breitengrad) {
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
