package Entitys;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 * Diese Klasse stellt einer Aufgabe dar, die von einem Benutzer bearbeitet wurde.
 * 
 * @author Seve
 */
@Entity
@IdClass(BeAufgabePK.class)
public class BeAufgabe implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Die bearbeitete Aufgabe.
     */
    @Id
    @ManyToOne
    private Aufgabe aufgabe;
    
    /**
     * Der Zugehoerige Lernstatus des Benutzers.
     */
    @Id
    @ManyToOne
    private LernStatus lernStatus;
    
    /**
     * Ist true, falls die Aufgabe richtig beantwortet wurde.
     */
    private Boolean richtig;
    
    /**
     * Das Datum, an dem die Aufgabe bearbeitet wurde.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datum;
    
    /**
     * Ist true, falls ein Hinweis benutzt wurde.
     */
    private Boolean hinweis;
    
    /**
     * Ist true, falls die Aufgabe beantwortet wurde,
     * oder von Benutzer noch aussteht (vielleicht nichtmer sinnvoll)
     */
    private Boolean beantwortet;
    
    public BeAufgabe(){
        
    }
    
    public BeAufgabe(Aufgabe aufgabe, LernStatus lernStatus, Boolean richtig, 
            Date datum, Boolean hinweis, Boolean beantwortet) {
        
        this.aufgabe = aufgabe;
        this.lernStatus = lernStatus;
        this.richtig = richtig;
        this.datum = datum;
        this.hinweis = hinweis;
        this.beantwortet = beantwortet;
    }
    
    public Boolean istRichtig(){
        return richtig;
    }
    
    public Date getDatum(){
        return datum;
    }
    
    public Boolean getHinweis(){
        return hinweis;
    }
    
    public Boolean istBeantwortet(){
        return beantwortet;
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
        hash = 79 * hash + Objects.hashCode(this.datum);
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
        final BeAufgabe other = (BeAufgabe) obj;
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
        return "Bearbeitete Aufgabe " + aufgabe + " " +  lernStatus;
    }
    
}
