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

public class PanelAlterarCadastro extends Composite {
	
	public PanelAlterarCadastro() {
		//panel com o nome do cadastro
		HorizontalPanel hPanelNomeCadastro = new HorizontalPanel();
		Label labelNomeCadastro = new Label("Nome:");
		labelNomeCadastro.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		labelNomeCadastro.setWidth("80px");
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
		labelEnderecoCadastro.setWidth("80px");	
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
		labelEmailCadastro.setWidth("80px");
		TextBox boxEmailCadastro = new TextBox();
		boxEmailCadastro.setStyleName("boxModificada");
		boxEmailCadastro.setWidth("150px");
		
		hPanelEmailCadastro.add(labelEmailCadastro);
		hPanelEmailCadastro.add(boxEmailCadastro);
		hPanelEmailCadastro.setCellVerticalAlignment(labelEmailCadastro, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelEmailCadastro.setCellVerticalAlignment(boxEmailCadastro, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelEmailCadastro.setSpacing(8);
		
		//panel com a nova senha do cadastro
		HorizontalPanel hPanelNovaSenhaCadastro = new HorizontalPanel();
		Label labelNovaSenhaCadastro = new Label("Nova Senha:");
		labelNovaSenhaCadastro.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		labelNovaSenhaCadastro.setWidth("80px");
		PasswordTextBox passordBoxNovaSenha = new PasswordTextBox();
		passordBoxNovaSenha.setStyleName("boxModificada");
		passordBoxNovaSenha.setWidth("150px");
		
		hPanelNovaSenhaCadastro.add(labelNovaSenhaCadastro);
		hPanelNovaSenhaCadastro.add(passordBoxNovaSenha);
		hPanelNovaSenhaCadastro.setCellVerticalAlignment(labelNovaSenhaCadastro, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelNovaSenhaCadastro.setCellVerticalAlignment(passordBoxNovaSenha, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelNovaSenhaCadastro.setSpacing(8);
		
		//panel final com todas as informacoes de cadastro
		VerticalPanel vPanelCadastroUsuario = new VerticalPanel();
		Label labelAlterarCadastro = new Label("Alteração de Cadastro");
		HTML htmlLinha1 = new HTML("<hr>");
		HTML htmlLinha2 = new HTML("<hr>");

		Button botaoAlterarCadastro = new Button("Salvar Alterações");
		botaoAlterarCadastro.setStyleName("botaoModificado");
		labelAlterarCadastro.setStyleName("labelNomeJanela");
		
		vPanelCadastroUsuario.add(labelAlterarCadastro);
		vPanelCadastroUsuario.add(htmlLinha1);
		vPanelCadastroUsuario.add(hPanelNomeCadastro);
		vPanelCadastroUsuario.add(hPanelEnderecoCadastro);
		vPanelCadastroUsuario.add(hPanelEmailCadastro);
		vPanelCadastroUsuario.add(hPanelNovaSenhaCadastro);
		vPanelCadastroUsuario.add(htmlLinha2);
		vPanelCadastroUsuario.add(botaoAlterarCadastro);
				
		vPanelCadastroUsuario.setCellHorizontalAlignment(labelAlterarCadastro, HasHorizontalAlignment.ALIGN_LEFT);
		vPanelCadastroUsuario.setCellHorizontalAlignment(botaoAlterarCadastro, HasHorizontalAlignment.ALIGN_CENTER);
		
		//panel com os itens do panelCadastro
		HorizontalPanel hPanelCadastro = new HorizontalPanel();
		hPanelCadastro.add(vPanelCadastroUsuario);
		
		hPanelCadastro.setWidth("10%");
		hPanelCadastro.setSpacing(8);
		hPanelCadastro.setCellVerticalAlignment(vPanelCadastroUsuario, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelCadastro.setCellHorizontalAlignment(vPanelCadastroUsuario, HasHorizontalAlignment.ALIGN_CENTER);
		
		initWidget(hPanelCadastro);
	}

}
