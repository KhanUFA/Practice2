package practice2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OktmoData {
    private List<Place> places = new ArrayList<>();

    public void readFile(String filename) {
        places.clear();
        Path p = Paths.get(filename);
        try {
            List<String> lines = Files.readAllLines(p, Charset.forName("cp1251"));
            for (String s: lines) {
                readLine(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
//"01";"512";"000";"146";"9";"2";"п Калиновка";;;"493";"3";12.08.2021;01.01.2022
   private static final Pattern RE=Pattern.compile("\"(\\d+)\";\"(\\d+)\";\"(\\d+)\";\"(\\d+)\";.+\"([а-я]*?)\\s([А-Яа-я].*?)\"");
    private void readLine(String s) {
        Matcher m = RE.matcher(s);
        if (m.find()) {
            if(!m.group(4).equals("000")) {
                places.add(new Place(
                        Integer.parseInt(m.group(1)),
                        Integer.parseInt(m.group(2)),
                        Integer.parseInt(m.group(3)),
                        Integer.parseInt(m.group(4)),
                        m.group(5), m.group(6)
                ));
            }
        }
    }

    public List<Place> getPlaces() {
        return places;
    }

    public String findPlace(String name){
        return places.stream().filter(place -> place.getName().equals(name)).findFirst().get().getName();
    }

    public Stream<Place> getAllPlacesOnDistrict(int regionNumber, int districtNumber){
        return places.stream().filter(currentPlace -> currentPlace.getCode1() == regionNumber &&
                currentPlace.getCode2() == districtNumber);
    }

    public Stream<Place> getAllPlacesByRegion(int regionNumber){
        return places.stream().filter(currentPlace -> currentPlace.getCode1() == regionNumber);
    }

    //name ends "-ово" or have "-"
    public void showPlacesByUniqueFilter(){
        List<Place> distinctPlaces = places.stream().filter(place -> place.getName().endsWith("ово") || place.getName().contains("-"))
                .collect(Collectors.toList());
        for (int i = 0; i < 5; i++) {
            System.out.println(distinctPlaces.get(i).getName());
        }
    }

    public void  getDistinctNamePlaces(){
        List<Place> distinctPlaces = places.stream().sorted(Comparator.comparingInt(place -> place.getName().charAt(0))).distinct()
                .collect(Collectors.toList());
        for (int i = 0; i < 5; i++) {
            System.out.println(distinctPlaces.get(i).getName());
        }
    }

    public void longNameInRegion(){

    }
}
