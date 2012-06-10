package ufcg.si1.infoCarona.view.janelas;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DialogFiltroBusca extends DialogBox{
	CheckBox checkOrigem, checkDestino;
	TextBox boxOrigem, boxDestino;
	
	public DialogFiltroBusca() {
		setText("Filtros de Busca");
		VerticalPanel panelFiltroBusca = new VerticalPanel();
		
		HorizontalPanel hPanel01 = new HorizontalPanel();
		checkOrigem = new CheckBox("Origem:");
		checkOrigem.setWidth("100px");
		boxOrigem = new TextBox();
		boxOrigem.setWidth("150px");
		boxOrigem.setEnabled(false);
		boxOrigem.setStyleName("boxModificada");
		hPanel01.add(checkOrigem);
		hPanel01.add(boxOrigem);
		hPanel01.setCellVerticalAlignment(checkOrigem, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel01.setCellVerticalAlignment(boxOrigem, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel01.setSpacing(4);
		
		HorizontalPanel hPanel02 = new HorizontalPanel();
		checkDestino = new CheckBox("Destino:");
		checkDestino.setWidth("100px");
		boxDestino = new TextBox();
		boxDestino.setEnabled(false);
		boxDestino.setWidth("150px");
		boxDestino.setStyleName("boxModificada");
		hPanel02.add(checkDestino);
		hPanel02.add(boxDestino);
		hPanel02.setCellVerticalAlignment(checkDestino, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel02.setCellVerticalAlignment(boxDestino, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel02.setSpacing(4);
			
		checkOrigem.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (checkOrigem.isChecked()) {
					boxOrigem.setEnabled(true);
				} else {
					boxOrigem.setEnabled(false);
				}
			}
		});
		checkDestino.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (checkDestino.isChecked()) {
					boxDestino.setEnabled(true);
				} else {
					boxDestino.setEnabled(false);
				}
			}
		});
		
		
		HorizontalPanel hPanel03 = new HorizontalPanel();
		hPanel03.setWidth("100%");
		Button botaoFechar = new Button("Fechar");
		botaoFechar.setStyleName("botaoModificado2");
		botaoFechar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				DialogFiltroBusca.this.hide();
				
			}
		});
		hPanel03.add(botaoFechar);
		hPanel03.setCellHorizontalAlignment(botaoFechar, HasHorizontalAlignment.ALIGN_RIGHT);
		hPanel03.setSpacing(4);
		
		DialogFiltroBusca.this.setAnimationEnabled(true);
		DialogFiltroBusca.this.setGlassEnabled(true);
		DialogFiltroBusca.this.center();
		
		panelFiltroBusca.add(hPanel01);
		panelFiltroBusca.add(hPanel02);
		panelFiltroBusca.add(hPanel03);
		panelFiltroBusca.setCellHorizontalAlignment(botaoFechar, HasHorizontalAlignment.ALIGN_CENTER);
		setWidget(panelFiltroBusca);
	}

}
