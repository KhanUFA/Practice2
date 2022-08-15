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
        String expectedString = "Залесово";
        assertEquals(expectedString, d.getPlaces().get(0).getName());
    }

    @Test
    void testMethodSearch(){
        assertEquals("Уржум", d.findPlace("Уржум").getName());
    }

    @Test
    void testOutputMethods(){
        System.out.println("----------------------------НП по номеру региона и района----------------------------");
        d.getAllPlacesOnDistrict(1, 604).limit(3).forEach(System.out::println);
        System.out.println("----------------------------НП по номеру региона----------------------------");
        d.getAllPlacesByRegion(1).limit(3).forEach(System.out::println);
        System.out.println("----------------------------Заканичаывется на \"-ово\" или содержит \"-\"----------------------------");
        d.showPlacesByUniqueFilter();
        System.out.println("----------------------------Стандартные названия НП----------------------------");
        d.showDistinctNamePlaces();
        System.out.println("----------------------------Длиное название НП в регионе----------------------------");
        d.showLongNameInRegion("Алтайский муниципальный район");
        System.out.println("----------------------------Кол-во повторений названий----------------------------");
        d.showRatingNames();
    }
}

