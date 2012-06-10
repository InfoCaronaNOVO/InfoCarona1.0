package ufcg.si1.infoCarona.view.janelas;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PanelCadastrarUsuario extends Composite {
	
	public PanelCadastrarUsuario() {
		//panel com o nome do cadastro
		HorizontalPanel hPanelNomeCadastro = new HorizontalPanel();
		Label labelNomeCadastro = new Label("Nome:");
		labelNomeCadastro.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		labelNomeCadastro.setWidth("60px");
		TextBox boxNomeCadastro = new TextBox();
		boxNomeCadastro.setStyleName("boxModificada");
		boxNomeCadastro.setWidth("150px");
		
		hPanelNomeCadastro.add(labelNomeCadastro);
		hPanelNomeCadastro.add(boxNomeCadastro);
		hPanelNomeCadastro.setCellVerticalAlignment(labelNomeCadastro, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelNomeCadastro.setCellVerticalAlignment(boxNomeCadastro, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelNomeCadastro.setSpacing(8);
		
		//panel com o endereco do cadastro
		HorizontalPanel hPanelEnderecoCadastro = new HorizontalPanel();
		Label labelEnderecoCadastro = new Label("Endereço:");
		labelEnderecoCadastro.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		labelEnderecoCadastro.setWidth("60px");	
		TextBox boxEnderecoCadastro = new TextBox();
		boxEnderecoCadastro.setStyleName("boxModificada");
		boxEnderecoCadastro.setWidth("150px");
				
		hPanelEnderecoCadastro.add(labelEnderecoCadastro);
		hPanelEnderecoCadastro.add(boxEnderecoCadastro);
		hPanelEnderecoCadastro.setCellVerticalAlignment(labelEnderecoCadastro, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelEnderecoCadastro.setCellVerticalAlignment(boxEnderecoCadastro, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelEnderecoCadastro.setSpacing(8);
		
		//panel com o email do cadastro
		HorizontalPanel hPanelEmailCadastro = new HorizontalPanel();
		Label labelEmailCadastro = new Label("Email:");
		labelEmailCadastro.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		labelEmailCadastro.setWidth("60px");
		TextBox boxEmailCadastro = new TextBox();
		boxEmailCadastro.setStyleName("boxModificada");
		boxEmailCadastro.setWidth("150px");
		
		hPanelEmailCadastro.add(labelEmailCadastro);
		hPanelEmailCadastro.add(boxEmailCadastro);
		hPanelEmailCadastro.setCellVerticalAlignment(labelEmailCadastro, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelEmailCadastro.setCellVerticalAlignment(boxEmailCadastro, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelEmailCadastro.setSpacing(8);
		
		//panel com o usuario do cadastro
		HorizontalPanel hPanelUsuarioCadastro = new HorizontalPanel();
		Label labelUsuarioCadastro = new Label("Usuário:");
		labelUsuarioCadastro.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		labelUsuarioCadastro.setWidth("60px");
		TextBox boxUsuarioCadastro = new TextBox();
		boxUsuarioCadastro.setStyleName("boxModificada");
		boxUsuarioCadastro.setWidth("150px");
		
		hPanelUsuarioCadastro.add(labelUsuarioCadastro);
		hPanelUsuarioCadastro.add(boxUsuarioCadastro);
		hPanelUsuarioCadastro.setCellVerticalAlignment(labelUsuarioCadastro, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelUsuarioCadastro.setCellVerticalAlignment(boxUsuarioCadastro, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelUsuarioCadastro.setSpacing(8);
		
		//panel com a senha de cadastro
		HorizontalPanel hPanelSenhaCadastro = new HorizontalPanel();
		Label labelSenhaCadastro = new Label("Senha:");
		labelSenhaCadastro.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		labelSenhaCadastro.setWidth("60px");
		PasswordTextBox passwordBoxSenhaCadastro = new PasswordTextBox();
		passwordBoxSenhaCadastro.setStyleName("boxModificada");
		passwordBoxSenhaCadastro.setWidth("150px");
		
		hPanelSenhaCadastro.add(labelSenhaCadastro);
		hPanelSenhaCadastro.add(passwordBoxSenhaCadastro);
		hPanelSenhaCadastro.setCellVerticalAlignment(labelSenhaCadastro, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelSenhaCadastro.setCellVerticalAlignment(passwordBoxSenhaCadastro, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelSenhaCadastro.setSpacing(8);
		
		//panel final com todas as informacoes de cadastro
		VerticalPanel vPanelCadastroUsuario = new VerticalPanel();
		Label labelCadastrese = new Label("Cadastre-se!");
		HTML htmlLinha1 = new HTML("<hr>");
		HTML htmlLinha2 = new HTML("<hr>");

		Button botaoCadastrese = new Button("Cadastrar");
		botaoCadastrese.setWidth("80px");
		botaoCadastrese.setStyleName("botaoModificado");
		labelCadastrese.setStyleName("labelNomeJanela");
		
		vPanelCadastroUsuario.add(labelCadastrese);
		vPanelCadastroUsuario.add(htmlLinha1);
		vPanelCadastroUsuario.add(hPanelNomeCadastro);
		vPanelCadastroUsuario.add(hPanelEnderecoCadastro);
		vPanelCadastroUsuario.add(hPanelEmailCadastro);
		vPanelCadastroUsuario.add(hPanelUsuarioCadastro);
		vPanelCadastroUsuario.add(hPanelSenhaCadastro);
		vPanelCadastroUsuario.add(htmlLinha2);
		vPanelCadastroUsuario.add(botaoCadastrese);
				
		vPanelCadastroUsuario.setCellHorizontalAlignment(labelCadastrese, HasHorizontalAlignment.ALIGN_LEFT);
		vPanelCadastroUsuario.setCellHorizontalAlignment(botaoCadastrese, HasHorizontalAlignment.ALIGN_CENTER);
		
		//panel com os itens do panelCadastro
		HorizontalPanel hPanelCadastro = new HorizontalPanel();
		Image imageInfoCarona = new Image("imagens/infocarona.png");
		hPanelCadastro.add(imageInfoCarona);
		hPanelCadastro.add(vPanelCadastroUsuario);
		
		hPanelCadastro.setWidth("100%");
		hPanelCadastro.setSpacing(20);
		hPanelCadastro.setCellVerticalAlignment(vPanelCadastroUsuario, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelCadastro.setCellHorizontalAlignment(imageInfoCarona, HasHorizontalAlignment.ALIGN_CENTER);
		hPanelCadastro.setCellVerticalAlignment(imageInfoCarona, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelCadastro.setCellHorizontalAlignment(vPanelCadastroUsuario, HasHorizontalAlignment.ALIGN_CENTER);
		
		initWidget(hPanelCadastro);
		
	}

}
