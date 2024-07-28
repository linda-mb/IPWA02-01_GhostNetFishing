import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class DashboardController implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Geisternetz> selectedGeisternetze;
    private List<Geisternetz> geisternetzeInBergung;

    @Inject
    private GeisternetzVerwaltung geisternetzVerwaltung;
    
    @Inject
    private LoginController loginController;

    public List<Geisternetz> getSelectedGeisternetze() {
        return selectedGeisternetze;
    }
    
    public void setSelectedGeisternetze(List<Geisternetz> selectedGeisternetze) {
        this.selectedGeisternetze = selectedGeisternetze;
    }
    
    // Fetch geisternetze in bergung status for the logged-in user
    public List<Geisternetz> getGeisternetzeInBergungByBooking() {
        Person loggedInPerson = loginController.getLoggedInPerson();
        if (loggedInPerson == null) {
            return null;
        }
        return geisternetzVerwaltung.getGeisternetzeByPersonAndStatus(loggedInPerson, Geisternetz.BERGUNG);
    }
    
    public void alsGeborgenMelden() {
        for (Geisternetz geisternetz : selectedGeisternetze) {
            geisternetz.setStatus(Geisternetz.GEBORGEN);
            geisternetzVerwaltung.updateGeisternetz(geisternetz);
        }
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Geisternetze erfolgreich als geborgen gemeldet!", null));
        this.selectedGeisternetze = null;
    }
    
    public void alsVerschollenMelden() {
        for (Geisternetz geisternetz : selectedGeisternetze) {
            geisternetz.setStatus(Geisternetz.VERSCHOLLEN);
            geisternetzVerwaltung.updateGeisternetz(geisternetz);
        }
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Geisternetze erfolgreich als verschollen gemeldet!", null));
        this.selectedGeisternetze = null;
    }
    
    public List<Geisternetz> getGeisternetzeInBergung() {
        geisternetzeInBergung = geisternetzVerwaltung.getGeisternetzeInBergung();
        return geisternetzeInBergung;
    }
}
