
public class Line {
	
	private int validBit;
	private String tag;
	private String dataCopy;
	private int index;
	
	public Line(int index){
		this.index = index;
		validBit = 0;
		tag = "I ZBRAZËT";
		dataCopy = "E ZBRAZËT";
	}
	
	
	public int getValidBit(){
		return validBit;
	}
	
	public void setValidBit(int vBit){
		validBit = vBit;
	}
	
	public void changeValidBit(){
		if(validBit == 0){
			validBit = 1;
		}else{
			validBit = 0;
		}
	}
	
	public void setTag(String newTag){
		tag = newTag;
	}
	
	public String getTag(){
		return tag;
	}
	
	public void setData(String s){
		dataCopy = s;
	}
	
	public int getIndex(){
		return index;
	}
	
	public String getCacheString(int u){
		if(u == 0){
			return Integer.toString(this.getIndex());
		}else if(u == 1){
			return Integer.toString(this.getValidBit());
		}else if(u == 2){
			return this.getTag();
		}else{
			return dataCopy;
		}
	}
}