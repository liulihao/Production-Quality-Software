package edu.nyu.psq.test;

import java.util.List;

import edu.nyu.pqs.ps1.AddressBook;
import edu.nyu.pqs.ps1.Contact;

public class Test {

  private static void sop(Object x) {
    System.out.println(x);
  }

  public static void main(String[] args) {
    AddressBook book1 = new AddressBook();
    sop("Save Empty File......");
    book1.save("file/save1.txt");

    sop("Read Empty File......");
    book1.read("");

    sop("Add......");
    Contact c1 = new Contact.contactBuilder("Lihao Liu")
        .postalAddress("109-33 71st. Road, Apt. 3A, Forest Hills NY 11375 United States")
        .phoneNumber("9172440233").emailAddress("lhl302@nyu.edu")
        .note("NYU Graduate Student, from Taiwan")
        .build();
    book1.add(c1);

    String searchStr = "9172440233";
    sop("Search for \"" + searchStr + "\"......");
    List<Contact> cs = book1.search(searchStr);
    for (Contact c : cs) {
      sop(c);
    }

    sop("Add......");
    Contact c2 = new Contact.contactBuilder("Casper Cheng").postalAddress("NJ United States")
        .phoneNumber("6264355624").emailAddress("me@company.co.uk")
        .note("UCLA Graduate Student, from Taiwan")
        .build();
    book1.add(c2);

    Contact c3 = new Contact.contactBuilder("Lady Gaga").phoneNumber("1234567890").build();
    book1.add(c3);

    Contact c4 = new Contact.contactBuilder("Derek Jeter")
        .postalAddress("Yankees Stadium")
        .phoneNumber("9172440233").emailAddress("").note("New York Student, from Taiwan")
        .build();
    book1.add(c4);

    searchStr = "graduate student";
    sop("Search for \"" + searchStr + "\"......");
    cs.clear();
    cs = book1.search(searchStr);
    for (Contact c : cs) {
      sop(c);
    }

    sop("Save File......");
    book1.save("file/save2.txt");

    sop("Delete for \"" + searchStr + "\"......");
    for (Contact c : cs) {
      book1.remove(c);
    }

    sop("Save File......");
    book1.save("file/save3.txt");

    sop("Read File......");
    AddressBook book2 = new AddressBook("file/save3.txt");

    sop("Delete One Contact......");
    Contact c5 = new Contact.contactBuilder("Lady Gaga")
        .phoneNumber("12234567890").build();
    book2.remove(c5);

    sop("Save File......");
    book2.save("file/save4.txt");
  }
}
