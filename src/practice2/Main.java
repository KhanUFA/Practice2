package practice2;

public class Main {

    public static void main(String[] args) {
        OktmoData d = new OktmoData();
        d.readFile("oktmo.csv");
        System.out.println(d.getPlaces().size());

    }
}
