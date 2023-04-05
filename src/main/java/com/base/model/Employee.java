package com.base.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Getter
@Setter
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String firstName;
    private String lastName;
    private Department department;
    public Employee() {
        super();
    }
    //Setters and Getters
//    @Override
//    public String toString() {
//        return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", department="
//                + department + "]";
//    }
    // It is called immediately after the object is created and before the unmarshalling begins.
    // The callback provides an opportunity to initialize JavaBean properties prior to unmarshalling.
//    void beforeUnmarshal(Unmarshaller unmarshaller, Object parent) {
//        System.out.println("Before Unmarshaller Callback");
//    }
//    // It is called after all the properties are unmarshalled for this object,
//    // but before this object is set to the parent object.
//    void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {
//        System.out.println("After Unmarshaller Callback");
//    }
}