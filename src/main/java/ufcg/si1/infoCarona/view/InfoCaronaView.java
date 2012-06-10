package ufcg.si1.infoCarona.view;


import ufcg.si1.infoCarona.view.janelas.JanelaInicial;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class InfoCaronaView implements EntryPoint {
	
	JanelaInicial janelaInicial;
	
	public void onModuleLoad() {
		janelaInicial = new JanelaInicial();
		
		RootPanel.get().add(janelaInicial);	
	}
}
