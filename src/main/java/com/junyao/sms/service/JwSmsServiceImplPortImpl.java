
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.junyao.sms.service;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.8
 * 2016-11-04T14:39:44.507+08:00
 * Generated source version: 3.1.8
 * 
 */

@javax.jws.WebService(
                      serviceName = "JwSmsServiceImplService",
                      portName = "JwSmsServiceImplPort",
                      targetNamespace = "http://service.sms.junyao.com/",
                      wsdlLocation = "file:/D:/Administrator/Documents/workspace_eclipse/smsmain/sms/src/main/resources/机务接口说明.wsdl",
                      endpointInterface = "com.junyao.sms.service.JwSmsService")
                      
public class JwSmsServiceImplPortImpl implements JwSmsService {

    private static final Logger LOG = Logger.getLogger(JwSmsServiceImplPortImpl.class.getName());

    /* (non-Javadoc)
     * @see com.junyao.sms.service.JwSmsService#getDefectInfo(com.junyao.sms.entity.req.PlaneDDQuery params)*
     */
    public java.lang.String getDefectInfo(com.junyao.sms.entity.req.PlaneDDQuery params) { 
        LOG.info("Executing operation getDefectInfo");
        System.out.println(params);
        try {
            java.lang.String _return = "_return-610784496";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.junyao.sms.service.JwSmsService#getDefectRepairInfo(com.junyao.sms.entity.req.PlanFcQuery params)*
     */
    public java.lang.String getDefectRepairInfo(com.junyao.sms.entity.req.PlanFcQuery params) { 
        LOG.info("Executing operation getDefectRepairInfo");
        System.out.println(params);
        try {
            java.lang.String _return = "_return870506589";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.junyao.sms.service.JwSmsService#getPlaneInfo(com.junyao.sms.entity.req.PlanStaticQuery params)*
     */
    public java.lang.String getPlaneInfo(com.junyao.sms.entity.req.PlanStaticQuery params) { 
        LOG.info("Executing operation getPlaneInfo");
        System.out.println(params);
        try {
            java.lang.String _return = "_return1357597234";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.junyao.sms.service.JwSmsService#getPlaneDDMoniInfo(com.junyao.sms.entity.req.PlanDDMoniQuery params)*
     */
    public java.lang.String getPlaneDDMoniInfo(com.junyao.sms.entity.req.PlanDDMoniQuery params) { 
        LOG.info("Executing operation getPlaneDDMoniInfo");
        System.out.println(params);
        try {
            java.lang.String _return = "_return1085709184";
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}