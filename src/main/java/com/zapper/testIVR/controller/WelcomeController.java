package com.zapper.testIVR.controller;

import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.IVRResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Controller
public class WelcomeController implements BasicController{


  @RequestMapping(value = "/home", method = RequestMethod.GET)
  @ResponseBody
  public String startService() {
    Response response = new Response();
    response.addPlayText("Hello! Welcome to M Training. Press 1 to start course. Press 2 to exit");
    return response.getXML();
  }
}
