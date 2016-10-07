package edu.nyu.cs.pqs.backup;


public class Customer {
	private String name;
	private String SSN;
	private int age;
	private double height;
	private double weight;

	public Customer(String name, String SSN, int age, double height, double weight) {
		this.name = name;
		this.SSN = SSN;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Customer)) {
			return false;
		}
		Customer customer = (Customer) o;
		return customer.age == this.age && customer.height == this.height
				&& customer.weight == this.weight && customer.name.equals(this.name)
				&& customer.SSN.equals(this.SSN);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + name.hashCode();
		result = 31 * result + SSN.hashCode();
		result = 31 * result + age;
		result = 31 * result + (int) weight;
		result = 31 * result + (int) height;
		return result;
	}

	@Override
	public String toString() {
		return "Name:" + name + ",SSN:" + SSN + ",Age:" + age + ",Height:" + height
				+ ",Weight:" + weight;
	}
}
