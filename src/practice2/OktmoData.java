package practice2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public Stream<Place> getAllPlacesOnArea(Place place){
        return places.stream().filter(currentPlace -> currentPlace.getCode1() == place.getCode1() &&
                currentPlace.getCode2() == place.getCode2());
    }

    public Stream<Place> getAllPlacesByRegion(Place place){
        return places.stream().filter(currentPlace -> currentPlace.getCode1() == place.getCode1() &&
                currentPlace.getCode4() == place.getCode4());
    }

    //name ends "-ово" or have "-"
    public void getPlacesByUniqueFilter(){
        places.stream().filter(place -> place.getName().endsWith("ово") || place.getName().contains("-"))
                .forEach(System.out::println);
    }

    public void  getRatingName(){

    }
}
