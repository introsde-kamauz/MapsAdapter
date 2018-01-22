package introsde.document.client;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import introsde.document.model.*;
import introsde.mapsadapter.soap.*;

public class Client {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://mapsadapter.herokuapp.com/?wsdl");
        // 1st argument service URI, refer to wsdl document above
        // 2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://soap.mapsadapter.introsde/", "MapsService");
        Service service = Service.create(url, qname);
        MapsAdapter hello = service.getPort(MapsAdapter.class);
 
        Maps m = hello.getMaps("Gasoline", "Trento", "Rovereto");

        System.out.println(m.getDistance());
        System.out.println(m.getDuration());
    }
}