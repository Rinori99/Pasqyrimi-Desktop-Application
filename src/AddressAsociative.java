
public class AddressAsociative extends AbstractAddress{

	public AddressAsociative(int gjatesia, int offset) {
		super(gjatesia, offset);
		tag = gjatesia - offset;
		addressState = new String[2];
		addressState[0] = "";
	    addressState[1] = "";
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
	    for(int i = tag; i < gjatesia; i++){
	    	addressState[1] += binar.charAt(i);
	    }
	}
	
	public void rifreskoAdresen(){
		addressState[0] = "";
	    addressState[1] = "";
	}
	
	public String getOffset(){
		return addressState[1];
	}
	
	public static void main(String[] args){
		AbstractAddress a = new AddressAsociative(6, 2);
//		a.setAddressState("16");
//		System.out.print(a.getTag() + " ");
//		System.out.print(a.getIndex() + " ");
//		System.out.print(a.getOffset());
		a.setAddressState("5");
		System.out.print(a.getTag() + " ");
		System.out.print(a.getOffset());
	}
}
