import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class BuchungVerwaltung implements Serializable {
    private static final long serialVersionUID = 1L;

    private String bookingNumber;
    private List<PersonGeisternetz> bookedGeisternetze;
    private List<PersonGeisternetz> selectedGeisternetze;
    
    @Inject
    private GeisternetzVerwaltung geisternetzVerwaltung;

    // Getters and setters
    public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public List<PersonGeisternetz> getBookedGeisternetze() {
        return bookedGeisternetze;
    }

    public void setBookedGeisternetze(List<PersonGeisternetz> bookedGeisternetze) {
        this.bookedGeisternetze = bookedGeisternetze;
    }

    public List<PersonGeisternetz> getSelectedGeisternetze() {
        return selectedGeisternetze;
    }

    public void setSelectedGeisternetze(List<PersonGeisternetz> selectedGeisternetze) {
        this.selectedGeisternetze = selectedGeisternetze;
    }

    public void searchByBookingNumber() {
        bookedGeisternetze = geisternetzVerwaltung.findByBookingNumber(bookingNumber);
    }

    public void updateGeisternetzeStatus(String status) {
        for (PersonGeisternetz pg : selectedGeisternetze) {
            pg.getGeisternetz().setStatus(status);
            geisternetzVerwaltung.updateGeisternetz(pg.getGeisternetz()); // Update the status in your database or service
        }
    }
}
