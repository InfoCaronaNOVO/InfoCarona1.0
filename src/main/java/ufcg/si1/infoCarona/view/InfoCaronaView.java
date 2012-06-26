package ufcg.si1.infoCarona.view;

import ufcg.si1.infoCarona.view.janelas.JanelaInicial;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class InfoCaronaView implements EntryPoint {
	
	private InfoCaronaServerAsync controller;
	private JanelaInicial janelaInicial;
	
	public void onModuleLoad() {
		controller = GWT.create(InfoCaronaServer.class);
		janelaInicial = new JanelaInicial(controller);		
		RootPanel.get().add(janelaInicial);	
	}
}
