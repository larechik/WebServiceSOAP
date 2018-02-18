package com.makhnovetc.ifmo.soap.lab1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Класс, содержащий метод для выборки данных из базы данных, а также
 упаковки этих данных в объекты класса Person.
 */

public class PostgreSQLDAO {
    public List<Person> getPersons() {
        List<Person> persons = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from persons");
            while (rs.next()) {
                int id=rs.getInt("id");
                String name = rs.getString("name");
                String middlename = rs.getString("middle_name");
                String surname = rs.getString("surname");
                Date dob = rs.getDate("dbo");
                String sex=rs.getString("sex");
                Person person = new Person(id,name,middlename, surname, dob, sex);
                persons.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return persons;
    }
}
