package modelers;

public class WordGram implements Comparable<WordGram>
{
    private String[] words;
    
    WordGram(String[] source, int start, int size) {
        final String[] words = new String[size];
        for (int i = 0; i < size; ++i) {
            if (!source[start + i].startsWith("[") && !source[start + i].endsWith("]")) {
                words[i] = source[start + i];
            }
            else {
                words[i] = "";
            }
        }
        this.words = words;
    }
    
    public boolean equals(Object obj) {
        if (!(obj instanceof WordGram)) {
            return false;
        }
        WordGram wordGram = (WordGram)obj;
        if (wordGram.words.length != this.words.length) {
            return false;
        }
        for (int i = 0; i < this.words.length; ++i) {
            if (!this.words[i].equals(wordGram.words[i])) {
                return false;
            }
        }
        return true;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i = 0; i < this.words.length; ++i) {
            sb.append(this.words[i]);
            if (i < this.words.length - 1) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }
    
    public int compareTo(WordGram otherWordGram) {
        for (int i = 0; i < this.words.length && i < otherWordGram.words.length; ++i) {
            if (!this.words[i].equals(otherWordGram.words[i])) {
                return this.words[i].compareTo(otherWordGram.words[i]);
            }
        }
        return this.words.length - otherWordGram.words.length;
    }
    
    WordGram shiftAdd(String last) {
        String[] arr = new String[this.words.length];
        System.arraycopy(this.words, 1, arr, 0, this.words.length - 1);
        arr[this.words.length - 1] = last;
        return new WordGram(arr, 0, this.words.length);
    }
}