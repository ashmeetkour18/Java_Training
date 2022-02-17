package Assignment;


import java.util.*;

class Song {
    private static String maxRockTitle;
    private static String maxPopTitle;
    private final String title;

    public static int maxRock = 0;
    public static int maxPop;

    private final int playCount;
    private final List<String> genre;

    public Song(String title, int playCount, List<String> genre) {
        this.title = title;
        this.playCount = playCount;
        this.genre = genre;

    }

    public String getTitle() {
        return title;
    }

    private int getPlayCount() {
        return playCount;
    }

    public List<String> getGenre() {
        return genre;
    }


    @Override
    public String toString() {
        return ("title= " + title + " " + " count =" + playCount);
    }

    public static Map<String, Integer> maximumOfGenres(Song[] o) {

        Map<String, Integer> map = new LinkedHashMap<>();
        for (Song songs : o) {

            if (songs.getGenre().contains("rock")) {
                if (songs.playCount > maxRock) {
                    maxRock = songs.playCount;
                    maxRockTitle = songs.title;
                }
            }
            if (songs.getGenre().contains("pop")) {
                if (songs.playCount > maxPop) {
                    maxPop = songs.playCount;
                    maxPopTitle = songs.title;
                }
            }
        }
        map.put(maxPopTitle, maxPop);
        map.put(maxRockTitle, maxRock);
        return map;
    }
}

/*
Suppose you are given a List of songs.
genre = pop, rock,
write a function that returns for each genre the song that is played
the maximum number of times.
song = {"title-1", 5 , ["pop","rock"]}, {"title-2",6,["pop"]}, {"title-3",4,["rock"]}
output:
pop -> title-2,6
rock -> title-1, 5
 */