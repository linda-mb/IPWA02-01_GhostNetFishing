import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.io.IOException;

@Named
@SessionScoped
public class LoginController implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nachname;
    private String telefonnummer;
    private boolean loggedIn = false;
    private Person loggedInPerson;

    @Inject
    private GeisternetzVerwaltung geisternetzVerwaltung;

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

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public Person getLoggedInPerson() {
        return loggedInPerson;
    }

    public String getLoggedInVorname() {
        return loggedInPerson != null ? loggedInPerson.getVorname() : "";
    }

    public String getLoggedInNachname() {
        return loggedInPerson != null ? loggedInPerson.getNachname() : "";
    }

    public String login() {
        Person person = geisternetzVerwaltung.findPersonByNachnameAndTelefonnummer(nachname, telefonnummer);
        if (person != null) {
            loggedIn = true;
            loggedInPerson = person;
            return "dashboardBergendePerson?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Failed", "Invalid Nachname or Telefonnummer"));
            return null;
        }
    }

    public String logout() {
        loggedIn = false;
        loggedInPerson = null;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

    public void checkLogin() {
        if (!loggedIn) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
