/**
 * Lab 3: Inheritance, Interfaces, Hash and Big Number <br />
 * The {@code Person} class
 * @author YongQuan Zhang 1515873
 */
public class Person {
    private String name;
	// Constructor, the base class
    public Person(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

	/**
     * Override "equals" and "==" for Employee class
	 * Override hashcode is required
     */
    @Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof Person)) {
			return false;
		}
		Person person = (Person) obj;
		return this.getName().equals(person.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getName());
	}
	
}
