
public class PasqyrimiAsociativ extends AbstractPasqyrimi{
	private int currentSize;
	private int currentIndex;
	private boolean lastFound;
	private int lastFoundIndex;
	private boolean used;
	
	public PasqyrimiAsociativ(int mmSize, int cmSize, int offsetBits){
		super(mmSize, cmSize, offsetBits);
		address = new AddressAsociative(log2(mmSize), offsetBits);
	}
	
	public void load(String hexInstruction){
		int mmAddress = super.hexToDec(hexInstruction);
		Line line = null;
		
		for(int i = 0; i < currentSize; i++){
			line = cm.getLine(i);
			if(line.getValidBit() != 0 && line.getTag().equals(address.getTag())){
				hitRate = 100*(double)++countHit/++countAll;
				cm.setData(i, 1, address.getTag(), mm.getBlockData(mmAddress));
				lastFound = true;
				lastFoundIndex = i;
				return;
			}
		}
		
		if(used){
			currentIndex++;
		}
		
		if(currentIndex == getLinesNumber()){
			currentIndex = 0;
		}
		
		lastFound = false;
		hitRate = 100*(double)countHit/++countAll;
		cm.setData(currentIndex, 1, address.getTag(), mm.getBlockData(mmAddress));

		if(currentSize < getLinesNumber()){
			currentSize++;
		}
		used=true;
	}
	
	public void store(){
		
	}
	
	public int getLastFoundIndex(){
		return lastFoundIndex;
	}
	
	public boolean getLastFound(){
		return lastFound;
	}
	
	public int getCurrentSize(){
		return currentSize;
	}
	
	public int getCurrentIndex(){
		return currentIndex;
	}
}