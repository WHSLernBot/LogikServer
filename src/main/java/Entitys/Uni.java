package Entitys;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Diese Klasse stellt eine Universitaet und einen dort angesiedelten Fachbereich dar.
 * @author Seve
 */
@Entity
public class Uni implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * Eideutige id der Uni.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private short id;
    
    /**
     * Name der Universitaet - Name des Fachbereichs.
     */
    @Column(length = 200)
    private String name;
    
    /**
     * Benutzer die diese Uni/Fachbereich besuchen.
     */
    @OneToMany(mappedBy="uni", cascade=CascadeType.ALL,orphanRemoval = true)
    private Collection<Benutzer> benutzer;
    
    /**
     * Module die dieser Fachbereich anbietet.
     */
    @OneToMany(mappedBy="uni", cascade=CascadeType.ALL,orphanRemoval = true)
    private Collection<Modul> modul;
    
    /**
     * Pruefungsperioden die dieser Fachbereich hat.
     */
    @OneToMany(mappedBy="uni", cascade=CascadeType.ALL,orphanRemoval = true)
    private Collection<Pruefungsperiode> pruefungsperiode;
    
    public Uni() {}
    
    public Uni(String name) {
        this.name = name;
    }

    public short getId() {
        return id;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }

    public Collection<Benutzer> getBenutzer() {
        return benutzer;
    }

    public void addBenutzer(Benutzer benutzer) {
        this.benutzer.add(benutzer);
    }

    public Collection<Modul> getModul() {
        return modul;
    }

    public void addModul(String kuerzel, String name) {
        this.modul.add(new Modul(this,kuerzel,name));
    }

    public Collection<Pruefungsperiode> getPruefungsperiode() {
        return pruefungsperiode;
    }

    public void addPruefungsperiode(short jahr, short phase, Date anmeldebeginn, Date anfang, Date ende) {
        this.pruefungsperiode.add(new Pruefungsperiode(this,jahr,phase,anmeldebeginn,anfang,ende));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
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
        final Uni other = (Uni) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Uni[ id=" + id + " ]";
    }
    
}
