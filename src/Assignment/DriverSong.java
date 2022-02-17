package Assignment;

import java.util.*;

public class DriverSong {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        list.add("pop");
        list.add("rock");

        list2.add("pop");

        list3.add("rock");

        Song[] songs = {new Song("Title-1", 5, list), new Song("Title-2", 6, list2), new Song("Title-3", 4, list3)
        };
        Map<String,Integer> maximumOfGenre = new LinkedHashMap<>(Song.maximumOfGenres(songs));
        String[] s = new String[2];
        s[0] = "pop : ";
        s[1] = "push: ";
        int counter = 0;
        for (Map.Entry<String, Integer> entry : maximumOfGenre.entrySet()) {
            System.out.println(s[counter] + entry.getKey() +
                    ", Value = " + entry.getValue());
            counter++;
        }
    }


}