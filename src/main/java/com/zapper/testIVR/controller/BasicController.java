package com.zapper.testIVR.controller;

import com.zapper.testIVR.model.IVRResponse;

import org.springframework.stereotype.Controller;

@Controller
public interface BasicController {

    IVRResponse service();
}
