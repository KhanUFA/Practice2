package practice2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
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
   private static final Pattern RE=Pattern.compile("(\\d+)\";\"(\\d+)\";\"(\\d+)\";\"(\\d+)\".+?;\"([а-я]*)\\s?([А-Яа-я].*?)\";");
    private void readLine(String s) {
        Matcher m = RE.matcher(s);
        if (m.find()) {
            if(m.group(6).contains("район") && m.group(3).equals("000")) { //"01";"605";"000";"000";"1";"1";"Благовещенский муниципальный район";"рп Благовещенка";;"000";"0";14.06.2013;01.01.2014
                places.add(new Place(
                        Integer.parseInt(m.group(1)),
                        Integer.parseInt(m.group(2)),
                        Integer.parseInt(m.group(3)),
                        Integer.parseInt(m.group(4)),
                        "район", m.group(6)
                ));
            } else if (!m.group(4).equals("000")) {
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

    public Place findPlace(String name){
        return places.stream().filter(place -> place.getName().equals(name)).findFirst().orElse(null);
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

    public void showDistinctNamePlaces(){
        places.stream().sorted(Comparator.comparing(Place::getName)).map(Place::getName).distinct().limit(5).forEach(System.out::println);
    }

    public void showLongNameInRegion(String regionName){
        int codeRegion = findPlace(regionName).code1;
        System.out.println(places.stream().filter(place -> place.getCode1() == codeRegion && place.getCode4() != 0)
                .max(Comparator.comparingInt(place -> place.getName().length())).orElse(null));
    }

    public void showRatingNames(){
        places.stream().map(Place::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().sorted((place1, place2) -> (int) (place2.getValue() - place1.getValue()))
                .limit(5).forEach(placeEntry -> System.out.printf("%s: %d\n", placeEntry.getKey(), placeEntry.getValue()));
    }
}
