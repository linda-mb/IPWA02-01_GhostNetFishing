import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@SessionScoped
public class GeisternetzIstGeborgenController implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Geisternetz> selectedGeisternetze;

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

    public List<Geisternetz> getGeisternetzeInBergung() {
        return geisternetzVerwaltung.getGeisternetzeByPersonAndStatus(
            loginController.getLoggedInPerson(), Geisternetz.BERGUNG);
    }

    public void alsGeborgenMelden() {
        for (Geisternetz geisternetz : selectedGeisternetze) {
            geisternetz.setStatus(Geisternetz.GEBORGEN);
            geisternetzVerwaltung.updateGeisternetz(geisternetz);
        }
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Geisternetze erfolgreich als geborgen gemeldet!", null));
    }

    public void alsVerschollenMelden() {
        for (Geisternetz geisternetz : selectedGeisternetze) {
            geisternetz.setStatus(Geisternetz.VERSCHOLLEN);
            geisternetzVerwaltung.updateGeisternetz(geisternetz);
        }
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Geisternetze erfolgreich als verschollen gemeldet!", null));
    }
}
