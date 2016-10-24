package com.zapper.testIVR.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class IVRResponse {

    @XmlElement
    private Integer responseCode;

    @XmlElement
    private String responseMessage;

    public IVRResponse(Integer responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public IVRResponse() {
    }

    public IVRResponse(Integer responseCode) {
        this.responseCode = responseCode;
        this.responseMessage = "";
    }

    public Boolean isNetworkFailure() {
        return IVRResponseCodes.NETWORK_FAILURE.equals(responseCode);
    }

    public boolean isSuccess() {
        return IVRResponseCodes.OK.equals(responseCode);
    }

    public Boolean hasValidationErrors() {
        return IVRResponseCodes.MISSING_FILES.equals(responseCode);
    }


    public Integer getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

}
