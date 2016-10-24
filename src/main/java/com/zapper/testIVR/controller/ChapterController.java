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

  private Integer responseStatus = 800;

  //TODO : Get this by querying for chapters available in a module
  private String
      responseMessage =
      "<response lang = \"en\"><playtext>Press 1 for Chapter 1. Press 2 for Chapter 2. Press 3 for Chapter 3. Press 9 to go back to Welcome Menu</playtext></response>";

  public IVRResponse service() {
    return new IVRResponse(responseStatus, responseMessage);
  }

  @ResponseBody
  @RequestMapping(value = "/publish", method = RequestMethod.GET)
  public String publishChapter() {
    Response response = new Response();
    response.sendSms("working with KooKoo","9100571475");
    response.addPlayText("Message 1. Oral Medicines are medicines that are consumed via oral inlets of the body");
    String xmlString = response.getXML();
    System.out.println("XML String : \n" + response.getXML());
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder;
    Document document = null;
    try {
      documentBuilder = documentBuilderFactory.newDocumentBuilder();
      document = documentBuilder.parse(new InputSource(new StringReader(xmlString)));
    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println(document);
    return response.getXML();
  }
}
