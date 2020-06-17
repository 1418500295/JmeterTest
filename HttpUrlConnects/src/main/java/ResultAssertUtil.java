import org.testng.Assert;

public class ResultAssertUtil {

    public static void equals(Object A, String B){
        if (!(A.equals(B))){
            throw new IllegalArgumentException();
        }
    }
}
