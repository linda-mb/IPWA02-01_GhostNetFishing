import java.io.Serializable;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class GeisternetzMeldenController implements Serializable {
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

	public int getIndex() {
		return index;
	}

	public Geisternetz getNeuesGeisternetz() {
		return neuesGeisternetz;
	}

	public void setNeuesGeisternetz(Geisternetz neuesGeisternetz) {
		this.neuesGeisternetz = neuesGeisternetz;
	}

	 public void meldeGeisternetz() {
	        // Console output for debugging
	        System.out.println("Received new Geisternetz with the following details:");
	        System.out.println("Längengrad: " + neuesGeisternetz.getLaengengrad());
	        System.out.println("Breitengrad: " + neuesGeisternetz.getBreitengrad());
	        System.out.println("Größe: " + neuesGeisternetz.getGroesse());

	        neuesGeisternetz.setStatus("gemeldet"); // Set status to "gemeldet"
	        geisternetzVerwaltung.saveGeisternetz(neuesGeisternetz);
	        this.neuesGeisternetz = new Geisternetz(); // Reset form

	    }
}
