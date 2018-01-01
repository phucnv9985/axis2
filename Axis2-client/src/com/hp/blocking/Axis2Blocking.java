package com.hp.blocking;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;

public class Axis2Blocking {

	public static OMElement createPayLoad() {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace("http://hp.com", "ns1");
		OMElement method = fac.createOMElement("echo", omNs);
		OMElement value = fac.createOMElement("value", omNs);
		value.setText("Hello , my first service utilization");
		method.addChild(value);
		return method;
	}

	public static void main(String [] args) {
		method1();
	}

	private static void method2() {
		ServiceClient sc = null;
		try {
			sc = new ServiceClient(null, new URL("http://localhost:8080/axis2/services/MyService?wsdl"),null,null);
			OMElement res = sc.sendReceive(new QName(" http://ws.apache.org/axis2 ","echo"),createPayLoad());
			System.out.println(res);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void method1() {
		ServiceClient sc = null;
		try {
			sc = new ServiceClient();
			// create option object
			Options opts = new Options();
			//setting target EPR
			opts.setTo(new EndpointReference(
			"http://127.0.0.1:8080/axis2/services/MyService"));
			//Setting action
			opts.setAction("urn:echo");
			//setting created option into service client
			sc.setOptions(opts);
			OMElement res = sc.sendReceive(createPayLoad());
			System.out.println(res);
		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
