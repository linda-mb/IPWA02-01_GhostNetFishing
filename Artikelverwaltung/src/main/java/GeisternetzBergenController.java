import java.io.Serializable;
import java.util.List;

import org.primefaces.PrimeFaces;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@SessionScoped
public class GeisternetzBergenController implements Serializable {
    private static final long serialVersionUID = 1L;
    private Person neuePerson = null;
    private List<Geisternetz> selectedGeisternetze;

    @Inject
    private GeisternetzVerwaltung geisternetzVerwaltung;

    public Person getNeuePerson() {
        if (null == this.neuePerson) {
            this.neuePerson = new Person();
        }
        return this.neuePerson;
    }

    public List<Geisternetz> getSelectedGeisternetze() {
        return selectedGeisternetze;
    }

    public void setSelectedGeisternetze(List<Geisternetz> selectedGeisternetze) {
        this.selectedGeisternetze = selectedGeisternetze;
    }

    public String personSpeichern() {
        if (geisternetzVerwaltung.savePersonWithGeisternetze(neuePerson, selectedGeisternetze));
       {
        	PrimeFaces.current().executeScript("PF('dlg-bergung').hide()");
        }
        int anzahl = selectedGeisternetze.size();
        FacesContext.getCurrentInstance().addMessage(null, 
            new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Erfolgreich für Bergung angemeldet. Anzahl der gebuchten Netze: " + anzahl, ""));
        this.neuePerson = null;
        return null;
    }
    

}
