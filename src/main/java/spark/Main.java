package spark;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        get("/hello", "application/json", (req, res) -> {
            
            JsonParser parser = new JsonParser();
            
            JsonElement element = parser.parse(req.body());
            
            JsonObject obj = element.getAsJsonObject();
            
            res.type("application/json");
            res.status(200);
            
            return obj;
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