package edu.nyu.pqs.test;

import edu.nyu.pqs.addressbook.Contact;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit test class for Contact.
 * Code coverage : 97.2%
 * 
 * @author Li-Hao Liu <lhl302@nyu.edu>
 */

public class ContactTest {

  private Contact testContact;

  @Test
  public void testCreateContact() {
    testContact = new Contact.Builder().withName("Test")
        .withPhoneNumber("999-999-9999")
        .withAddress("XXX-XX XX Road, City, 99999")
        .withEmail("test@test.com").withNote("NYU").build();
  }

  // Builder has bug because it should have the same result as
  // testCreateContactWithNothing
  @Test(expected = IllegalArgumentException.class)
  public void testCreateContactWithEmptyValue() {
    testContact = new Contact.Builder().withName("")
        .withPhoneNumber("")
        .withAddress("")
        .withEmail("").withNote("").build();
  }

  // Builder has bug because it should have the same result as
  // testCreateContactWithNothing
  @Test(expected = IllegalArgumentException.class)
  public void testCreateContactWithNullValue() {
    testContact = new Contact.Builder().withName(null)
        .withPhoneNumber(null)
        .withAddress(null)
        .withEmail(null).withNote(null).build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateContactWithMultipleName() {
    testContact = new Contact.Builder().withName("Jon").withName("Mary")
        .withPhoneNumber("999-999-9999")
        .withAddress("XXX-XX XX Road, City, 99999")
        .withEmail("test@test.com").withNote("NYU").build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateContactWithMultiplePhoneNumber() {
    testContact = new Contact.Builder().withName("Jon")
        .withPhoneNumber("999-999-9999").withPhoneNumber("888-888-8888")
        .withAddress("XXX-XX XX Road, City, 99999")
        .withEmail("test@test.com").withNote("NYU").build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateContactWithMultipleAddress() {
    testContact = new Contact.Builder().withName("Jon")
        .withPhoneNumber("999-999-9999")
        .withAddress("XXX-XX XX Road, City, 99999")
        .withAddress("XXX-XX XX Ave, County, 88888")
        .withEmail("test@test.com").withNote("NYU").build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateContactWithMultipleEmail() {
    testContact = new Contact.Builder().withName("Jon")
        .withPhoneNumber("999-999-9999")
        .withAddress("XXX-XX XX Road, City, 99999")
        .withEmail("test@test.com").withEmail("test@nyu.edu").withNote("NYU")
        .build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateContactWithMultipleNote() {
    testContact = new Contact.Builder().withName("Jon")
        .withPhoneNumber("999-999-9999")
        .withAddress("XXX-XX XX Road, City, 99999")
        .withEmail("test@test.com").withNote("NYU").withNote("Master").build();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateContactWithNothing() {
    testContact = new Contact.Builder().build();
  }

  // Builder has bug because it allows phone that is not digit to be created.
  @Test(expected = IllegalArgumentException.class)
  public void testCreateContactWithWrongPhoneNumber() {
    testContact = new Contact.Builder().withName("Jon")
        .withPhoneNumber("ABCDEFGHIJ")
        .withAddress("XXX-XX XX Road, City, 99999")
        .withEmail("test@test.com").withNote("NYU").build();
  }

  // Builder has bug because it allows non email format to be created as email.
  @Test(expected = IllegalArgumentException.class)
  public void testCreateContactWithWrongEmail() {
    testContact = new Contact.Builder().withName("Jon")
        .withPhoneNumber("999-999-9999")
        .withAddress("XXX-XX XX Road, City, 99999")
        .withEmail("test").withNote("NYU").build();
  }

  @Test
  public void testGetName() {
    String testName = "Test";
    testContact = new Contact.Builder().withName(testName)
        .withPhoneNumber("999-999-9999")
        .withAddress("XXX-XX XX Road, City, 99999")
        .withEmail("test@test.com").withNote("NYU").build();
    assertEquals(testName, testContact.getName());
  }

  @Test
  public void testGetEmailAddress() {
    String testEmail = "test@test.com";
    testContact = new Contact.Builder().withName("Test")
        .withPhoneNumber("999-999-9999")
        .withAddress("XXX-XX XX Road, City, 99999")
        .withEmail(testEmail).withNote("NYU").build();
    assertEquals(testEmail, testContact.getEmailAddress());
  }

  @Test
  public void testGetPhoneNumber() {
    String testPhoneNumber = "999-999-9999";
    testContact = new Contact.Builder().withName("Test")
        .withPhoneNumber(testPhoneNumber)
        .withAddress("XXX-XX XX Road, City, 99999")
        .withEmail("test@test.com").withNote("NYU").build();
    assertEquals(testPhoneNumber, testContact.getPhoneNumber());
  }

  @Test
  public void testGetAddress() {
    String testAddress = "XXX-XX XX Road, City, 99999";
    testContact = new Contact.Builder().withName("Test")
        .withPhoneNumber("999-999-9999")
        .withAddress(testAddress)
        .withEmail("test@test.com").withNote("NYU").build();
    assertEquals(testAddress, testContact.getAddress());
  }

  @Test
  public void testGetNote() {
    String testNote = "NYU";
    testContact = new Contact.Builder().withName("Test")
        .withPhoneNumber("999-999-9999")
        .withAddress("XXX-XX XX Road, City, 99999")
        .withEmail("test@test.com").withNote(testNote).build();
    assertEquals(testNote, testContact.getNote());
  }

}
