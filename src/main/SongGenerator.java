package main;

import java.util.Arrays;
import java.io.IOException;
import java.io.FileNotFoundException;
import utils.SongGeneratorUtils;
import utils.FileParser;
import java.util.ResourceBundle;

public class SongGenerator
{
    private static ResourceBundle filePathAndVariables;
    
    public static void main(final String[] args) {
        System.out.println("Generating a random Lana Del Rey circa 2011-2014 song...");
        final FileParser fileParser = new FileParser();
        SongGenerator.filePathAndVariables = ResourceBundle.getBundle("FilePathAndVariables");
        final String pathToSongs = SongGenerator.filePathAndVariables.getString("lanaDelRey");
        final int songOrder = Integer.valueOf(SongGenerator.filePathAndVariables.getString("defaultSongOrder"));
        final int songStructureOrder = Integer.valueOf(SongGenerator.filePathAndVariables.getString("defaultSongStructureOrder"));
        try {
            final SongGeneratorUtils songGeneratorUtils = new SongGeneratorUtils();
            final String song = songGeneratorUtils.generateFullSong(pathToSongs, songOrder, songStructureOrder, fileParser);
            printSong(song);
        }
        catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
        }
        catch (IOException e) {
            System.err.println("IOEException: " + e.getMessage());
        }
    }
    
    private static void printSong(final String s) {
        final String[] words = s.split("\\s");
        int wordsOnLine = 0;
        for (int i = 0; i < words.length; ++i) {
            final boolean hasProperNoun = Arrays.asList(SongGenerator.filePathAndVariables.getString("properNouns").split(", ")).contains(words[i]);
            if ((words[i].length() >= 1 && Character.isUpperCase(words[i].charAt(0)) && !hasProperNoun) || (hasProperNoun && wordsOnLine > 8) || (words[i].length() > 1 && words[i].equals(words[i].toUpperCase()))) {
                System.out.println("\n");
                wordsOnLine = 0;
            }
            System.out.println(words[i] + " ");
            ++wordsOnLine;
            if (words[i].length() > 1 && words[i].equals(words[i].toUpperCase())) {
                System.out.println("\n");
                wordsOnLine = 0;
            }
        }
    }
}