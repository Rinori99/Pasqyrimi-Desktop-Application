
public class CacheMemory {
	private int size;
	private Line[] lines;
	
	public CacheMemory(int size, int lineSize){
		this.size = size;
		lines = new Line[size/lineSize];
		for(int i = 0; i < lines.length; i++){
			lines[i] = new Line(i);
		}
	}
	
	public int getSize(){
		return size;
	}
	
	public Line getLine(int index){
		return lines[index];
	}
	
	public void setTag(int index, String tag){
		lines[index].setTag(tag);
	}
	
//	public void setData(int index, String[] data){
//		lines[index].setData(data);
//	}
	
	public void setData(int index, int validBit, String tag, String data){
		lines[index].setValidBit(validBit);
		lines[index].setTag(tag);
		lines[index].setData(data);
	}
	
	public int getLinesNumber(){
		return lines.length;
	}
	
	public String getCacheString(int i, int u){
		return lines[i].getCacheString(u);
	}
}
