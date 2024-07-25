import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class GeisternetzMeldenController implements Serializable {
	private static final long serialVersionUID = 1L;

	private Geisternetz neuesGeisternetz = new Geisternetz();

	@Inject
	private GeisternetzVerwaltung geisternetzVerwaltung;

	public Geisternetz getNeuesGeisternetz() {
		return neuesGeisternetz;
	}

	public void setNeuesGeisternetz(Geisternetz neuesGeisternetz) {
		this.neuesGeisternetz = neuesGeisternetz;
	}

	public void meldeGeisternetz() {
		neuesGeisternetz.setStatus(Geisternetz.GEMELDET); // Set status to "Gemeldet"
		geisternetzVerwaltung.saveGeisternetz(neuesGeisternetz);
		this.neuesGeisternetz = new Geisternetz(); // Reset form
		FacesContext.getCurrentInstance().addMessage("growl-success", new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Geisternetz erfolgreich gemeldet.", "Das gemeldete Netz ist ab sofort für die Bergung verfügbar."));
	}

}
