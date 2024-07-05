import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Geisternetz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double laengengrad =10.1;
	private double breitengrad=13.2;
	private String groesse = "klein";
	private String status;

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
}