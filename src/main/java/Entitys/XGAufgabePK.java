package Entitys;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 * Primary Key Klasse von XGAufgabe.
 * @author Betuel
 */
@Embeddable
public class XGAufgabePK implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long aufgabe;
    
    private LernStatusPK lernStatus;

    public XGAufgabePK() {}

    public XGAufgabePK(Long aufgabe, LernStatusPK lernStatus) {
        this.aufgabe = aufgabe;
        this.lernStatus = lernStatus;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.aufgabe);
        hash = 41 * hash + Objects.hashCode(this.lernStatus);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final XGAufgabePK other = (XGAufgabePK) obj;
        if (!Objects.equals(this.aufgabe, other.aufgabe)) {
            return false;
        }
        if (!Objects.equals(this.lernStatus, other.lernStatus)) {
            return false;
        }
        return true;
    }
    
}
