import java.io.Serializable;
import java.util.List;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
	@ViewScoped
	public class AuswahlGeisternetze implements Serializable {
	    private static final long serialVersionUID = 1L;

	    private List<Geisternetz> selectedGeisternetze;

		public List<Geisternetz> getSelectedGeisternetze() {
			return selectedGeisternetze;
		}

		public void setSelectedGeisternetze(List<Geisternetz> selectedGeisternetze) {
			this.selectedGeisternetze = selectedGeisternetze;
		}


	}

