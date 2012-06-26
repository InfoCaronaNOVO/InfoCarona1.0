package ufcg.si1.infoCarona.view.janelas;

import ufcg.si1.infoCarona.model.sistema.SistemaRaiz;
import ufcg.si1.infoCarona.view.ExceptionSerialized;
import ufcg.si1.infoCarona.view.InfoCaronaServerAsync;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PanelEfetuarLogin extends Composite {
	
	private InfoCaronaServerAsync controller;
	
	public PanelEfetuarLogin(final InfoCaronaServerAsync controller) {
		this.controller = controller;
		VerticalPanel vPanel01 = new VerticalPanel();
		Label labelNome = new Label("Login");
		labelNome.setStyleName("labelNomeBranco");
		final TextBox boxLogin = new TextBox();
		boxLogin.setStyleName("boxModificada2");
		boxLogin.setWidth("180px");
		Hyperlink link = new Hyperlink();
		link.setText("Esqueceu seus dados?");
		link.setStyleName("link");
		link.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				final DialogBox dialogEsqueceuSenha = new DialogBox();
				dialogEsqueceuSenha.setGlassEnabled(true);
				dialogEsqueceuSenha.setAnimationEnabled(true);
				dialogEsqueceuSenha.setText("Reenvio de Dados");
				dialogEsqueceuSenha.center();
				dialogEsqueceuSenha.show();
				VerticalPanel vPanel01 = new VerticalPanel();
				Label labelUsuarioEmail = new Label("Digite seu login ou email");
				labelUsuarioEmail.setStyleName("menu");
				TextBox boxUsuarioEmail = new TextBox();
				boxUsuarioEmail.setWidth("180px");
				boxUsuarioEmail.setStyleName("boxModificada");
				vPanel01.add(labelUsuarioEmail);
				vPanel01.add(boxUsuarioEmail);
				HorizontalPanel hPanel01 = new HorizontalPanel();
				Button buttonEnviar = new Button("Enviar");
				buttonEnviar.setStyleName("botaoModificado");
				Button buttonFechar = new Button("Fechar");
				buttonFechar.setStyleName("botaoModificado");
				hPanel01.add(buttonEnviar);
				hPanel01.add(buttonFechar);
				hPanel01.setSpacing(5);
				vPanel01.add(hPanel01);
				vPanel01.setCellHorizontalAlignment(hPanel01, HasHorizontalAlignment.ALIGN_CENTER);
				dialogEsqueceuSenha.add(vPanel01);
				buttonFechar.addClickHandler(new ClickHandler() {
					
					public void onClick(ClickEvent event) {
						dialogEsqueceuSenha.hide();
						
					}
				});
				
			}
		});
		
		vPanel01.add(labelNome);
		vPanel01.add(boxLogin);
		vPanel01.add(link);
		vPanel01.setSpacing(4);		
		
		//panel com as imagens das redes sociais e o botao entrar
		HorizontalPanel hPanel01 = new HorizontalPanel();
		Button botaoEntrar = new Button("Entrar");
		botaoEntrar.setStyleName("botaoModificado");
		botaoEntrar.setWidth("100px");
		
		Image imageTwitter = new Image("imagens/twitter.png"); 
		imageTwitter.setSize("24px", "24px");
		imageTwitter.setTitle("Twitter");
		Image imageFacebook = new Image("imagens/facebook.png");
		imageFacebook.setSize("24px", "24px");
		imageFacebook.setTitle("Facebook");
		Image imageOrkut = new Image("imagens/orkut.png");
		imageOrkut.setSize("24px", "24px");
		imageOrkut.setTitle("Orkut");

		hPanel01.add(imageTwitter);
		hPanel01.add(imageFacebook);
		hPanel01.add(imageOrkut);
		hPanel01.add(botaoEntrar);
		hPanel01.setSpacing(1);
		
		//panel com o campo senha para efetuar login
		VerticalPanel vPanel02 = new VerticalPanel();
		Label labelSenha = new Label("Senha");
		labelSenha.setStyleName("labelNomeBranco");
		final PasswordTextBox passwordLogin = new PasswordTextBox();
		passwordLogin.setWidth("172px");
		passwordLogin.setStyleName("boxModificada2");
		
		vPanel02.add(labelSenha);
		vPanel02.add(passwordLogin);
		vPanel02.add(hPanel01);
		vPanel02.setSpacing(4);
		
		//panel com todos os panel de efetuar login
		HorizontalPanel panelEfetuarLogin = new HorizontalPanel();	
		panelEfetuarLogin.add(vPanel01);
		panelEfetuarLogin.add(vPanel02);
		panelEfetuarLogin.setSpacing(1);
		panelEfetuarLogin.setHeight("95px");
		panelEfetuarLogin.setWidth("100%");
		panelEfetuarLogin.setCellWidth(vPanel02, "1px");
		panelEfetuarLogin.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		panelEfetuarLogin.setCellVerticalAlignment(vPanel01,HasVerticalAlignment.ALIGN_TOP);
		panelEfetuarLogin.setCellHorizontalAlignment(vPanel01, HasHorizontalAlignment.ALIGN_RIGHT);
		panelEfetuarLogin.setCellVerticalAlignment(vPanel02,HasVerticalAlignment.ALIGN_TOP);
		panelEfetuarLogin.setCellHorizontalAlignment(vPanel02, HasHorizontalAlignment.ALIGN_RIGHT);
		panelEfetuarLogin.setStyleName("panelFundoLaranja");
		
		botaoEntrar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				controller.abrirSessao(boxLogin.getText(), passwordLogin.getText(), new AsyncCallback<String>() {
					public void onSuccess(String result) {
						JanelaLogado janelaLogado = new JanelaLogado(controller,result, boxLogin.getText());
						RootPanel.get().clear();
						RootPanel.get().add(janelaLogado);
						
					}					
					public void onFailure(Throwable caught) {
						DialogMensagemUsuario dialogErro = new DialogMensagemUsuario("Falhou", caught.getMessage());
						dialogErro.show();
					}
					
					
				});
			}
		});
		
		initWidget(panelEfetuarLogin);
	}

}
