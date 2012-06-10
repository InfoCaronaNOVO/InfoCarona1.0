package ufcg.si1.infoCarona.view.janelas;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class JanelaLogado extends Composite {

	private PanelCadastrarCarona panelCadastrarCarona;
	private PanelBuscarCarona panelBuscarCarona;
	private PanelAlterarCadastro panelAlterarCadastro;
	
	public JanelaLogado(String nomeUsuario) {
		DockPanel panelLogado = new DockPanel();
		panelLogado.setWidth("100%");
		
		PanelUsuarioLogado panelUsuarioLogado = new PanelUsuarioLogado(nomeUsuario);
		panelLogado.add(panelUsuarioLogado, DockPanel.NORTH);
		
		PanelRodape panelRodape = new PanelRodape();
		panelLogado.add(panelRodape, DockPanel.SOUTH);
		
		//panels para carregarno centro
		HorizontalPanel panelCentral = new HorizontalPanel();
		panelLogado.add(panelCentral, DockPanel.CENTER);

		
		panelCadastrarCarona = new PanelCadastrarCarona();		
		panelCadastrarCarona.setVisible(false);
		panelCentral.add(panelCadastrarCarona);
		
		panelBuscarCarona = new PanelBuscarCarona();
		panelBuscarCarona.setVisible(false);
		panelCentral.add(panelBuscarCarona);
		
		panelAlterarCadastro = new PanelAlterarCadastro();
		panelAlterarCadastro.setVisible(false);
		panelCentral.add(panelAlterarCadastro);
		///////////////////////////////
		
		PanelMenuUsuarioLogado panelMenuUsuarioLogado = new PanelMenuUsuarioLogado(this);
		panelLogado.add(panelMenuUsuarioLogado, DockPanel.WEST);	
		
		panelLogado.setCellWidth(panelLogado.getWidget(2), "80%");

		initWidget(panelLogado);
		
	}

	public void selecionarMenu(int menu) {
		panelCadastrarCarona.setVisible(false);
		panelBuscarCarona.setVisible(false);
		panelAlterarCadastro.setVisible(false);
		switch (menu) {
			case 1:
				panelCadastrarCarona.setVisible(true);
				break;
			case 2:
				panelBuscarCarona.setVisible(true);
				break;
			case 7:
				panelAlterarCadastro.setVisible(true);
				break;
			default:
				panelCadastrarCarona.setVisible(false);
				panelBuscarCarona.setVisible(false);
				panelAlterarCadastro.setVisible(false);
				break;
		}
	}
}
