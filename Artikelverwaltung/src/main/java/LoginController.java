import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIInput;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ComponentSystemEvent;
import jakarta.faces.validator.ValidatorException;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class LoginController implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nachname;
	private String buchungsnummer;
	private Person loggedInPerson;

	@Inject
	private GeisternetzVerwaltung geisternetzVerwaltung;

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getBuchungsnummer() {
		return buchungsnummer;
	}

	public void setBuchungsnummer(String buchungsnummer) {
		this.buchungsnummer = buchungsnummer;
	}

    public Person getLoggedInPerson() {
        return loggedInPerson;
    }

	public String login() {
		List<Person> personenListe = geisternetzVerwaltung.getPersonen();
		for (Person p : personenListe) {
			if (p.equals(new Person(0, null, this.nachname, null, this.buchungsnummer))) {
				loggedInPerson = p;
				return "dashboardBergendePerson.xhtml?faces-redirect=true";
			}
		}
		return "login.xhtml";
	}

	public void validateLogin(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		List<Person> personenListe = geisternetzVerwaltung.getPersonen();
		for (Person p : personenListe) {
		      Person temp = new Person(0, null, this.nachname, null, (String) value);
	if (p.equals(temp))
	return;
	}
	throw new ValidatorException(new FacesMessage("Login falsch!"));
	}

	public void postValidateName(ComponentSystemEvent ev) throws AbortProcessingException {
		 UIInput temp = (UIInput)ev.getComponent();
		this.nachname = (String)temp.getValue();
		}
	
	public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.loggedInPerson = null;
        return "index.xhtml";
    }
}
