package ufcg.si1.infoCarona.view.janelas;

import ufcg.si1.infoCarona.view.InfoCaronaServerAsync;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;


public class JanelaInicial extends Composite {
	private InfoCaronaServerAsync controller;
	
	public JanelaInicial(InfoCaronaServerAsync controller) {
		this.controller = controller;
		
		DockPanel panelInicial = new DockPanel();
		panelInicial.setWidth("100%");

		PanelEfetuarLogin panelEfetuarLogin = new PanelEfetuarLogin(controller);
		panelInicial.add(panelEfetuarLogin, DockPanel.NORTH);
		
		PanelRodape panelRodape = new PanelRodape();
		panelInicial.add(panelRodape, DockPanel.SOUTH);
		
		PanelCadastrarUsuario panelCadastrarUsuario = new PanelCadastrarUsuario(controller);
		panelInicial.add(panelCadastrarUsuario, DockPanel.CENTER);
		
		initWidget(panelInicial);
	}


}
