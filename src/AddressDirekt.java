
public class AddressDirekt extends AbstractAddress{
	private int index;
	
	public AddressDirekt(int gjatesia, int index, int offset){
		super(gjatesia, offset);
		this.index = index;
		tag = gjatesia - (index + offset);
		addressState = new String[3];
		addressState[0] = "";
	    addressState[1] = "";
	    addressState[2] = "";
	}
	
	public void setAddressState(String hex){
		String binar = Integer.toBinaryString(Integer.parseInt(hex, 16));
		int gjatesiaBinar = binar.length();
		int zeros = gjatesia - gjatesiaBinar;
	    for(int i = 0; i < zeros; i++){
	    	binar = "0" + binar;
	    }
	    for(int i = 0; i < tag; i++){
	    	addressState[0] += binar.charAt(i);
	    }
	    for(int i = tag; i < tag+index; i++){
	    	addressState[1] += binar.charAt(i);
	    }
	    
	    for(int i = tag+index; i < gjatesia; i++){
	    	addressState[2] += binar.charAt(i);
	    }
	}
	
	public void rifreskoAdresen(){
		addressState[0] = "";
	    addressState[1] = "";
	    addressState[2] = "";
	}
	
	public String getIndex(){
		return addressState[1];
	}
	
	public String getOffset(){
		return addressState[2];
	}
}
