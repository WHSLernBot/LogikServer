package Controller;

import com.google.gson.JsonObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Diese Klasse stellt Methoden zur Verfuegung, um einen ThreadPool zu erzeugen 
 * und auszufuehren. Allerdings wird die Poolgroesse anfangs festgelegt.
 */
public class ControllerPool {
    /**
     * Gibt die Groesse des Threadpools an.
     */
    private static final int POOL_SIZE = 30;
    
    /**
     * Erzeugt ein ExecutorService Object, welches den ThreadPool verwaltet.
     */
    private ExecutorService pool;
    
    /**
     * Der Konstruktor initialisiert ein neues Executor Object, 
     * mit einer fixen Poolgroesse.
     */
    public void controller(){
        
        pool = Executors.newFixedThreadPool(POOL_SIZE);
        
    }
    
    /**
     * Fuehrt einen ControllerThread aus.
     * @param json Enthaelt alle wichtigen informationen, die vom Server kommen.
     */
    public void loese(JsonObject json) {
        
        pool.submit(new ControllerThread(json));
        
    }

}
