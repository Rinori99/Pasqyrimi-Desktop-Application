public class PasqyrimiDirekt extends AbstractPasqyrimi {
	
	public PasqyrimiDirekt(int mmSize, int cmSize, int offsetBits){
		super(mmSize, cmSize, offsetBits);
		address = new AddressDirekt(log2(mmSize), log2(cm.getSize()/blockLineSize), offsetBits);
	}
	
	public void load(String hexInstruction){
		int checkIndex = super.binToDec(((AddressDirekt)address).getIndex());
		int mmAddress = super.hexToDec(hexInstruction);
		Line line = cm.getLine(checkIndex);
		if(line.getValidBit() == 0){
			hitRate = 100*(double)countHit/++countAll;
			cm.setData(checkIndex, 1, address.getTag(), mm.getBlockData(mmAddress));
		}else{
			if(line.getTag().equals(address.getTag())){
				hitRate = 100*(double)++countHit/++countAll;
			}else{
				hitRate = 100*(double)countHit/++countAll;
				cm.setData(checkIndex, 1, address.getTag(), mm.getBlockData(mmAddress));
			}
		}
	}
	
	public void store(){
		
	}

	public String getAddressIndex() {
		return ((AddressDirekt)address).getIndex();
	}
}
