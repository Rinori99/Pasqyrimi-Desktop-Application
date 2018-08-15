
public abstract class AbstractAddress {
	protected String[] addressState;
	protected int gjatesia;
	protected int tag;
	protected int offset;
	
	public AbstractAddress(int gjatesia, int offset){
		this.gjatesia = gjatesia;
		this.offset = offset;
	}
	
	public String getTag(){
		return addressState[0];
	}
	
	public abstract String getOffset();
	
	public abstract void setAddressState(String hex);
	
	public abstract void rifreskoAdresen();
}
