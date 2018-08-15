
public class MainMemory {
	
	private Block[] blocks;
	private int size;
	
	public MainMemory(int size, int blockSize){
		this.size = size;
		blocks = new Block[size/blockSize];
		for(int i = 0; i < blocks.length; i++){
			blocks[i] = new Block(blockSize, i);
		}
	}
	
	public String printBlockString(int n){
		String s = "";
		String[] arr = blocks[n].printWords();
		for(int i = 0; i < arr.length; i++){
			s += (i == 0 ? "" : " ") + arr[i];
		}
		return s;
	}
	
	public String[] printBlockArray(int n){
		return blocks[n].printWords();
	}
	
	public String getBlockData(int n){
		return "Blloku " + n + ", Words: 0 - 3";
	}
	
	public Block[] printBlocks(){
		return blocks;
	}
	
	public String printBlocksString(){
		for(int i = 0; i < blocks.length; i++){
			
		}
		return null;
	}
	
	public void initializeMainMemory(){
		
	}
	
	public int getSize(){
		return size;
	}
	
	public int getBlockNumber(){
		return blocks.length;
	}
	
	public String[] getData(int index){
		return blocks[index].getData();
	}
	
	public String getWord(int i, int u){
		return blocks[i].getWord(u);
	}
}
