package com.zapper.testIVR.controller;

import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.kookooJavaExtensions.MyResponse;
import com.zapper.testIVR.model.IVRResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@Controller
@RequestMapping(value = "/chapter")
public class ChapterController implements BasicController {

  @ResponseBody
  @RequestMapping(value = "/publish", method = RequestMethod.GET)
  public String publishChapter() {
    Response response = new Response();
    response.sendSms("working with KooKoo","9100571475");
    response.addPlayText("Message 1. Oral Medicines are medicines that are consumed via oral inlets of the body");
    return response.getXML();
  }
}
