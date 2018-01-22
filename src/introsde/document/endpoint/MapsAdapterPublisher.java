package introsde.document.endpoint;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;
import javax.xml.ws.Endpoint;

import introsde.mapsadapter.soap.MapsAdapterImpl;

public class MapsAdapterPublisher {
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException{
        String PROTOCOL = "http://";
        String HOSTNAME = InetAddress.getLocalHost().getHostAddress();
        if (HOSTNAME.equals("127.0.0.1"))
        {
            HOSTNAME = "localhost";
        }
        String PORT = "6905";
        String BASE_URL = "/mapsadapter";

        if (String.valueOf(System.getenv("PORT")) != "null"){
            PORT=String.valueOf(System.getenv("PORT"));
        }
        
        String endpointUrl = PROTOCOL+HOSTNAME+":"+PORT+BASE_URL;
        System.out.println("Starting Meteo Adapter Service...");
        System.out.println("--> Published. Check out "+endpointUrl+"?wsdl");
        Endpoint.publish(endpointUrl, new MapsAdapterImpl());
    }
}