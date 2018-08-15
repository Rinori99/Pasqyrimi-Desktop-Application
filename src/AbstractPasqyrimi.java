import java.text.DecimalFormat;

public abstract class AbstractPasqyrimi {
	protected MainMemory mm;
	protected CacheMemory cm;
	protected AbstractAddress address;
	protected int blockLineSize;
	protected int countAll;
	protected int countHit;
	protected double hitRate;
	
	public AbstractPasqyrimi(int mmSize, int cmSize, int offsetBits){
		blockLineSize = (int)Math.pow(2, offsetBits);
		mm = new MainMemory(mmSize, blockLineSize);
		cm = new CacheMemory(cmSize, blockLineSize);
	}
	
	public abstract void load(String hexInstruction);
	
	public static int log2(int n){
		int count = 1;
        int x = 2;
        while(x!=n){
            x*=2;
            count++;
        }
        return count;
	}
	
	public static int binToDec(String s){
		int result = 0;
		int count = 0;
		for(int i = s.length() - 1; i >= 0; i--){
			result += Integer.parseInt(Character.toString(s.charAt(i)))*(int)Math.pow(2, count++);
		}
		return result;
	}
	
	public static int hexToDec(String s){
		int result = 0;
		int count = 0;
		for(int i = s.length() - 1; i >= 0; i--){
			String c = "";
			if(Character.toString(s.charAt(i)).toUpperCase().equals("A")){
				c = "10";
			}else if(Character.toString(s.charAt(i)).toUpperCase().equals("B")){
				c = "11";
			}else if(Character.toString(s.charAt(i)).toUpperCase().equals("C")){
				c = "12";
			}else if(Character.toString(s.charAt(i)).toUpperCase().equals("D")){
				c = "13";
			}else if(Character.toString(s.charAt(i)).toUpperCase().equals("E")){
				c = "14";
			}else if(Character.toString(s.charAt(i)).toUpperCase().equals("F")){
				c = "15";
			}else{
				c = Character.toString(s.charAt(i));
			}
			result += Integer.parseInt(c)*(int)Math.pow(16, count++);
		}
		return result;
	}
	
	public String getAddressTag() {
		return address.getTag();
	}
	
	public String getAddressOffset(){
		return address.getOffset();
	}
	
	public String getCountAll(){
		return Integer.toString(countAll);
	}
	
	public String getCountHit(){
		return Integer.toString(countHit);
	}
	
	public String getCountMiss(){
		return Integer.toString(countAll - countHit);
	}
	
	public String getHitRate(){
		DecimalFormat numberFormat = new DecimalFormat("#.0");
		return numberFormat.format(hitRate);
	}
	
	public String getMissRate(){
		DecimalFormat numberFormat = new DecimalFormat("#.0");
		if(countAll == 0){
			return "0";
		}
		return numberFormat.format(100-hitRate);
	}
	
	public Line getLine(int n){
		return cm.getLine(n);
	}
	
	public int getCacheSize(){
		return cm.getSize();
	}
	
	public String getBlockData(int n){
		return mm.getBlockData(n);
	}
	
	public void rifreskoAdresen(){
		address.rifreskoAdresen();
	}
	
	public void setAdresa(String s){
		address.setAddressState(s);
	}
	
	public int getBlockLineSize(){
		return blockLineSize;
	}
	
	public int getBlocksNumber(){
		return mm.getBlockNumber();
	}
	
	public String getWord(int i, int u){
		return mm.getWord(i, u);
	}
	
	public int getLinesNumber(){
		return cm.getLinesNumber();
	}
	
	public String getCacheString(int i, int u){
		return cm.getCacheString(i, u);
	}
	
	public void rifreskoStatistikat(){
		countHit = 0;
		countAll = 0;
		hitRate = 0;
	}
	
	public int getMmSize(){
		return mm.getSize();
	}
}
