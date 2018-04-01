package com.makhnovetc.ifmo.soap;

import com.makhnovetc.ifmo.soap.Exception.InvalidDateFormatException;
import com.makhnovetc.ifmo.soap.Exception.InvalidDateFormatExceptionBean;
import com.makhnovetc.ifmo.soap.Exception.NullFieldException;
import com.makhnovetc.ifmo.soap.Exception.NullFieldExceptionBean;
import com.makhnovetc.ifmo.soap.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName = "PersonService")
public class PersonWebService {

    @WebMethod(operationName = "insertPerson")
    public int insertPerson(String name, String middlename, String surname, String dob, String sex) throws NullFieldException {
        checkField(name);
        checkField(middlename);
        checkField(surname);
        checkField(dob);
        checkField(sex);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        int id = dao.insertPerson(name, middlename, surname, dob, sex);
        return id;
    }

    @WebMethod(operationName = "updatePerson")
    public String updatePerson(String id, String name, String middlename, String surname, String dob, String sex) throws NullFieldException {
        checkField(id);
        checkField(name);
        checkField(middlename);
        checkField(surname);
        checkField(dob);
        checkField(sex);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        String status = dao.updatePerson(id, name, middlename, surname, dob, sex);
        return status;
    }

    @WebMethod(operationName = "deletePerson")
    public String deletePerson(String id) throws NullFieldException {
        checkField(id);
        PostgreSQLDAO dao = new PostgreSQLDAO();
        String status = dao.deletPerson(id);
        return status;
    }

    @WebMethod(operationName = "getPersonsQuery")
    public List<Person> getPersons(String query) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Person> persons = dao.getPersons(query);
        return persons;
    }

    @WebMethod(operationName = "findPeople")
    public List<Person> findPeople(String id, String name, String middlename, String surname, String dob, String sex) throws NullFieldException, InvalidDateFormatException {
        checkField(id);
        checkField(name);
        checkField(middlename);
        checkField(surname);
        checkField(dob);
        checkField(sex);
        if (checkDate(dob)){
            InvalidDateFormatExceptionBean fault=new InvalidDateFormatExceptionBean();
            throw new InvalidDateFormatException("Error entering dob: wrong format. The correct format is 'YYYY-MM-DD",fault);
        }
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Person> persons = dao.findPeople(id, name, middlename, surname, dob, sex);
        return persons;
    }

    private void checkField(String field) throws NullFieldException {
        if (field == null || field.isEmpty()) {
            NullFieldExceptionBean fault = new NullFieldExceptionBean();
            throw new NullFieldException("Error entering " + field + " : " + field + " is null or empty", fault);
        }
    }

    public boolean checkDate(String dateToValidate) {
        String dateFormat = "yyyy-MM-dd";
        if (dateToValidate == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {

            Date date = sdf.parse(dateToValidate);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}