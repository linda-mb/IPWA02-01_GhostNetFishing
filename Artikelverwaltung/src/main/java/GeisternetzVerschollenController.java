import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class GeisternetzVerschollenController implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Geisternetz> gefundeneGeisternetze;
    private List<Geisternetz> selectedGeisternetze;

    @Inject
    private GeisternetzVerwaltung geisternetzVerwaltung;

    public List<Geisternetz> getGefundeneGeisternetze() {
        // Refresh the list every time it's accessed to ensure it's up to date
        gefundeneGeisternetze = geisternetzVerwaltung.getGeisternetzeMitStatus();
        return gefundeneGeisternetze;
    }

    public List<Geisternetz> getSelectedGeisternetze() {
        return selectedGeisternetze;
    }

    public void setSelectedGeisternetze(List<Geisternetz> selectedGeisternetze) {
        this.selectedGeisternetze = selectedGeisternetze;
    }

    public void alsVerschollenMelden() {
        for (Geisternetz geisternetz : selectedGeisternetze) {
            geisternetz.setStatus(Geisternetz.VERSCHOLLEN);
            geisternetzVerwaltung.updateGeisternetz(geisternetz);
        }
        gefundeneGeisternetze = geisternetzVerwaltung.getGeisternetzeMitStatus(); // Refresh the list after update
    }
}
