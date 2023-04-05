package com.base.service;

import com.base.model.Employee;
import com.base.model.Student;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.*;

@Service
public class ConvertUtil {
    public Student convertXmlToObject() throws JAXBException {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<student>\n" +
                "<name>John</name>\n" +
                "<code>12345</code>\n" +
                "<age>19</age>\n" +
                "</student>";

        JAXBContext jaxbContext = JAXBContext.newInstance(Student.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Student student = (Student) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
        List<Student> students = new ArrayList<>();
        students.add(student);
        return student;
    }

    public Employee convertXmlToObjectN() throws JAXBException {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<employee>\n" +
                "    <department>\n" +
                "        <id>101</id>\n" +
                "        <name>IT</name>\n" +
                "    </department>\n" +
                "    <firstName>Lokesh</firstName>\n" +
                "    <id>1</id>\n" +
                "    <lastName>Gupta</lastName>\n" +
                "</employee>";

        JAXBContext jaxbContext = JAXBContext.newInstance(Employee.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Employee employee = (Employee) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
        return employee;
    }
}
