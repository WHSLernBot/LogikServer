package Entitys;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Diese Klasse stellt eine Aufgabe dar, die der Benutzer als naechstes 
 * zu bearbeitet hat. Ausserdem verweist sie auf eine Aufgabe die 
 * er dannach bearbeiten soll.
 * 
 * @author Betuel
 */
@Entity
@IdClass(ZuAufgabePK.class)
public class ZuAufgabe implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Ist true, falls dies die letzte Aufgabe ist.
     */
    private boolean zuletzt;
    
    /**
     * Die zubearbeitende Aufgabe.
     */
    @Id
    @ManyToOne
    private Aufgabe aufgabe;
    
    /**
     * Der zugehoerige Lernstatus.
     */
    @Id
    @OneToOne
    private LernStatus lernStatus;
    
    /**
     * Die naechste zu bearbeitenden Aufgabe.
     */
    @OneToOne
    private ZuAufgabe naechsteAufgabe;
    
    public ZuAufgabe(){
        
    }
    
    public ZuAufgabe(LernStatus status, Aufgabe aufgabe, boolean zuletzt){
        this.lernStatus = status;
        this.aufgabe = aufgabe;
        this.zuletzt = zuletzt;
    }
    
    public LernStatus getStatus(){
        return lernStatus;
    }
    
    public boolean istZuletzt(){
        return zuletzt;
    }

    public Aufgabe getAufgabe() {
        return aufgabe;
    }

    public ZuAufgabe getNaechsteAufgabe() {
        return naechsteAufgabe;
    }

    public ZuAufgabe setNaechsteAufgabe(Aufgabe aufgabe) {
        this.naechsteAufgabe = new ZuAufgabe(lernStatus,aufgabe,true);
        this.zuletzt = false;
        
        return naechsteAufgabe;
    } 
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.aufgabe);
        hash = 67 * hash + Objects.hashCode(this.lernStatus);
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
        final ZuAufgabe other = (ZuAufgabe) obj;
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
        return "Zuletzt bearbeitete Aufgabe: " + lernStatus;
    }
    
}
