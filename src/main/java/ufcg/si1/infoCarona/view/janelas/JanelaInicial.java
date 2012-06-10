package ufcg.si1.infoCarona.view.janelas;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;


public class JanelaInicial extends Composite {
	
	public JanelaInicial() {
		DockPanel panelInicial = new DockPanel();
		panelInicial.setWidth("100%");

		PanelEfetuarLogin panelEfetuarLogin = new PanelEfetuarLogin();
		panelInicial.add(panelEfetuarLogin, DockPanel.NORTH);
		
		PanelRodape panelRodape = new PanelRodape();
		panelInicial.add(panelRodape, DockPanel.SOUTH);
		
		PanelCadastrarUsuario panelCadastrarUsuario = new PanelCadastrarUsuario();
		panelInicial.add(panelCadastrarUsuario, DockPanel.CENTER);
		
		initWidget(panelInicial);
	}


}
