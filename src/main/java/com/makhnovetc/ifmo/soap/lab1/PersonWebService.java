package com.makhnovetc.ifmo.soap.lab1;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 Класс-реализация веб-сервиса. Данный сервис предоставляет одну операцию –
 getPersons. Обратите внимание на аннотации @WebService и @WebMethod.
 @WebService используется для того, чтобы маркировать класс, который должен
 предоставлять функциональность через веб-сервис. @WebMethod – маркер метода,
 который будет являться операцией веб-сервиса.
 Заметьте, что метод getPersons() возвращает List<Person>, а не какой-либо
 специальный объект. То есть, единственное, что нужно указать разработчику – это
 аннотация над методом, а об остальном позаботится имплементация JAX-WS.

 В данной работе в веб-сервис, разработанный в первой работе, необходимо
 добавить методы для создания, изменения и удаления записей из таблицы.
 Метод создания должен принимать значения полей новой записи, метод
 изменения – идентификатор изменяемой записи, а также новые значения полей, а
 метод удаления – только идентификатор удаляемой записи.
 Метод создания должен возвращать идентификатор новой записи, а методы
 обновления или удаления – статус операции.
 */

@WebService(serviceName = "PersonService")
public class PersonWebService {

    @WebMethod(operationName = "insertPerson")
    public int insertPerson(String name, String middlename, String surname, String dob, String sex){
        PostgreSQLDAO dao = new PostgreSQLDAO();
        int id = dao.insertPerson(name, middlename, surname, dob, sex);
        return id;
    }

    @WebMethod(operationName = "updatePerson")
    public String updatePerson(String id, String name, String middlename, String surname, String dob, String sex){
        PostgreSQLDAO dao = new PostgreSQLDAO();
            String status = dao.updatePerson(id, name, middlename, surname, dob, sex);
            return status;
    }

    @WebMethod(operationName = "deletePerson")
    public String deletePerson(String id){
        PostgreSQLDAO dao= new PostgreSQLDAO();
        String status = dao.deletPerson(id);
        return status;
    }

    @WebMethod(operationName = "getPersonsQuery")
    public List<Person> getPersons(String query)  {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Person> persons = dao.getPersons(query) ;
        return persons;
    }
    @WebMethod(operationName = "findPeople")
    public List<Person> findPeople(String id, String name, String middlename, String surname, String dob, String sex) {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Person> persons = dao.findPeople(id, name, middlename, surname, dob, sex) ;
        return persons;
    }


}