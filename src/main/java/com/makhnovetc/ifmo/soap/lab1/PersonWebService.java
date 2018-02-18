package com.makhnovetc.ifmo.soap.lab1;

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
 */

@WebService(serviceName = "PersonService")
public class PersonWebService {
    @WebMethod(operationName = "getPersons")
    public List<Person> getPersons() {
        PostgreSQLDAO dao = new PostgreSQLDAO();
        List<Person> persons = dao.getPersons();
        return persons;
    }
}