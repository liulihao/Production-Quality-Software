package edu.nyu.pqs.ps1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Contact includes all the fields for one contact
 */
public class Contact {
  private final String name;
  private final String postalAddress;
  private final String phoneNumber;
  private final String emailAddress;
  private final String note;

  /**
   * Creates new class for Contact
   * Attribute postalAddress, phoneNumber, emailAddress and note are default as
   * empty string
   */
  public static class contactBuilder {
    private final String name;
    private String postalAddress = "";
    private String phoneNumber = "";
    private String emailAddress = "";
    private String note = "";

    /**
     * Set the name of this contact
     * 
     * @param name
     *          name of this contact
     * @throws IllegalArgumentException
     *           if name is blank
     */
    public contactBuilder(String name) {
      if (name.trim().length() == 0) {
        throw new IllegalArgumentException("Name cannot be blank");
      }
      this.name = name;
    }

    /**
     * Set the postal address of this contact
     * 
     * @return reference to this contactBuilder
     * @param postal
     *          postal address of this contact
     */
    public contactBuilder postalAddress(String address) {
      this.postalAddress = address;
      return this;
    }

    /**
     * Check if phone number is valid and set the phone number of this contact
     * Phone number input is digits only, no () - or .
     * 
     * @param phone
     *          phone number of this contact
     * @return reference to this contactBuilder
     * @throws IllegalArgumentException
     *           if phone number is not digits
     */
    public contactBuilder phoneNumber(String phone) {
      if (!isValidNumber(phoneNumber)) {
        throw new IllegalArgumentException(
            "Phone number can only contain digits");
      }
      this.phoneNumber = phone;
      return this;
    }

    /**
     * Check if email address is valid and set the email address of this contact
     * 
     * @param email
     *          email address of this contact
     * @return reference to this contactBuilder
     */
    public contactBuilder emailAddress(String email) {
      if (email.trim().length() != 0 && !isValidEmailAddress(email)) {
        throw new IllegalArgumentException("Invalid Email Address");
      }
      this.emailAddress = email;
      return this;
    }

    /**
     * Set the note of this contact
     * 
     * @param note
     *          note of this contact
     * @return reference to this contactBuilder
     */
    public contactBuilder note(String note) {
      this.note = note;
      return this;
    }

    /**
     * @return reference to this Contact
     */
    public Contact build() {
      return new Contact(this);
    }

    /**
     * Check if the phone number contains things other than digits
     * 
     * @param number
     *          phone number that want to be checked
     * @return false if the numbers are not all digits,
     *         true if the numbers are all digits
     */
    private boolean isValidNumber(String number) {
      for (int i = 0; i < number.length(); i++) {
        if (!Character.isDigit(number.charAt(i))) {
          return false;
        }
      }
      return true;
    }

    /**
     * Check if email address is valid
     * examples are valid:
     * - test@gmail.com
     * - test@test.co.jp
     * - test.100@gmail.com
     * examples are invalid:
     * - test@.gmail.com
     * - test@.com
     * - test.@gmail.com
     * 
     * @param email
     *          email address that want to be checked
     * @return true if email address matches the rule,
     *         false if email address fail to match the rule
     */
    private boolean isValidEmailAddress(String email) {
      String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]"
          + "+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])"
          + "|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
      Pattern p = Pattern.compile(ePattern);
      Matcher m = p.matcher(email);
      return m.matches();
    }
  }

  /**
   * Constructor for class Contact
   * 
   * @param class
   *          contactBuilder for class contact
   */
  private Contact(contactBuilder builder) {
    name = builder.name;
    postalAddress = builder.postalAddress;
    phoneNumber = builder.phoneNumber;
    emailAddress = builder.emailAddress;
    note = builder.note;
  }

  /**
   * Get the name of this contact
   * 
   * @return name of this contact
   */
  public String getName() {
    return name;
  }

  /**
   * Get the postal address of this contact
   * 
   * @return postal address of this contact
   */
  public String getPostalAddress() {
    return postalAddress;
  }

  /**
   * Get the phone number of this contact
   * 
   * @return phone number of this contact
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Get the email address of this contact
   * 
   * @return email address of this contact
   */
  public String getEmailAddress() {
    return emailAddress;
  }

  /**
   * Get this note of this contact
   * 
   * @return note of this contact
   */
  public String getNote() {
    return note;
  }

  /**
   * Concatenate all the fields and put them as one line
   * 
   * @return contact as one line string
   */
  @Override
  public String toString() {
    return "Name:" + name + ",Postal Address:" + postalAddress
        + ",Phone Number:" + phoneNumber + ",Email:" + emailAddress + ",Note:"
        + note;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Contact)) {
      return false;
    }
    Contact contact = (Contact) obj;
    return contact.name.equals(name)
        && contact.postalAddress.equals(postalAddress)
        && contact.phoneNumber.equals(phoneNumber)
        && contact.emailAddress.equals(emailAddress)
        && contact.note.equals(note);
  }

  /**
   * Ensure that equal objects have the same hash code
   */
  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + name.hashCode();
    result = 31 * result + postalAddress.hashCode();
    result = 31 * result + phoneNumber.hashCode();
    result = 31 * result + emailAddress.hashCode();
    result = 31 * result + note.hashCode();
    return result;
  }

}
