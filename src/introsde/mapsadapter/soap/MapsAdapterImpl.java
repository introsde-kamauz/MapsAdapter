package introsde.mapsadapter.soap;
import introsde.document.model.*;

import javax.jws.WebService;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Response;

import java.net.URLEncoder;
import org.glassfish.jersey.client.ClientConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//Service Implementation

@WebService(endpointInterface = "introsde.mapsadapter.soap.MapsAdapter", serviceName="MapsService")
public class MapsAdapterImpl implements MapsAdapter {
	
	// es.
	// https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=Washington,DC&destinations=New+York+City,NY&key=AIzaSyCWb1D-EqhglO2EUgkPnzW3AHva_MuqHOk
	String GOOGLE_API_KEY = "AIzaSyCWb1D-EqhglO2EUgkPnzW3AHva_MuqHOk";
	JsonNode node;
	WebTarget service;
	
	@Override
	public Maps getMaps(String venue, String city, String origin) {
		// TODO Auto-generated method stub
		Maps m = new Maps();
		try {
			
			ClientConfig clientConfig = new ClientConfig();
			clientConfig.register(Maps.class);
			Client client = ClientBuilder.newClient(clientConfig);
			
			URLEncoder encoder = null;
			
			WebTarget webTarget = client.target("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+encoder.encode(origin)+"&destinations="+encoder.encode(venue+" "+city)+"&key="+GOOGLE_API_KEY);
			Builder request = webTarget.request();
			
			System.out.println("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+encoder.encode(origin)+"&destinations="+encoder.encode(venue+" "+city)+"&key="+GOOGLE_API_KEY);
			
			Response response = request.get();
			
			String json = response.readEntity(String.class);
			System.out.println(json);
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				node = mapper.readTree(json);
				System.out.println(node.asText());
				String status;
				if (node.findValue("status").toString() == "OK") {
					status = node.findValue("rows").findValue("elements").findValue("status").toString();
				} else {
					status = node.findValue("status").toString();
				}
				if (status.indexOf("NOT_FOUND") != -1 || status.indexOf("INVALID_REQUEST") != -1 || status.indexOf("ZERO_RESULTS") != -1) {
					m.setDistance("not found");
					m.setDuration("not found");
				} else {
					m.setDistance(node.findValue("distance").findValue("text").toString());
					m.setDuration(node.findValue("duration").findValue("text").toString());
				}
			} catch (Exception er) {
				// TODO Auto-generated catch block
				m.setDistance("not found");
				m.setDuration("not found");
				er.printStackTrace();
			}
			
			return m;
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			
			m.setDistance("not found");
			m.setDuration("not found");
			e1.printStackTrace();
		}
		
		return m;
			
	}
}