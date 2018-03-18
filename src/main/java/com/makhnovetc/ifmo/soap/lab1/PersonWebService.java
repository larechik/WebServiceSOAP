package com.makhnovetc.ifmo.soap.lab1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Класс-реализация веб-сервиса. Данный сервис предоставляет одну операцию –
 * getPersons. Обратите внимание на аннотации @WebService и @WebMethod.
 *
 * @WebService используется для того, чтобы маркировать класс, который должен
 * предоставлять функциональность через веб-сервис. @WebMethod – маркер метода,
 * который будет являться операцией веб-сервиса.
 * Заметьте, что метод getPersons() возвращает List<Person>, а не какой-либо
 * специальный объект. То есть, единственное, что нужно указать разработчику – это
 * аннотация над методом, а об остальном позаботится имплементация JAX-WS.
 * <p>
 * В данной работе в веб-сервис, разработанный в первой работе, необходимо
 * добавить методы для создания, изменения и удаления записей из таблицы.
 * Метод создания должен принимать значения полей новой записи, метод
 * изменения – идентификатор изменяемой записи, а также новые значения полей, а
 * метод удаления – только идентификатор удаляемой записи.
 * Метод создания должен возвращать идентификатор новой записи, а методы
 * обновления или удаления – статус операции.
 */

@WebService(serviceName = "PersonService")
public class PersonWebService {
    private int connection = 0;

    public synchronized void inc() {
        connection++;
    }

    public synchronized void dec() {
        connection--;
    }

    private void checkConnection() throws ThrottlingException {
        if (connection > 1) {
            ThrottlingBean fault = ThrottlingBean.defaultInstance();
            throw new ThrottlingException("connection > 1", fault);
        }
    }

    @WebMethod(operationName = "insertPerson")
    public int insertPerson(String name, String middlename, String surname, String dob, String sex) throws ThrottlingException {
        int id=0;
        try {
            inc();
            checkConnection();
            PostgreSQLDAO dao = new PostgreSQLDAO();
            id = dao.insertPerson(name, middlename, surname, dob, sex);
        } catch (ThrottlingException msg) {
            System.out.println(msg);
        } finally {
            System.out.println("This is Finally block " + connection);
            dec();
        }
        return id;
    }

    @WebMethod(operationName = "updatePerson")
    public String updatePerson(String id, String name, String middlename, String surname, String dob, String sex) throws
            ThrottlingException {
        String status = "";
        try {
            inc();
            checkConnection();
            PostgreSQLDAO dao = new PostgreSQLDAO();
            status = dao.updatePerson(id, name, middlename, surname, dob, sex);
        } catch (ThrottlingException msg) {
            System.out.println(msg);
        } finally {
            System.out.println("This is Finally block " + connection);
            dec();
        }
        return status;
    }

    @WebMethod(operationName = "deletePerson")
    public String deletePerson(String id) throws ThrottlingException {
        String status = "";
        try {
            inc();
            checkConnection();
            PostgreSQLDAO dao = new PostgreSQLDAO();
             status= dao.deletPerson(id);
        } catch (ThrottlingException msg) {
            System.out.println(msg);
        } finally {
            System.out.println("This is Finally block " + connection);
            dec();
        }
        return status;
    }

    @WebMethod(operationName = "getPersonsQuery")
    public List<Person> getPersons(String query) throws ThrottlingException {
        List<Person> persons = new ArrayList<>();
        try {
            inc();
            checkConnection();
            PostgreSQLDAO dao = new PostgreSQLDAO();
            persons = dao.getPersons(query);
            dec();
        } catch (ThrottlingException msg) {
            System.out.println(msg);
        } finally {
            System.out.println("This is Finally block " + connection);
            dec();
        }
        return persons;
    }

    @WebMethod(operationName = "findPeople")
    public List<Person> findPeople(String id, String name, String middlename, String surname, String
            dob, String sex) throws ThrottlingException {
        List<Person> persons = new ArrayList<>();
        try {
            inc();
            checkConnection();
            PostgreSQLDAO dao = new PostgreSQLDAO();
            persons = dao.findPeople(id, name, middlename, surname, dob, sex);

        } catch (ThrottlingException msg) {
            System.out.println(msg);
        } finally {
            System.out.println("This is Finally block " + connection);
            dec();
        }
        return persons;
    }


}