package io.spring2go.hystrixlab.schoolservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.spring2go.hystrixlab.schoolservice.delegate.StudentServiceDelegate1;
import io.spring2go.hystrixlab.schoolservice.delegate.StudentServiceDelegate2;

@RestController
public class SchoolServiceController {
     
    @Autowired
	StudentServiceDelegate1 studentServiceDelegate1;

    @Autowired
    StudentServiceDelegate2 studentServiceDelegate2;
 
    @RequestMapping(value = "/getSchoolDetails1/{schoolName}", method = RequestMethod.GET)
    public String getSchoolDetails1(@PathVariable String schoolName) {
        System.out.println("getSchoolDetails1");
        return studentServiceDelegate1.callStudentService1(schoolName);
    }

    @RequestMapping(value = "/getSchoolDetails2/{schoolName}", method = RequestMethod.GET)
    public String getSchoolDetails2(@PathVariable String schoolName) {
        System.out.println("getSchoolDetails2");
        return studentServiceDelegate2.callStudentService2(schoolName);
    }
}