import org.junit.Test;

import static org.junit.Assert.*;

public class FirstTest {

    @Test
    public void add() {
        assertEquals(3,new First().add(1,2));
    }
}