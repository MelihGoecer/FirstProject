package com.example.mytools;

public class Student {

   private String firstName;
   private String lastName;
   private String dateOfBirth;

   private byte nClass;
   private byte nSubjects;
   private byte age;

   private long id;

    Student(String pFirstName, String pLastName, String pDateOfBirth, byte pClass, byte pSubjects, byte pAge, long pId) {
        this.firstName = pFirstName;
        this.lastName = pLastName;
        this.dateOfBirth = pDateOfBirth;
        this.nClass = pClass;
        this.nSubjects = pSubjects;
        this.age = pAge;
        this.id = pId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public byte getnClass() {
        return nClass;
    }

    public void setnClass(byte nClass) {
        this.nClass = nClass;
    }

    public byte getnSubjects() {
        return nSubjects;
    }

    public void setnSubjects(byte nSubjects) {
        this.nSubjects = nSubjects;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }
}
