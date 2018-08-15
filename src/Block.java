import java.util.Arrays;

public class Block {
	private String[] words;
	
	
	public Block(int blockSize, int rreshti){
		words = new String[blockSize];

		for(int i = 0; i < words.length; i++){
			words[i] = "Blloku: " + rreshti + ", Word: " + i;
		}
	}
	
	public String[] printWords(){
		return words;
	}
	
	public String printWord(int n){
		return words[n];
	}
	
	@Override
	public String toString(){
		return Arrays.toString(words);
	}
	
	public String[] getData(){
		return words;
	}
	
	public String getWord(int u){
		return words[u];
	}
}
