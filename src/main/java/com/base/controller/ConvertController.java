package com.base.controller;

import com.base.model.Employee;
import com.base.model.Student;
import com.base.service.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBException;

@RestController
@RequiredArgsConstructor
public class ConvertController {
    private final ConvertUtil convertUtil;
    @GetMapping("student")
    public Student convert() throws JAXBException {
        return convertUtil.convertXmlToObject();
    }
    @GetMapping("employee")
    public Employee convertEmployee() throws JAXBException {
        return convertUtil.convertXmlToObjectN();
    }
}
