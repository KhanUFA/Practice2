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
    @Test
    void testReadAndCreateList() {
        String expectedString = "с Залесово";
        assertEquals(expectedString, d.getPlaces().get(0).getName());
    }

    @Test
    void testMethodSearch(){
        assertEquals("Уржум",d.findPlace("Уржум"));
    }

    @Test
    void testOutputMethods(){
        d.getAllPlacesOnDistrict(1, 604).limit(3).forEach(System.out::println);
        System.out.println("----------------------------");
        d.getAllPlacesByRegion(1).limit(3).forEach(System.out::println);
        System.out.println("----------------------------");
        d.showPlacesByUniqueFilter();
        System.out.println("----------------------------");
        d.getDistinctNamePlaces();

    }
}

