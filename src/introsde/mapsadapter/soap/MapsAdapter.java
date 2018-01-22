package introsde.mapsadapter.soap;
import introsde.document.model.*;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface MapsAdapter {

	// 1
	@WebMethod(operationName="getMaps")
	@WebResult(name="getMaps") 
	public Maps getMaps(String venue, String city, String origin) ;

}