package modelers;

import java.util.Iterator;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Stream;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class LyricsModeler
{
    private int order;
    private TreeMap<WordGram, HashSet<String>> wordGramMap;
    private String[] songs;
    private int totalNumWords;
    private HashMap<String, ArrayList<Integer>> verseWordCountMap;
    private ArrayList<String> songStructures;
    private ResourceBundle filePathAndVariables;
    private ArrayList<ArrayList<String>> allSongsAndLyrics;
    
    public LyricsModeler(int order) {
        this.order = order;
        this.wordGramMap = new TreeMap<WordGram, HashSet<String>>();
        this.verseWordCountMap = new HashMap<String, ArrayList<Integer>>();
        this.filePathAndVariables = ResourceBundle.getBundle("FilePathAndVariables");
        this.allSongsAndLyrics = new ArrayList<ArrayList<String>>();
        this.songStructures = new ArrayList<String>();
    }
    
    public void setTraining(String[] songs) {
        this.songs = songs;
        int count = 0;
        String currentVerseType = "";
        for (String song : songs) {
            String[] words = song.split("\\s+");
            ArrayList<String> songLyrics = new ArrayList<String>();
            StringBuilder songStructureBuilder = new StringBuilder();
            for (String word : words) {
                if (word.startsWith("[") && word.endsWith("]") && word.length() >= 3) {
                    if (!currentVerseType.isEmpty()) {
                        if (!this.verseWordCountMap.containsKey(currentVerseType)) {
                            ArrayList<Integer> verseLengths = new ArrayList<Integer>();
                            verseLengths.add(count);
                            this.verseWordCountMap.put(currentVerseType, verseLengths);
                        }
                        else {
                            this.verseWordCountMap.get(currentVerseType).add(count);
                        }
                    }
                    count = 0;
                    currentVerseType = this.filePathAndVariables.getString(word.substring(1, word.length() - 1).toUpperCase());
                    Stream<String> stream = Arrays.stream(this.filePathAndVariables.getString("validVerseTypes").split(", "));
                    boolean containsIgnoreCase = stream.anyMatch(currentVerseType::equalsIgnoreCase);
                    if (!containsIgnoreCase) {
                        System.err.println("ERROR: invalid verse type: " + currentVerseType);
                        break;
                    }
                    songStructureBuilder.append(currentVerseType);
                }
                else {
                    ++count;
                    songLyrics.add(word);
                }
            }
            this.songStructures.add(songStructureBuilder.toString());
            this.allSongsAndLyrics.add(songLyrics);
        }
        for (ArrayList<String> currentSong : this.allSongsAndLyrics) {
            for (int j = 0; j < currentSong.size() - this.order; ++j) {
                ++this.totalNumWords;
                WordGram wordGram = new WordGram(currentSong.toArray(new String[0]), j, this.order);
                String word = currentSong.get(this.order + j);
                if (this.wordGramMap.containsKey(wordGram)) {
                    this.wordGramMap.get(wordGram).add(word);
                } else {
                    HashSet<String> nextValues = new HashSet<>();
                    nextValues.add(word);
                    this.wordGramMap.put(wordGram, nextValues);
                }
                if (j + this.order > currentSong.size()) {
                    this.wordGramMap.get(wordGram).add("");
                }
            }
        }
    }
    
    public String generateSong(int songStructureOrder) {
        SongStructureMarkov songStructureMarkov = new SongStructureMarkov(songStructureOrder);
        songStructureMarkov.setTraining(this.songStructures.toArray(new String[0]));
        String songStructure = songStructureMarkov.getRandomSongStructure();
        StringBuilder songBuilder = new StringBuilder();
        String chorus = "";
        String preChorus = "";
        String postChorus = "";
        for (int i = 0; i < songStructure.length(); ++i) {
            Random random = new Random();
            String verseType = String.valueOf(songStructure.charAt(i));
            songBuilder.append(this.filePathAndVariables.getString(verseType).toUpperCase());
            songBuilder.append(" ");
            int min = Collections.min(this.verseWordCountMap.get(verseType));
            int max = Collections.max(this.verseWordCountMap.get(verseType));
            int numWordsInVerse = random.nextInt(max - min) + min;
            String verse = this.createVerse(verseType, numWordsInVerse, chorus, preChorus, postChorus);
            songBuilder.append(verse);
            if (verseType.equalsIgnoreCase(this.filePathAndVariables.getString("CHORUS"))) {
                chorus = verse;
            }
            else if (verseType.equalsIgnoreCase(this.filePathAndVariables.getString("PRE-CHORUS"))) {
                preChorus = verse;
            }
            else if (verseType.equalsIgnoreCase(this.filePathAndVariables.getString("POST-CHORUS"))) {
                postChorus = verse;
            }
        }
        return songBuilder.toString();
    }
    
    private String createVerse(String verseType, int numWordsInVerse, String chorus, String preChorus, String postChorus) {
        if (verseType.equalsIgnoreCase(this.filePathAndVariables.getString("CHORUS"))) {
            if (chorus.isEmpty()) {
                chorus = this.getRandomWords(numWordsInVerse);
            }
            return chorus;
        }
        if (verseType.equalsIgnoreCase(this.filePathAndVariables.getString("PRE-CHORUS"))) {
            if (preChorus.isEmpty()) {
                preChorus = this.getRandomWords(numWordsInVerse);
            }
            return preChorus;
        }
        if (verseType.equalsIgnoreCase(this.filePathAndVariables.getString("POST-CHORUS"))) {
            if (postChorus.isEmpty()) {
                postChorus = this.getRandomWords(numWordsInVerse);
            }
            return postChorus;
        }
        return this.getRandomWords(numWordsInVerse);
    }
    
    public String getRandomWords(int numWords) {
        String[] allLyrics = new String[this.totalNumWords];
        int i = 0;
        for (String song : this.songs) {
            for (String word : song.split("\\s+")) {
                if (i >= this.totalNumWords) {
                    break;
                }
                if (!word.startsWith("[") && !word.endsWith("]")) {
                    allLyrics[i++] = word;
                }
            }
        }
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int index = random.nextInt(allLyrics.length - this.order);
        WordGram current = new WordGram(allLyrics, index, this.order);
        for (int j = 0; j < numWords - this.order; ++j) {
            HashSet<String> nextValues = this.wordGramMap.get(current);
            if (nextValues == null) {
                nextValues = new HashSet<>();
            }
            if (nextValues.size() == 0) {
                break;
            }
            index = random.nextInt(nextValues.size());
            Iterator<String> iterator = nextValues.iterator();
            for (int k = 0; k < index; ++k) {
                iterator.next();
            }
            String next = iterator.next();
            if (next.isEmpty()) {
                break;
            }
            sb.append(next);
            sb.append(" ");
            current = current.shiftAdd(next);
        }
        return sb.toString();
    }
}