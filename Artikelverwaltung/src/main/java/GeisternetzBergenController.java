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
    
    @Inject
    private BuchungsnummerGenerator buchungsnummerGenerator;

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
    
    public String getButtonLabel() {
        int anzahl = selectedGeisternetze != null ? selectedGeisternetze.size() : 0;
        return anzahl + " ausgewählte Geisternetze bergen";
    }
    
    public void saveBuchung() {
    	neuePerson.setBuchungsnummer(buchungsnummerGenerator.generateBuchungsnummer(10));
        geisternetzVerwaltung.savePersonWithGeisternetze(neuePerson, selectedGeisternetze);
        int anzahl = selectedGeisternetze.size();
        String buchungsnummer = neuePerson.getBuchungsnummer();
        FacesContext.getCurrentInstance().addMessage("growl-success", new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Erfolgreich für Bergung angemeldet.", "Anzahl der gebuchten Netze: "+anzahl+" Buchungsnummer: "+ buchungsnummer));
        PrimeFaces.current().ajax().update("bergenForm:growl-success");
        PrimeFaces.current().ajax().update("bergenForm:registrierteNetzeTabelle");
        PrimeFaces.current().ajax().update("bergenForm:bergenButton");
        PrimeFaces.current().executeScript("PF('dlg-kontaktdaten').hide();");
        this.neuePerson = null;
        this.selectedGeisternetze = null;
    }

}