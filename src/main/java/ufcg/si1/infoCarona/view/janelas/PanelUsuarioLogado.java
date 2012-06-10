package ufcg.si1.infoCarona.view.janelas;


import ufcg.si1.infoCarona.view.InfoCaronaView;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;

public class PanelUsuarioLogado extends Composite {

	public PanelUsuarioLogado(String nomeUsuario) {
		HorizontalPanel panelUsuarioLogado = new HorizontalPanel();
		panelUsuarioLogado.setHeight("90px");
		panelUsuarioLogado.setWidth("100%");
		panelUsuarioLogado.setStyleName("panelFundoLaranja");
		HorizontalPanel hPanel01 = new HorizontalPanel();
		Image imageUsuario = new Image("imagens/imagemUsuario01.png");
		imageUsuario.setSize("70px","70px");
		imageUsuario.setStyleName("images");
		hPanel01.add(imageUsuario);
		
		Command comandoLogout = new Command() {
			public void execute() {
				RootPanel.get().clear();
				InfoCaronaView sistema = new InfoCaronaView();
				sistema.onModuleLoad();
			}
		};
		
		MenuBar menu1 = new MenuBar(true);
		menu1.addItem("Sair",comandoLogout);
		MenuBar menuPrincipal = new MenuBar();
		menuPrincipal.addItem(nomeUsuario,menu1);
		menuPrincipal.setAnimationEnabled(true);
		menuPrincipal.ensureDebugId("cwMenuBar");
		hPanel01.add(menuPrincipal);
		hPanel01.setCellVerticalAlignment(imageUsuario, HasVerticalAlignment.ALIGN_BOTTOM);
		hPanel01.setCellVerticalAlignment(menuPrincipal, HasVerticalAlignment.ALIGN_BOTTOM);
		hPanel01.setSpacing(5);
		panelUsuarioLogado.add(hPanel01);
		panelUsuarioLogado.setCellHorizontalAlignment(hPanel01, HasHorizontalAlignment.ALIGN_RIGHT);
		panelUsuarioLogado.setCellVerticalAlignment(hPanel01, HasVerticalAlignment.ALIGN_BOTTOM);
		panelUsuarioLogado.setSpacing(5);
		
		initWidget(panelUsuarioLogado);
	}
}
