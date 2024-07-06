import java.io.Serializable;
import java.util.logging.Logger; // Ensure this import is present

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class GeisternetzController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int index = 0;
	private Geisternetz neuesGeisternetz = new Geisternetz();

	@Inject
	private GeisternetzVerwaltung geisternetzVerwaltung;

	public Geisternetz getGeisternetz() {
		return geisternetzVerwaltung.getBestand().get(index);
	}

	public void vor() {
		if (index < getMaxIndex()) {
			index++;
		}
	}

	public void zurueck() {
		if (index > 0) {
			index--;
		}
	}

	public int getIndex() {
		return index;
	}

	public int getMaxIndex() {
		return geisternetzVerwaltung.getBestand().size() - 1;
	}

	public Geisternetz getNeuesGeisternetz() {
		return neuesGeisternetz;
	}

	public void setNeuesGeisternetz(Geisternetz neuesGeisternetz) {
		this.neuesGeisternetz = neuesGeisternetz;
	}

	 public void addGeisternetz() {
	        // Console output for debugging
	        System.out.println("Received new Geisternetz with the following details:");
	        System.out.println("Längengrad: " + neuesGeisternetz.getLaengengrad());
	        System.out.println("Breitengrad: " + neuesGeisternetz.getBreitengrad());
	        System.out.println("Größe: " + neuesGeisternetz.getGroesse());

	        neuesGeisternetz.setStatus("gemeldet"); // Set status to "gemeldet"
	        geisternetzVerwaltung.getBestand().add(neuesGeisternetz);
	        this.neuesGeisternetz = new Geisternetz(); // Reset form

	    }
}
