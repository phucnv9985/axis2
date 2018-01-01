package com.hp.inonly;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

public class Axis2InOnly {

	public static OMElement createPayLoad() {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://hp.com", "ns1");
		OMElement method = fac.createOMElement("update", omNs);
		OMElement value = fac.createOMElement("value", omNs);
		value.setText("10");
		method.addChild(value);
		return method;
	}
	public static void main(String [] args) {
		method1();
	}

	private static void method1() {
		ServiceClient sc = null;
		try {
			sc = new ServiceClient();
			Options opts = new Options();
			opts.setTo(new EndpointReference("http://127.0.0.1:8080/axis2/services/MyService"));
			opts.setAction("urn:update");
			sc.setOptions(opts);
			sc.fireAndForget(createPayLoad());
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
