package com.makhnovetc.ifmo.soap;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Класс, содержащий метод для выборки данных из базы данных, а также
 упаковки этих данных в объекты класса Person.
 */

public class PostgreSQLDAO {
    private String query = "select * from persons";
/*    public List<Person> getPersons() {
        List<Person> persons = getPersons(query);
        return persons;
    }*/

    public List<Person> getPersons(String query) {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id=rs.getInt("id");
                String name = rs.getString("name");
                String middlename = rs.getString("middle_name");
                String surname = rs.getString("surname");
                Date dob = rs.getDate("dob");
                String sex=rs.getString("sex");
                Person person = new Person(id,name,middlename, surname, dob, sex);
                persons.add(person);
                System.out.println("get person");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
    }

    public List<Person> findPeople(String id, String name, String middlename, String surname, String dob, String sex){
        ArrayList<String> query_where = new ArrayList<String>();
        if (!id.isEmpty()) query_where.add("id='"+id+"'");
        if (!name.isEmpty()) query_where.add("name='"+name+"'");
        if (!middlename.isEmpty()) query_where.add("middle_name='"+middlename+"'");
        if (!surname.isEmpty()) query_where.add("surname='"+surname+"'");
        if (!dob.isEmpty()) query_where.add("dob='"+dob+"'");
        if (!sex.isEmpty()) query_where.add("sex='"+sex+"'");
        String query = query_where.size() >0 ? this.query +
                " where " + String.join(" and ",query_where)
                : this.query;

        List<Person> persons = getPersons(query);
        return persons;
    }

    public int insertPerson(String name, String middlename, String surname, String dob, String sex){
        /*this.query = "INSERT into persons(NAME,MIDDLE_NAME,SURNAME,DOB,SEX) VALUES " +
                "('"+name+"','"+middlename+"','"+surname+"','"+dob+"','"+sex+"')";
        List<Person> persons = new ArrayList<>();*/
        int id=-5;
        try (Connection connection = ConnectionUtil.getConnection()){
            PreparedStatement stmt = connection.prepareStatement("INSERT into persons(NAME,MIDDLE_NAME,SURNAME,DOB,SEX) VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,name);
            stmt.setString(2,middlename);
            stmt.setString(3,surname);
/*            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date date = format.parse(dob);*/
            LocalDate localDate= LocalDate.parse(dob, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            stmt.setDate(4, java.sql.Date.valueOf(String.valueOf(localDate)));
            stmt.setString(5,sex);
            //statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            //stmt.executeUpdate();
            stmt.executeUpdate();
            ResultSet rs=stmt.getGeneratedKeys();
            //List<Person> persons = getPersons("Select * from person where");
            while (rs.next()) {
                id = rs.getInt(1);
                System.out.println("insert row with id = "+id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public String updatePerson(String id, String name, String middlename, String surname, String dob, String sex) {
        String status = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("UPDATE persons SET name=?, middle_name=?, surname = ?, dob = ?, sex = ? WHERE id=?");
            stmt.setString(1,name);
            stmt.setString(2,middlename);
            stmt.setString(3,surname);
            LocalDate localDate= LocalDate.parse(dob, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            stmt.setDate(4, java.sql.Date.valueOf(String.valueOf(localDate)));
            stmt.setString(5,sex);
            stmt.setInt(6, Integer.valueOf(id));
            int count_row=stmt.executeUpdate();
            status = count_row>0? "update " + count_row + " row": "no updated row";
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    public String deletPerson(String id){
        String status = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement("DELETE from persons where id=?");
            stmt.setInt(1,Integer.valueOf(id));
            int count_row=stmt.executeUpdate();
            status = count_row>0? "delete " + count_row + " row": "no deleted row";
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public boolean checkPerson(String id){
        boolean check=false;
        Person person = new Person();
        String query = "Select count(*) from persons as count where id = " + id;
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            int count = rs.getInt("count");
            if (count == 1) {
                check = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
}
