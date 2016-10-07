package edu.nyu.pqs.test;

import edu.nyu.pqs.addressbook.AddressBook;
import edu.nyu.pqs.addressbook.AddressBook.ContactAttribute;
import edu.nyu.pqs.addressbook.Contact;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test class for AddressBook.
 * Code coverage : 98.0%
 * 
 * @author Li-Hao Liu <lhl302@nyu.edu>
 */

public class AddressBookTest {

  private AddressBook addressBook;

  @Before
  public void constructTestContact() throws FileNotFoundException, IOException {
    addressBook = new AddressBook();
    Contact testContact1 = new Contact.Builder().withName("Alice")
        .withPhoneNumber("123-456-7890")
        .withAddress("XXX-XX XX Road, City, 10001")
        .withEmail("test1@test.com").withNote("NYU CS").build();
    Contact testContact2 = new Contact.Builder().withName("Bob")
        .withPhoneNumber("234-567-8901")
        .withAddress("XXX-XX XX Road, City, 10002")
        .withEmail("test2@test.com").withNote("NYU IS").build();
    Contact testContact3 = new Contact.Builder().withName("Cathy")
        .withPhoneNumber("345-678-9012")
        .withAddress("XXX-XX XX Road, City, 10003")
        .withEmail("test3@test.com").withNote("NYU IM").build();
    Contact testContact4 = new Contact.Builder().withName("David")
        .withPhoneNumber("456-789-0123")
        .withAddress("XXX-XX XX Road, City, 10004")
        .withEmail("test4@test.com").withNote("NYU MATH").build();
    Contact testContact5 = new Contact.Builder().withName("Ellen")
        .withPhoneNumber("567-890-1234")
        .withAddress("XXX-XX XX Road, City, 10005")
        .withEmail("test5@test.com").withNote("NYU CS").build();
    assertTrue(addressBook.addContact(testContact1));
    assertTrue(addressBook.addContact(testContact2));
    assertTrue(addressBook.addContact(testContact3));
    assertTrue(addressBook.addContact(testContact4));
    assertTrue(addressBook.addContact(testContact5));
    addressBook.save("test.json");
  }

  @Test
  public void testAddContact() {
    Contact testContact = new Contact.Builder().withName("Test")
        .withPhoneNumber("999-999-9999")
        .withAddress("XXX-XX XX Road, City, 99999")
        .withEmail("test@test.com").withNote("NYU").build();
    assertTrue(addressBook.addContact(testContact));
  }

  // addContact has bug because it allows null contact to be added.
  @Test
  public void testAddNullContact() {
    assertFalse("Should not allow users to add null contact",
        addressBook.addContact(null));
  }

  @Test
  public void testRemoveContact() {
    List<Contact> contacts = addressBook.search(ContactAttribute.NAME, "David");
    for (Contact c : contacts) {
      assertTrue(addressBook.removeContact(c));
    }
  }

  @Test
  public void testSaveFile() throws FileNotFoundException, IOException {
    addressBook.save("test_save.json");
  }

  @Test
  public void testSaveEmptyFile() throws FileNotFoundException, IOException {
    AddressBook emptyAddressBook = new AddressBook();
    emptyAddressBook.save("test_save_empty.json");
  }

  @Test(expected = FileNotFoundException.class)
  public void testReadFileNotExist() throws FileNotFoundException, IOException {
    addressBook = new AddressBook("notfound.json");
  }

  @Test
  public void testRead() throws FileNotFoundException, IOException {
    AddressBook readAddressBook = new AddressBook("test.json");
    assertTrue(readAddressBook.toString().equals(addressBook.toString()));
  }

  @Test
  public void testSearchByName() {
    List<Contact> contacts = addressBook.search(ContactAttribute.NAME, "Alice");
    assertTrue("1 contact should be returned", contacts.size() == 1);
  }

  @Test
  public void testSearchByEmptyName() {
    List<Contact> contacts = addressBook.search(ContactAttribute.NAME, "");
    assertTrue("5 contacts should be returned", contacts.size() == 5);
  }

  @Test(expected = NullPointerException.class)
  public void testSearchByNullName() {
    addressBook.search(ContactAttribute.NAME, null);
  }

  @Test
  public void testSearchByNote() {
    List<Contact> contacts = addressBook.search(ContactAttribute.NOTE, "CS");
    assertTrue("2 contacts should be returned", contacts.size() == 2);
  }

  @Test
  public void testSearchByEmptyNote() {
    List<Contact> contacts = addressBook.search(ContactAttribute.NOTE, "");
    assertTrue("5 contacts should be returned", contacts.size() == 5);
  }

  @Test(expected = NullPointerException.class)
  public void testSearchbyNullNote() {
    addressBook.search(ContactAttribute.NOTE, null);
  }

  @Test
  public void testSearchByEmail() {
    List<Contact> contacts = addressBook.search(ContactAttribute.EMAIL,
        "test1@test.com");
    assertTrue("1 contact should be returned", contacts.size() == 1);
  }

  @Test
  public void testSearchByEmptyEmail() {
    List<Contact> contacts = addressBook.search(ContactAttribute.EMAIL, "");
    assertTrue("5 contacts should be returned", contacts.size() == 5);
  }

  @Test(expected = NullPointerException.class)
  public void testSearchByNullEmail() {
    addressBook.search(ContactAttribute.EMAIL, null);
  }

  @Test
  public void testSearchByPhoneNumber() {
    List<Contact> contacts = addressBook.search(ContactAttribute.PHONE,
        "1234567890");
    assertTrue("1 contact should be returned", contacts.size() == 1);
  }

  @Test
  public void testSearchByEmptyPhoneNumber() {
    List<Contact> contacts = addressBook.search(ContactAttribute.PHONE, "");
    assertTrue("5 contacts should be returned", contacts.size() == 5);
  }

  @Test(expected = NullPointerException.class)
  public void testSearchByNullPhoneNumber() {
    addressBook.search(ContactAttribute.PHONE, null);
  }

  @Test
  public void testSearchByPostalAddress() {
    List<Contact> contacts = addressBook.search(ContactAttribute.ADDRESS,
        "XXX-XX XX Road, City, 10003");
    assertTrue("1 contact should be returned", contacts.size() == 1);
  }

  @Test
  public void testSearchByEmptyPostalAddress() {
    List<Contact> contacts = addressBook.search(ContactAttribute.ADDRESS, "");
    assertTrue("5 contacts should be returned", contacts.size() == 5);
  }

  @Test(expected = NullPointerException.class)
  public void testSearchByNullPostalAddress() {
    addressBook.search(ContactAttribute.ADDRESS, null);
  }

  @After
  public void deleteFiles() {
    File test_save_empty = new File("test_save_empty.json");
    File test_save = new File("test_save.json");
    File test = new File("test.json");
    if (test_save_empty.exists()) {
      test_save_empty.delete();
    }
    if (test_save.exists()) {
      test_save.delete();
    }
    if (test.exists()) {
      test.delete();
    }
  }

}
