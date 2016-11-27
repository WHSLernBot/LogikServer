package Message;


import Entitys.Klausur;
import Entitys.Aufgabe;
import Entitys.LernStatus;
import Entitys.Uni;
import com.google.gson.JsonObject;
import java.util.Collection;
import main.CBBenutzer;
import main.ChatBotManager;
/**
 * Die Klasse MessageCreator stellt Methoden zur Verfuegung, um aus Objekten,
 * ein JsonObject zu erzeugen.
 */
public class MessageCreator {

    private final JsonObject jResponse;
    
    private final JsonObject jUser;
    
    private final JsonObject jAufgabe;
    
    private final JsonObject jInfo;
    
    private final JsonObject jUni;
    
    private final JsonObject jKlausurinfo;
    
    /**
     * Initialisiert alle Objekte.
     */
    public MessageCreator() {
        jResponse = new JsonObject();
        jUser = new JsonObject();
        jAufgabe = new JsonObject();
        jInfo = new JsonObject();
        jUni = new JsonObject();
        jKlausurinfo = new JsonObject();
    }
    
    /**
     * Die Methode erstellt ein JsonObject, fuer eine Uebergebene Aufgabe, mit 
     * entsprechenen Aufgabentext.
     * @param id Id des Nutzers.
     * @param plattform Plattform des Nutzers.
     * @param witSession
     * @param aufgabe Aufgabe fuer den Nutzer.
     */
    public void erstelleAufgabenJson(long id,long plattform,String witSession, Aufgabe aufgabe) {
        jUser.addProperty("id", id);
        jUser.addProperty("plattform", plattform);
        jUser.addProperty("witsession", witSession);
        
        jAufgabe.addProperty("frage", aufgabe.getFrage());
        jAufgabe.addProperty("hinweis", aufgabe.getHinweis());
        jAufgabe.addProperty("verweis", aufgabe.getVerweis());
        
        jResponse.add("user", jUser);
        jResponse.add("aufgabe",jAufgabe);
        jResponse.addProperty("nachricht", gibText("aufgabe"));
        erzeugeNachricht(jResponse);
    }
    
    /**
     * Die Methode erstellt ein JsonObject, mit allen Vorhandenen Unis, die uebergeben werden.
     * @param id Id des Nutzers.
     * @param plattform Plattform des Nutzers.
     * @param witSession
     * @param unis Enthaelt alle Unis, die zur auswahl stehen.
     */
    public void erstelleUniJason(long id, long plattform,String witSession,Collection<Uni> unis){
        jUser.addProperty("id", id);
        jUser.addProperty("plattform", plattform);
        jUser.addProperty("witsession", witSession);
        int i = 0;
        for (Uni name : unis) {
            this.jUni.addProperty("name" + i, name.getName());
            this.jUni.addProperty("nameid", name.getId());
            i++;
        }
        jResponse.add("user", jUser);
        jResponse.add("uni", jUni);
        jResponse.addProperty("nachricht", gibText("uni"));
        erzeugeNachricht(jResponse);
    }
    
    /**
     * Die Methode erstellt ein JsonObject, mit allen Informationen, 
     * die zur Klausur vorhanden sind.
     * @param id Id des Nutzers.
     * @param plattform Plattform des Nutzers.     
     * @param witSession     
     * @param klausur Enthaelt alle Informatonen zur Klausur.
     */
    public void erstelleKlausurInfoJson(long id, long plattform,String witSession, Klausur klausur) {
        jUser.addProperty("id", id);
        jUser.addProperty("plattform", plattform);
        jUser.addProperty("witsession", witSession);
        
        jKlausurinfo.addProperty("hilfsmittel",klausur.getHilfsmittel());
        jKlausurinfo.addProperty("ort",klausur.getOrt());
        
        jResponse.add("user", jUser);
        jResponse.add("klausurinfo", jKlausurinfo);     
        jResponse.addProperty("nachricht", gibText("info"));
        erzeugeNachricht(jResponse);
    }
    
    /**
     * Die Methode erstellt ein JsonObject, mit allen Informationen, 
     * die zu dem Benutzer vorhanden sind.
     * @param benutzer 
     */
    public void erstelleBenutzerInfoNachricht(CBBenutzer benutzer) {
        jUser.addProperty("id", benutzer.getBenutzer().getId());
        jUser.addProperty("plattform", benutzer.getBenutzer().getPlattform().getPfID());
        jUser.addProperty("witsession", benutzer.getBenutzer().getPlattform().getWitSession());
        Collection<LernStatus> stadi = benutzer.getBenutzer().getLernStadi();
        int i = 0;
        for (LernStatus status : stadi) {
            this.jUni.addProperty("status" + i, status.getSumPunkte());
            i++;
        }
        jResponse.add("user", jUser);
        jResponse.add("info", jInfo);     
        jResponse.addProperty("nachricht", gibText("info"));
        erzeugeNachricht(jResponse);
    }
    
    
    /**
     * Erzeugt einen Text, fuer die entsprechende Methode.
     * @param methode Gibt an, fuer welche Methode der Text erzeugt werden soll.
     * @return Gibt den entsprechenden Text zurueck.
     */
    private String gibText(String methode) {
        String text = "Ups... da ist ein fehler unterlaufen!";
        switch(methode) {
            case "aufgabe":
                text = "Hier ist eine neue Aufage: ";
                break;
            case "uni":
                text = "Das sind alle verf�gbaren Uni's";
                break;
            case "info":
                text = "Hier hast du die gew�nschten Info's!";
                break;
        }
        return text;
    }
    
    /**
     * Fuegt eine Nachricht dem ChatBotManager hinzu.
     * @param json Enthaelt alle wichtigen Informationen.
     */
    private void erzeugeNachricht(JsonObject json){
        ChatBotManager cbm = ChatBotManager.getInstance();
        Nachricht nachricht = new Nachricht(json,null);
        cbm.addNachricht(nachricht);
    }
}