package spark;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static spark.Service.ignite;

public class Main {
    
    private static final int POOL_SIZE = 20;
   

    public static void main(String[] args) {
        
        igniteFirstSpark();  
       
    }
    
//    static void igniteSecondSpark() {
//        
//        Service http = ignite();
//        
//        
//        http.get("/basic", (req, res) -> "Hello World 2");
//        
//        
//    }
    
    static void igniteFirstSpark() {
        Service http = ignite()
                      .port(getHerokuAssignedPort())
                      .threadPool(POOL_SIZE);
        
        
        http.get("/", (req, res) -> "Ja es geht zumindest.");
        
        http.post("/hello", (req, res) -> {
            
            JsonParser parser = new JsonParser();
            
            String s = req.body();
            
            JsonObject obj= new JsonObject();
            
            JsonObject objBody;
            
            obj.add("body", parser.parse(s));
            objBody = obj.get("body").getAsJsonObject();
            
//            System.out.println(obj.get("body").isJsonPrimitive());
//            System.out.println(objBody.get("username"));
//            System.out.println(objBody.get("key"));
//            System.out.println(objBody.get("zahlen"));
            
            Rand rd = new Rand(objBody.get("key").getAsInt());
//                System.out.println(ChatBotManager.getInstance().jetzt());
            
            System.out.println(rd.toString());
            return rd.toString();
      
        });
        
        
        http.get("/hello2", (q, a) -> "Hello from port 1234!");
        
        
        http.get("/hello3", (q, a) -> "Hello from port 5678!");
        
        
        
    }
    
    

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //Port welcher Standardmäßig verwendet werden soll.
    }

}