package main;

import java.util.Arrays;
import java.io.IOException;
import utils.SongGeneratorUtils;
import utils.FileParser;
import java.util.ResourceBundle;

public class SongGenerator
{
    private static ResourceBundle filePathAndVariables;
    
    public static void main(String[] args) {
        System.out.println("Generating a random Lana Del Rey circa 2011-2014 song...");
        FileParser fileParser = new FileParser();
        SongGenerator.filePathAndVariables = ResourceBundle.getBundle("FilePathAndVariables");
        String pathToSongs = SongGenerator.filePathAndVariables.getString("lanaDelRey");
        int songOrder = Integer.parseInt(SongGenerator.filePathAndVariables.getString("defaultSongOrder"));
        int songStructureOrder = Integer.parseInt(SongGenerator.filePathAndVariables.getString("defaultSongStructureOrder"));
        try {
            SongGeneratorUtils songGeneratorUtils = new SongGeneratorUtils();
            String song = songGeneratorUtils.generateFullSong(pathToSongs, songOrder, songStructureOrder, fileParser);
            printSong(song);
        }
        catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }
    
    private static void printSong(String s) {
        String[] words = s.split("\\s");
        int wordsOnLine = 0;
        for (String word : words) {
            boolean hasProperNoun = Arrays.asList(SongGenerator.filePathAndVariables.getString("properNouns")
                    .split(", ")).contains(word);
            boolean isVerseType = word.length() > 4 && word.equals(word.toUpperCase())
                    && (word.matches("^[a-zA-Z]*$") || word.length() >= 10);
            if ((word.length() >= 1 && Character.isUpperCase(word.charAt(0)) && !hasProperNoun) ||
                    (hasProperNoun && wordsOnLine >= 8) || isVerseType) {
                System.out.println();
                wordsOnLine = 0;
                if(isVerseType) System.out.println();
            }
            System.out.print(word + " ");
            ++wordsOnLine;
            if (isVerseType) {
                System.out.println();
                System.out.println();
                wordsOnLine = 0;
            }
        }
    }
}