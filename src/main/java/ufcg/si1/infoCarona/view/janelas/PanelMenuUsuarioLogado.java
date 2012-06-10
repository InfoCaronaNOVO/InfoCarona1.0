package ufcg.si1.infoCarona.view.janelas;

import java.util.Iterator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.TreeListener;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PanelMenuUsuarioLogado extends Composite implements TreeListener {
	
	private Tree root;
	private JanelaLogado janelaLogado;
	
	public PanelMenuUsuarioLogado(JanelaLogado janelaLogado) {
		this.janelaLogado = janelaLogado;
		VerticalPanel panelMenuUsuario = new VerticalPanel();
		//labels utilizados para identificacao
		Label labelCarona = new Label("CARONAS");
		labelCarona.setStyleName("labelMenu");
		Label labelSolicitacoes = new Label("SOLICITAÇÕES");
		labelSolicitacoes.setStyleName("labelMenu");
		Label labelConfig = new Label("CONFIGURAÇÕES");
		labelConfig.setStyleName("labelMenu");
		//panel cadastrarCarona
		HorizontalPanel panelCadastrarCarona = new HorizontalPanel();
		Image imageCadastrarCarona = new Image("imagens/carro02.png");
		imageCadastrarCarona.setSize("20px","20px");
		Label labelCadastrarCarona = new Label("Cadastrar Carona");
		labelCadastrarCarona.setStyleName("menu");
		panelCadastrarCarona.add(imageCadastrarCarona);
		panelCadastrarCarona.add(labelCadastrarCarona);
		panelCadastrarCarona.setSpacing(4);
		panelCadastrarCarona.setCellVerticalAlignment(labelCadastrarCarona, HasVerticalAlignment.ALIGN_MIDDLE);
		//panel buscarCaronas
		HorizontalPanel panelBuscarCaronas = new HorizontalPanel();
		Image imageBuscarCaronas = new Image("imagens/busca01.png");
		imageBuscarCaronas.setSize("20px","20px");
		Label labelBuscarCaronas = new Label("Buscar Caronas");
		labelBuscarCaronas.setStyleName("menu");
		panelBuscarCaronas.add(imageBuscarCaronas);
		panelBuscarCaronas.add(labelBuscarCaronas);
		panelBuscarCaronas.setSpacing(4);
		panelBuscarCaronas.setCellVerticalAlignment(labelBuscarCaronas, HasVerticalAlignment.ALIGN_MIDDLE);
		//panel SoliticacoesFeitas
		HorizontalPanel panelSolicitacoesFeitas = new HorizontalPanel();
		Image imageSolicitacoesFeitas = new Image("imagens/efetuarCadastro01.png");
		imageSolicitacoesFeitas.setSize("20px","20px");
		Label labelSolicitacoesFeitas = new Label("Solicitaçoes Feitas");
		labelSolicitacoesFeitas.setStyleName("menu");
		panelSolicitacoesFeitas.add(imageSolicitacoesFeitas);
		panelSolicitacoesFeitas.add(labelSolicitacoesFeitas);
		panelSolicitacoesFeitas.setSpacing(4);
		panelSolicitacoesFeitas.setCellVerticalAlignment(labelSolicitacoesFeitas, HasVerticalAlignment.ALIGN_MIDDLE);
		//panel SoliticacoesPendentes
		HorizontalPanel panelSolicitacoesPendentes = new HorizontalPanel();
		Image imageSolicitacoesPendentes = new Image("imagens/calendario01.png");
		imageSolicitacoesPendentes.setSize("20px","20px");
		Label labelSolicitacoesPendentes = new Label("Solicitaçoes Pendentes");
		labelSolicitacoesPendentes.setStyleName("menu");
		panelSolicitacoesPendentes.add(imageSolicitacoesPendentes);
		panelSolicitacoesPendentes.add(labelSolicitacoesPendentes);
		panelSolicitacoesPendentes.setSpacing(4);
		panelSolicitacoesPendentes.setCellVerticalAlignment(labelSolicitacoesPendentes, HasVerticalAlignment.ALIGN_MIDDLE);
		//panel alterarCadastro
		HorizontalPanel panelAlterarCadastro = new HorizontalPanel();
		Image imageAlterarCadastro = new Image("imagens/alterarCadastro01.png");
		imageAlterarCadastro.setSize("20px","20px");
		Label labelAlterarCadastro = new Label("Alterar Cadastro");
		labelAlterarCadastro.setStyleName("menu");
		panelAlterarCadastro.add(imageAlterarCadastro);
		panelAlterarCadastro.add(labelAlterarCadastro);
		panelAlterarCadastro.setSpacing(4);
		panelAlterarCadastro.setCellVerticalAlignment(labelAlterarCadastro, HasVerticalAlignment.ALIGN_MIDDLE);
		
		//tree final
		root = new Tree();
		root.addTreeListener(this);
		TreeItem treeItemCadastrarCaronas = new TreeItem(panelCadastrarCarona);
		TreeItem treeItemListarCaronas = new TreeItem(panelBuscarCaronas);
		TreeItem treeItemSolicitacoesFeitas = new TreeItem(panelSolicitacoesFeitas);
		TreeItem treeItemSolicitacoesPendentes = new TreeItem(panelSolicitacoesPendentes);
		TreeItem treeItemAlterarDados = new TreeItem(panelAlterarCadastro);
		root.addItem(labelCarona);
		root.addItem(treeItemCadastrarCaronas);
		root.addItem(treeItemListarCaronas);
		root.addItem(labelSolicitacoes);
		root.addItem(treeItemSolicitacoesFeitas);
		root.addItem(treeItemSolicitacoesPendentes);
		root.addItem(labelConfig);
		root.addItem(treeItemAlterarDados);

		VerticalPanel vPanel = new VerticalPanel();
		vPanel.add(root);
		panelMenuUsuario.setStyleName("panelMenuUsuario");
		panelMenuUsuario.add(vPanel);
		panelMenuUsuario.setCellHorizontalAlignment(vPanel, HasHorizontalAlignment.ALIGN_CENTER);
		panelMenuUsuario.setSpacing(5);
		
		initWidget(panelMenuUsuario);
		
	}

	
	public void onTreeItemSelected(TreeItem item) {
		for (Iterator<TreeItem> it = root.treeItemIterator(); it.hasNext();) {
			TreeItem obj = it.next();
			obj.removeStyleName("menuSelecionado");
		}
		if (item == item.getTree().getItem(1)) {
			item.setStyleName("menuSelecionado");
			janelaLogado.selecionarMenu(1);
		} else if (item == item.getTree().getItem(2)) {
			item.setStyleName("menuSelecionado");
			janelaLogado.selecionarMenu(2);
		} else if (item == item.getTree().getItem(4)) {
			item.setStyleName("menuSelecionado");
			janelaLogado.selecionarMenu(4);
			//solicitacoes feitas;
		} else if (item == item.getTree().getItem(5)) {
			item.setStyleName("menuSelecionado");
			janelaLogado.selecionarMenu(5);
			//solicitacoes pendentes;
		} else if (item == item.getTree().getItem(7)) {
			item.setStyleName("menuSelecionado");
			janelaLogado.selecionarMenu(7);
		}
		
	}

	
	public void onTreeItemStateChanged(TreeItem item) {
		// TODO Auto-generated method stub
		
	}

}
