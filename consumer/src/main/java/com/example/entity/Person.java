package com.example.entity;

public class Person {

    String personName;
    int age;

    public Person(String personName,int age){
        this.age = age;
        this.personName=personName;
    }
    public Person(){

    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
