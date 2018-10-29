package com.junyao.sms.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * http://172.19.66.216:8080/reportServer/services/smsinterface?wsdl
 *
 * This class was generated by Apache CXF 3.1.8
 * 2016-11-04T14:39:44.524+08:00
 * Generated source version: 3.1.8
 * 
 */
@WebService(targetNamespace = "http://service.sms.junyao.com/", name = "JwSmsService")
@XmlSeeAlso({ObjectFactory.class, com.junyao.sms.entity.req.ObjectFactory.class})
public interface JwSmsService {

    @WebResult(name = "result", targetNamespace = "")
    @RequestWrapper(localName = "getDefectInfo", targetNamespace = "http://service.sms.junyao.com/", className = "com.junyao.sms.service.GetDefectInfo")
    @WebMethod
    @ResponseWrapper(localName = "getDefectInfoResponse", targetNamespace = "http://service.sms.junyao.com/", className = "com.junyao.sms.service.GetDefectInfoResponse")
    public java.lang.String getDefectInfo(
        @WebParam(name = "params", targetNamespace = "")
        com.junyao.sms.entity.req.PlaneDDQuery params
    );

    @WebResult(name = "result", targetNamespace = "")
    @RequestWrapper(localName = "getDefectRepairInfo", targetNamespace = "http://service.sms.junyao.com/", className = "com.junyao.sms.service.GetDefectRepairInfo")
    @WebMethod
    @ResponseWrapper(localName = "getDefectRepairInfoResponse", targetNamespace = "http://service.sms.junyao.com/", className = "com.junyao.sms.service.GetDefectRepairInfoResponse")
    public java.lang.String getDefectRepairInfo(
        @WebParam(name = "params", targetNamespace = "")
        com.junyao.sms.entity.req.PlanFcQuery params
    );

    @WebResult(name = "result", targetNamespace = "")
    @RequestWrapper(localName = "getPlaneInfo", targetNamespace = "http://service.sms.junyao.com/", className = "com.junyao.sms.service.GetPlaneInfo")
    @WebMethod
    @ResponseWrapper(localName = "getPlaneInfoResponse", targetNamespace = "http://service.sms.junyao.com/", className = "com.junyao.sms.service.GetPlaneInfoResponse")
    public java.lang.String getPlaneInfo(
        @WebParam(name = "params", targetNamespace = "")
        com.junyao.sms.entity.req.PlanStaticQuery params
    );

    @WebResult(name = "result", targetNamespace = "")
    @RequestWrapper(localName = "getPlaneDDMoniInfo", targetNamespace = "http://service.sms.junyao.com/", className = "com.junyao.sms.service.GetPlaneDDMoniInfo")
    @WebMethod
    @ResponseWrapper(localName = "getPlaneDDMoniInfoResponse", targetNamespace = "http://service.sms.junyao.com/", className = "com.junyao.sms.service.GetPlaneDDMoniInfoResponse")
    public java.lang.String getPlaneDDMoniInfo(
        @WebParam(name = "params", targetNamespace = "")
        com.junyao.sms.entity.req.PlanDDMoniQuery params
    );
}