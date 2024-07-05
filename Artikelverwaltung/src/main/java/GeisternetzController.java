import java.io.Serializable;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class GeisternetzController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private int index = 0;
	
	@Inject
	private GeisternetzVerwaltung geisternetzVerwaltung;
	
	public Geisternetz getGeisternetz()  {
		return geisternetzVerwaltung.getBestand().get(index);
	}
	
	 public void vor()
	    {
	      if (index < getMaxIndex()) {
	        index++;
	      }
	    }

	    public void zurueck()
	    {
	      if (index > 0) {
	        index--;
	      }
	    }

	
	
	public int getIndex() {
		return index;
	}
	
	 public int getMaxIndex() {
	        return geisternetzVerwaltung.getBestand().size() - 1;
	    }


}
