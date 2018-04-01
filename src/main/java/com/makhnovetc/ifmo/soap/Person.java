package com.makhnovetc.ifmo.soap;

import java.util.Date;

/** Объекты этого класса возвращает веб-сервис. Обратите внимание на
 соответствие класса JavaBeans-стандарту: наличие конструктора без параметров,
 а также get/set-методы для private-полей.
 */
public class Person {
    private int id;
    private String name;
    private String middlename;
    private String surname;
    private Date dob;
    private String sex;
    public Person() {
    }
    public Person(int id, String name,String middlename, String surname, Date dob, String sex) {
        this.id = id;
        this.name = name;
        this.middlename = middlename;
        this.surname = surname;
        this.dob = dob;
        this.sex = sex;
    }
    public int getId(){
        return id;
    }
    public String getName() {
        return name;
    }
    public String getMiddlename() {
        return middlename;
    }
    public String getSurname() {
        return surname;
    }
    public Date getDob() {
        return dob;
    }
    public String getSex() {
        return sex;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
//    @Override
//    public String toString() {
//        return "Person{" + "name=" + name + ", middle name=" + middlename +", surname=" + surname + ", date of birthday=" + dob + ", sex=" + sex +'}';
//    }
}