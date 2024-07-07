import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Named
@SessionScoped
public class GeisternetzBergenController implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Geisternetz> selectedGeisternetze = new ArrayList<>();
    private String bookingNumber;

    @Inject
    private Person person;

    // Getters and setters
    public List<Geisternetz> getSelectedGeisternetze() {
        return selectedGeisternetze;
    }

    public void setSelectedGeisternetze(List<Geisternetz> selectedGeisternetze) {
        this.selectedGeisternetze = selectedGeisternetze;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void bookGeisternetze() {
        if (selectedGeisternetze != null && !selectedGeisternetze.isEmpty()) {
            bookingNumber = UUID.randomUUID().toString();
            System.out.println("Booked Geisternetze:");
            for (Geisternetz netz : selectedGeisternetze) {
                PersonGeisternetz association = new PersonGeisternetz();
                association.setPerson(person);
                association.setGeisternetz(netz);
                association.setRolle("Bergender"); // Example role
                association.setBuchungsnummer(bookingNumber); // Use the single booking number
                System.out.println("Geisternetz ID: " + netz.getId() + " Buchungsnummer: " + association.getBuchungsnummer());
            }
            System.out.println("Booking Number: " + bookingNumber);
        } else {
            System.out.println("No Geisternetze selected.");
        }
    }
}
