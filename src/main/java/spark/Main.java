package spark;

import com.google.gson.JsonParser;
import java.util.Iterator;
import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        
        port(getHerokuAssignedPort());
        
        
        post("/hello", (req, res) -> {
            
            
            
            return "lol";
            
      
        });
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //Port welcher Standardmäßig verwendet werden soll.
    }

}