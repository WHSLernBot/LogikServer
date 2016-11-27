package Entitys;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

/**
 * Diese Klasse stellt eine eXtra Gel�ste Aufgabe dar, die auserhalb der
 * ausgedachten Reihenfolge bearbeitet wurde.
 * @author Betuel
 */
@Entity
@IdClass(XGAufgabePK.class)
public class XGAufgabe implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Aufgabe die bearbeitet wurde.
     */
    @Id
    @ManyToOne
    private Aufgabe aufgabe;
    
    /**
     * Zugehoeriger Lernstatus der diese Aufgabe bearbeitet hat.
     */
    @Id
    @ManyToOne
    private LernStatus lernStatus;
    
    public XGAufgabe(){}

    public XGAufgabe(Aufgabe aufgabe, LernStatus lernStatus) {
        this.aufgabe = aufgabe;
        this.lernStatus = lernStatus;
    }

    public Aufgabe getAufgabe() {
        return aufgabe;
    }

    public LernStatus getLernStatus() {
        return lernStatus;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.aufgabe);
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
        final XGAufgabe other = (XGAufgabe) obj;
        if (!Objects.equals(this.aufgabe, other.aufgabe)) {
            return false;
        }
        if (!Objects.equals(this.lernStatus, other.lernStatus)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aufgabe " + aufgabe + " " +  lernStatus;
    }
    
}
