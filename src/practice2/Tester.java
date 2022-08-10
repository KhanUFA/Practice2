package practice2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tester {
    static OktmoData d;
    @BeforeAll
    static void read() {
        d=new OktmoData();
        d.readFile("oktmo.csv");
    }
    @Test void test() {
        String expectedString = "Раздел 1. Муниципальные образования субъектов Российской Федерации";
        assertEquals(expectedString, d.getPlaces().get(0).getName());

        assertEquals("с Уржум",d.findPlace("с Уржум"));
    }
}
