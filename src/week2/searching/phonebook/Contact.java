package week2.searching.phonebook;

public class Contact implements Comparable<Contact> {

	public String name;
	public int phone;
	
	public Contact(String name, int phone){
		this.name = name;
		this.phone = phone;
	}

	@Override
	public int compareTo(Contact o) {
		if (this.phone == o.phone) {
			return 0;
		}

		return this.phone > o.phone ? 1 : -1;
	}

}
