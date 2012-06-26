package ufcg.si1.infoCarona.view;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ExceptionSerialized extends Exception implements IsSerializable {
	
	public ExceptionSerialized() {
	
	}
	public ExceptionSerialized(String erroMsg) {
		super(erroMsg);
	}
}
