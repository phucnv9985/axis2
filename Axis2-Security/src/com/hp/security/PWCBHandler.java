package com.hp.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class PWCBHandler implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			WSPasswordCallback pwcb = (WSPasswordCallback) callbacks[i];
			if (pwcb.getIdentifer().equals("axis2uers") && pwcb.getPassword().equals("password")) {
				// If authentication successful, simply return
				return;
			} else {
				throw new UnsupportedCallbackException(callbacks[i], "check failed");
			}
		}
	}

}
