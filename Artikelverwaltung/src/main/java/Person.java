import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String vorname;
	private String nachname;
	private String telefonnummer;

	@Column(name = "buchungsnummer", nullable = false, unique = true)
	private String buchungsnummer;

	@OneToMany(mappedBy = "person")
	List<Geisternetz> zugeordneteGeisternetze;

	public Person() {
	}

	public Person(int id, String vorname, String nachname, String telefonnummer, String buchungsnummer) {
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.telefonnummer = telefonnummer;
		this.buchungsnummer = buchungsnummer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getTelefonnummer() {
		return telefonnummer;
	}

	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}

	public String getBuchungsnummer() {
		return buchungsnummer;
	}

	public void setBuchungsnummer(String buchungsnummer) {
		this.buchungsnummer = buchungsnummer;
	}

	public List<Geisternetz> getZugeordneteGeisternetze() {
		return zugeordneteGeisternetze;
	}

	public void setZugeordneteGeisternetze(List<Geisternetz> zugeordneteGeisternetze) {
		this.zugeordneteGeisternetze = zugeordneteGeisternetze;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person p = (Person) obj;
			if (p.getNachname().equals(this.nachname) && p.getBuchungsnummer().equals(this.buchungsnummer))
				return true;
		}
		return false;
	}
}