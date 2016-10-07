package edu.nyu.pqs.ps1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Use this address book library, users can do the following: 
 * - Create an empty address book. 
 * - Add an entry which consists of name, postal address, phone number, email address and note. 
 * - Remove an entry. 
 * - Search for an entry by any of the contact properties. 
 * - Save the address book to a file. 
 * - Read the address book from a file. 
 * 
 * @author Li-Hao Liu <lhl302@nyu.edu>
 */

/**
 * Class AddressBook allows users to save multiple contacts in it
 */
public class AddressBook2 {
  private List<Contact> contacts;

  /**
   * Creates an empty address book.
   */
  public AddressBook2() {
    contacts = new ArrayList<Contact>();
  }

  /**
   * Creates an address book by reading a text file.
   * 
   * @param filePath
   *          name of the text file
   * @throws IOException
   *           when reading file is unsuccessful
   */
  // should check filePath if it's blank
  public AddressBook2(String filePath) throws IOException {
    contacts = new ArrayList<Contact>();
    read(filePath);
  }

  /**
   * Reads text file and call parseLine to analyze the data.
   * 
   * @param filePath
   *          name of the text file
   * @throws IOException
   *           when reading file is unsuccessful
   */

  // read broken/inconsistent when fields contain a newline character or comma
  public void read(String filePath) throws IOException {
    contacts.clear();
    Scanner scanner = null;
    Contact contact;
    scanner = new Scanner(new FileReader(filePath));
    while (scanner.hasNextLine()) {
      contact = parseLine(scanner.nextLine());
      add(contact);
    }
    scanner.close();
  }

  /**
   * Analyzes the line pass by read by using scanner to set all the field then
   * call builder to create a new contact and return this contact.
   * 
   * @param line
   *          string line from text file
   * @return Contact information
   */
  private Contact parseLine(String line) {
    Scanner scanner = new Scanner(line);
    scanner.useDelimiter(",");
    String scanName = "";
    String scanAddress = "";
    String scanPhone = "";
    String scanEmail = "";
    String scanNote = "";
    while (scanner.hasNext()) {
      String str = scanner.next();
      int startPos = str.indexOf(":") + 1;
      int endPos = str.length();
      String content = (startPos <= endPos) ? str.substring(startPos, endPos)
          : "";
      if (str.contains("Name:")) {
        scanName = content;
      } else if (str.contains("Postal Address:")) {
        scanAddress = content;
      } else if (str.contains("Phone Number:")) {
        scanPhone = content;
      } else if (str.contains("Email:")) {
        scanEmail = content;
      } else if (str.contains("Note:")) {
        scanNote = content;
      }
    }
    Contact contact = new Contact.contactBuilder(scanName)
        .postalAddress(scanAddress)
        .phoneNumber(scanPhone).emailAddress(scanEmail).note(scanNote).build();
    scanner.close();
    return contact;
  }

  /**
   * Saves address book into a text file If the filename does not exist, create a
   * new file for saving.
   * 
   * @param path
   *          name of the text file
   * @throws IOException
   *           when saving is unsuccessful
   */
  public void save(String path) throws IOException {
    BufferedWriter bw;
    File file = new File(path);
    if (!file.exists()) {
      file.createNewFile();
    }
    bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
    for (Contact contact : contacts) {
      bw.write(contact.toString());
      bw.newLine();
    }
    bw.close();
  }

  /**
   * Searches contact in address book by using keyword Any fields of a contact
   * that contains the keyword, will be added to results and return the results
   * in the end.
   * 
   * @param keyword
   *          string that users want to search in address book
   * @return all the contacts that have information contains keyword
   */

  // return type should be an interface, not a concrete class
  public ArrayList<Contact> search(String keyword) {
    ArrayList<Contact> results = new ArrayList<Contact>();
    for (Contact contact : contacts) {
      if (contact.getName().toLowerCase().contains(keyword.toLowerCase())
          || contact.getPostalAddress().toLowerCase()
              .contains(keyword.toLowerCase())
          || contact.getPhoneNumber().toLowerCase()
              .contains(keyword.toLowerCase())
          || contact.getEmailAddress().toLowerCase()
              .contains(keyword.toLowerCase())
          || contact.getNote().toLowerCase().contains(keyword.toLowerCase())) {
        results.add(contact);
      }
    }
    return results;
  }

  /**
   * Adds contact to address book.
   * 
   * @param contact
   *          contact to be added
   * @return true if insert was successful, else false.
   */
  public boolean add(Contact contact) {
    return contacts.add(contact);
  }

  /**
   * Removes contact from address book.
   * 
   * @param contact
   *          contact to be removed
   * @return true if remove was successful, else false.
   */
  public boolean remove(Contact contact) {
    return contacts.remove(contact);
  }

  @Override
  public String toString() {
    // ???
    return null;
  }

}
