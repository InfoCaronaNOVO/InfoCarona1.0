package ufcg.si1.infoCarona.view.janelas;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class PanelCadastrarCarona extends Composite {
	
	public PanelCadastrarCarona() {
		VerticalPanel vPanel = new VerticalPanel();
		//label com o nome do panel
		Label labelCadastrarCarona = new Label("Cadastrar Carona");
		labelCadastrarCarona.setStyleName("labelNomeJanela");
		vPanel.add(labelCadastrarCarona);
		
		//primeira linha
		HTML htmlLinha = new HTML("<hr>");
		htmlLinha.setStyleName("labelLinha");
		vPanel.add(htmlLinha);
		//panel de origem
		HorizontalPanel hPanel01 = new HorizontalPanel();
		hPanel01.setWidth("240px");
		Label labelOrigem = new Label("Origem:");
		TextBox boxOrigem = new TextBox();
		boxOrigem.setStyleName("boxModificada");
		boxOrigem.setWidth("150px");
		hPanel01.add(labelOrigem);
		hPanel01.add(boxOrigem);
		hPanel01.setSpacing(8);
		hPanel01.setCellVerticalAlignment(labelOrigem, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel01.setCellHorizontalAlignment(labelOrigem, HasHorizontalAlignment.ALIGN_LEFT);
		hPanel01.setCellVerticalAlignment(boxOrigem, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel01.setCellHorizontalAlignment(boxOrigem, HasHorizontalAlignment.ALIGN_RIGHT);
		vPanel.add(hPanel01);		
		//panel de destino
		HorizontalPanel hPanel02 = new HorizontalPanel();
		hPanel02.setWidth("240px");
		Label labelDestino = new Label("Destino:");
		TextBox boxDestino = new TextBox();
		boxDestino.setStyleName("boxModificada");
		boxDestino.setWidth("150px");
		hPanel02.add(labelDestino);
		hPanel02.add(boxDestino);
		hPanel02.setSpacing(8);
		hPanel02.setCellVerticalAlignment(labelDestino, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel02.setCellHorizontalAlignment(labelDestino, HasHorizontalAlignment.ALIGN_LEFT);
		hPanel02.setCellVerticalAlignment(boxDestino, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel02.setCellHorizontalAlignment(boxDestino, HasHorizontalAlignment.ALIGN_RIGHT);
		vPanel.add(hPanel02);
		
		//panel com a data da carona
		HorizontalPanel hPanel03 = new HorizontalPanel();
		hPanel03.setWidth("240px");
		Label labelData = new Label("Data:");
		DateBox boxDataCarona = new DateBox();
		boxDataCarona.setFormat(new DateBox.DefaultFormat(com.google.gwt.i18n.client.DateTimeFormat.getFormat("dd/MM/yyyy")));
		boxDataCarona.getTextBox().setReadOnly(true);
		boxDataCarona.setStyleName("boxModificada");
		boxDataCarona.setWidth("150px");
		hPanel03.add(labelData);
		hPanel03.add(boxDataCarona);
		hPanel03.setSpacing(8);
		hPanel03.setCellVerticalAlignment(labelData, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel03.setCellHorizontalAlignment(labelData, HasHorizontalAlignment.ALIGN_LEFT);
		hPanel03.setCellVerticalAlignment(boxDataCarona, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel03.setCellHorizontalAlignment(boxDataCarona, HasHorizontalAlignment.ALIGN_RIGHT);
		vPanel.add(hPanel03);
		
		//panel com a hora e a quantidade de vagas
		HorizontalPanel hPanel04 = new HorizontalPanel();
		hPanel04.setWidth("240px");
		Label labelHora = new Label("Hora:");
		labelHora.setWidth("53px");
		final TextBox boxHora = new TextBox();
		boxHora.setStyleName("boxModificada");
		boxHora.setMaxLength(5);
		boxHora.setWidth("50px");	
		boxHora.addKeyboardListener(new KeyboardListenerAdapter() {
			@Override
			public void onKeyPress(Widget sender, char keyCode, int modifiers) {
				if (!Character.isDigit(keyCode)){
					((TextBox)sender).cancelKey();
				}
			}
			
			@Override
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				if (boxHora.getText().length() == 2) {
					boxHora.setText(boxHora.getText()+":");
				}
				
			}
		});
		boxHora.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				boxHora.setText("");
				
			}
		});
		Label labelVagas = new Label("Vagas:");
		ListBox listBoxVagas = new ListBox();
		for (int i = 1; i < 10; i++) {
			listBoxVagas.addItem(i+"");
		}
		listBoxVagas.setStyleName("boxModificada");
		hPanel04.add(labelHora);
		hPanel04.add(boxHora);
		hPanel04.add(labelVagas);
		hPanel04.add(listBoxVagas);
		hPanel04.setSpacing(8);
		hPanel04.setCellVerticalAlignment(labelHora, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel04.setCellHorizontalAlignment(labelHora, HasHorizontalAlignment.ALIGN_LEFT);
		hPanel04.setCellVerticalAlignment(boxHora, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel04.setCellHorizontalAlignment(boxHora, HasHorizontalAlignment.ALIGN_RIGHT);
		hPanel04.setCellVerticalAlignment(labelVagas, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel04.setCellHorizontalAlignment(labelVagas, HasHorizontalAlignment.ALIGN_RIGHT);
		hPanel04.setCellVerticalAlignment(listBoxVagas, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel04.setCellHorizontalAlignment(listBoxVagas, HasHorizontalAlignment.ALIGN_RIGHT);
		vPanel.add(hPanel04);
		//segunda linha
		HTML htmlLinha2 = new HTML("<hr>");
		htmlLinha2.setStyleName("labelLinha");
		vPanel.add(htmlLinha2);
		//carona municipal
		DisclosurePanel disclousureCaronaMunicipal = new DisclosurePanel("Carona Municipal");
		HorizontalPanel hPanel05 = new HorizontalPanel();
		Label labelCidade = new Label("Cidade:");
		TextBox boxCidade = new TextBox();
		boxCidade.setStyleName("boxModificada");
		boxCidade.setWidth("150px");
		hPanel05.add(labelCidade);
		hPanel05.add(boxCidade);
		hPanel05.setSpacing(8);
		hPanel05.setCellHorizontalAlignment(labelCidade, HasHorizontalAlignment.ALIGN_LEFT);
		hPanel05.setCellVerticalAlignment(labelCidade, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel05.setCellHorizontalAlignment(boxCidade, HasHorizontalAlignment.ALIGN_RIGHT);
		hPanel05.setCellVerticalAlignment(boxCidade, HasVerticalAlignment.ALIGN_MIDDLE);
		disclousureCaronaMunicipal.add(hPanel05);		
		vPanel.add(disclousureCaronaMunicipal);
		//botao de cadastro
		Button botaoCadastrarCarona = new Button("Cadastrar Carona");
		botaoCadastrarCarona.setStyleName("botaoModificado");
		botaoCadastrarCarona.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				//realizar cadastro da carona
				
			}
		});
		vPanel.add(botaoCadastrarCarona);
		vPanel.setCellHorizontalAlignment(botaoCadastrarCarona, HasHorizontalAlignment.ALIGN_CENTER);
		vPanel.setWidth("1px");
		
		//panel com as informacoes gerais de cadastradas
		HorizontalPanel hPanelCadastroCarona = new HorizontalPanel();
		hPanelCadastroCarona.add(vPanel);
		hPanelCadastroCarona.setCellHorizontalAlignment(vPanel, HasHorizontalAlignment.ALIGN_LEFT);
		hPanelCadastroCarona.setWidth("10%");
		hPanelCadastroCarona.addStyleName("panelCadastroCarona");
		hPanelCadastroCarona.setSpacing(8);
		initWidget(hPanelCadastroCarona);		
	}
}
