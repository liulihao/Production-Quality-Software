package edu.nyu.cs.pqs.test;

public class Citizen {
  private String name;
  private int age;
  private double height;
  private double weight;
  private String resident;

  public Citizen(String name, int age, double height, double weight,
      String resident) {
    this.name = name;
    this.age = age;
    this.height = height;
    this.weight = weight;
    this.resident = resident;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getResident() {
    return resident;
  }

  public void setResident(String resident) {
    this.name = resident;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Citizen)) {
      return false;
    }
    Citizen customer = (Citizen) o;
    return customer.age == this.age && customer.height == this.height
        && customer.weight == this.weight && customer.name.equals(this.name)
        && customer.resident.equals(this.resident);
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + name.hashCode();
    result = 31 * result + age;
    result = 31 * result + (int) weight;
    result = 31 * result + (int) height;
    result = 31 * result + resident.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Name:" + name + ",Age:" + age + ",Height:" + height
        + ",Weight:" + weight + ",Resident:" + resident;
  }
}
