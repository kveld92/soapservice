package nl.hu.iac.wsconsumer;

import nl.hu.iac.wsinterface.*;

import java.math.BigInteger;

/**
 * Created by Kevin Veld on 16-4-2017.
 */
public class WSConsumer {

    public static void main(String[] args) {
        BmiServiceService service = new BmiServiceService();
        BmiRequest request = new ObjectFactory().createBmiRequest();
        request.setLength(new BigInteger("179"));
        request.setWeight(new BigInteger("75"));
        System.out.println("De lengte "+request.getLength()+ " en het gewicht van " +request.getWeight()+ " kg bepaald dat uw bmi uitkomt op: ");
        BmiResponse response = null;
        try {
            response = service.getWSPort().calculateBmi(request);
        } catch (Fault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(response.getResult());

    }

}
