package practice2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import static org.junit.jupiter.api.Assertions.*;

public class Tester {
    static OktmoData d;
    @BeforeAll
    static void read() {
        d=new OktmoData();
        d.readFile("oktmo.csv");
    }
    @Test
    void testReadAndCreateList() {
        String expectedString = "с Залесово";
        assertEquals(expectedString, d.getPlaces().get(0).getName());
    }

    @Test
    void testMethodSearch(){
        assertEquals("с Уржум",d.findPlace("с Уржум"));
    }
}

