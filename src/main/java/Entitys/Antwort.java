package Entitys;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

/**
 * Diese Klasse stellt eine Antwortmoeglichkeit einer Aufgabe dar.
 * 
 * @author Seve
 */
@Entity
@IdClass(AntwortPK.class)
public class Antwort implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Die Aufgabe dessen Antwortmoeglichkeit diese Antwort ist.
     */
    @Id
    @ManyToOne
    private Aufgabe aufgabe;
    
    /**
     * Die Nummer der Antwortmoeglichkeit.
     */
    @Id
    private short nummer;
 
    /**
     * Die Antwot an sich.
     */
    @Column(length = 2000)
    private String antwort; 
    
    /**
     * Zeigt an ob diese Antwort eine richtige Antwort ist.
     */
    private Boolean richtig;
    
    /**
     * Gibt an wie haeufig diese Antwort gewaehlt wurde.
     */
    private long haeufigkeit;

    public Antwort(){

    }
    
    public Antwort(Aufgabe aufgabe, short nummer, String antwort, Boolean richtig) {
       this.aufgabe = aufgabe;
       this.nummer = nummer;
       this.antwort = antwort;
       this.richtig = richtig;
       this.haeufigkeit = 0;
    }
    
    /**
     * 
     * @return Die Nummer der Antwort unter den Antwortmoeglichkeiten einer Aufgabe.
     */
    public int getNummer(){
        return nummer;
    }
    
    /**
     * 
     * @param antwort Der neue Antworttext dieser Antwort.
     */
    public void setAntwort(String antwort){
        this.antwort = antwort;
    }
    
    /**
     * 
     * @return Der Antworttext dieser Antwort.
     */
    public String getAntwort(){
        return antwort;
    }
    
    /**
     * @param richtig Falls true ist die Aufgabe richtig.
     */
    public void setRichtig(boolean richtig){
        this.richtig = richtig;
    }
    
    /**
     * 
     * @return Falls true ist die Antwort eine richtige Antwort.
     */
    public Boolean getRichtig(){
        return richtig;
    }
    
    /**
     * Erhoeht den Wert, der zaehlt wie haeufig die Antwort gewaehlt wurde.
     */
    public void wurdeGewaehlt(){
        this.haeufigkeit++;
    }
    
    /**
     * Gibt zurueck wie Haeufig eine Antwort gewaehlt wurde.
     * 
     * @return Die Haeufigkeit.
     */
    public long getHaeufigkeit(){
        return haeufigkeit;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.antwort);
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
        final Antwort other = (Antwort) obj;
        if (this.nummer != other.nummer) {
            return false;
        }
        if (!Objects.equals(this.aufgabe, other.aufgabe)) {
            return false;
        }
        return true;
    }
 
    @Override
    public String toString() {
        return nummer + " " + antwort + " von " + aufgabe;
    }   
    
}
