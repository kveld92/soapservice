package nl.hu.iac.wsproducer;

import com.sun.xml.ws.developer.SchemaValidation;
import nl.hu.iac.wsinterface.*;

import javax.jws.WebService;
import java.math.BigInteger;

/**
 * Created by Kevin Veld on 16-4-2017.
 */

@WebService(endpointInterface = "nl.hu.iac.wsinterface.BmiServiceInterface")
@SchemaValidation(handler = SchemaValidationErrorHandler.class)

public class BmiServiceImpl implements BmiServiceInterface {

    @Override
    public BmiResponse calculateBmi(BmiRequest request) throws Fault {
        System.out.println("Request object "+request.getLength()+ " " +request.getWeight());
        ObjectFactory factory = new ObjectFactory();
        BmiResponse response = factory.createBmiResponse();
        try {
            // de formule voor het berekenen van je bmi is:
            // kg / (lengte*lengte)
            Double meters  = request.getLength().doubleValue()/100;
            Double result = request.getWeight().doubleValue()/(meters*meters);
            response.setResult(BigInteger.valueOf(result.longValue()));
        } catch (RuntimeException e) {
            BmiFault x = factory.createBmiFault();
            x.setErrorCode((short) 1);
            x.setMessage("Kan de lengte van " + request.getLength()
                    + " en het gewicht " + request.getWeight().toString()
                    + " niet berekenen.");
            Fault fault = new Fault(
                    "Er ging iets mis met het berekenen van je bmi", x);
            throw fault;
        }
        return response;
    }
}
