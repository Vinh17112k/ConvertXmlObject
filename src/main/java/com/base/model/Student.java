package com.base.model;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.PROPERTY)
@Setter
@Getter
public class Student {
//    @XmlElement
    private String name;
//    @XmlElement
    private String age;
//    @XmlElement
    private String code;
}
