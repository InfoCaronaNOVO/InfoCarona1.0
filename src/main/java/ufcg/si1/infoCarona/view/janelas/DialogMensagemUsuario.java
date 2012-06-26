package ufcg.si1.infoCarona.view.janelas;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DialogMensagemUsuario extends DialogBox {
	
	public DialogMensagemUsuario(String titulo, String mensagem) {
		DialogMensagemUsuario.this.setAnimationEnabled(true);
		DialogMensagemUsuario.this.setGlassEnabled(true);
		DialogMensagemUsuario.this.center();
		
		setText(titulo);
		VerticalPanel panelDialogMensagem = new VerticalPanel();
		panelDialogMensagem.setWidth("100%");
		panelDialogMensagem.setSpacing(4);
		Label labelmensagem = new Label(mensagem);
		HTML htmlLinha = new HTML("<hr>");
		Button botaoFechar = new Button("Fechar");
		botaoFechar.setStyleName("botaoModificado");
		botaoFechar.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				DialogMensagemUsuario.this.hide();
			
			}
		});
		panelDialogMensagem.add(labelmensagem);
		panelDialogMensagem.add(htmlLinha);
		panelDialogMensagem.add(botaoFechar);
		panelDialogMensagem.setCellHorizontalAlignment(labelmensagem, HasHorizontalAlignment.ALIGN_CENTER);
		panelDialogMensagem.setCellHorizontalAlignment(botaoFechar, HasHorizontalAlignment.ALIGN_CENTER);
		panelDialogMensagem.setCellHorizontalAlignment(htmlLinha, HasHorizontalAlignment.ALIGN_CENTER);
		panelDialogMensagem.setCellVerticalAlignment(htmlLinha, HasVerticalAlignment.ALIGN_MIDDLE);
		panelDialogMensagem.setCellVerticalAlignment(labelmensagem, HasVerticalAlignment.ALIGN_MIDDLE);
		panelDialogMensagem.setCellVerticalAlignment(botaoFechar, HasVerticalAlignment.ALIGN_MIDDLE);
		setWidget(panelDialogMensagem);	
	}

}
