package Resource;

/**
 * Created by Kevin Veld on 16-4-2017.
 */
import junit.framework.*;
import nl.hu.iac.wsinterface.*;

import java.math.BigInteger;

public class BmiTest extends TestCase{

    public void test1(){
        BmiServiceService service = new BmiServiceService();
        BmiRequest request = new ObjectFactory().createBmiRequest();
        request.setLength(new BigInteger("180"));
        request.setWeight(new BigInteger("80"));

        BmiResponse response = null;
        try {
            response = service.getWSPort().calculateBmi(request);
        } catch (Fault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals("24", response.getResult().toString());
    }

    public void test2(){
        BmiServiceService service = new BmiServiceService();
        BmiRequest request = new ObjectFactory().createBmiRequest();
        request.setLength(new BigInteger("175"));
        request.setWeight(new BigInteger("50"));

        BmiResponse response = null;
        try {
            response = service.getWSPort().calculateBmi(request);
        } catch (Fault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals("16",response.getResult().toString());
    }
}
