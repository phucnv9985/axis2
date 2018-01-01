package com.hp.security.client;

public class SecureServiceCGClient {
	public static void main(String[] args) throws Exception {
		SecureserviceStub stub = new SecureserviceStub(null, "https://localhost:8443/axis2/services/secureservice");
		String result = stub.echo("MyMesage");
		System.out.println(result);
	}

}
