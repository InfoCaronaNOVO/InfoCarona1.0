package ufcg.si1.infoCarona.view.janelas;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PanelRodape extends Composite {
	
	public PanelRodape() {
		VerticalPanel vPanelFinal01 = new VerticalPanel();
		Label labelNomeCopyright = new Label("Todos os direitos reservados.");		
		vPanelFinal01.setWidth("100%");
		labelNomeCopyright.setHeight("20px");
		vPanelFinal01.add(labelNomeCopyright);
		vPanelFinal01.setCellHorizontalAlignment(labelNomeCopyright, HasHorizontalAlignment.ALIGN_CENTER);
		vPanelFinal01.setCellVerticalAlignment(labelNomeCopyright, HasVerticalAlignment.ALIGN_MIDDLE);
		vPanelFinal01.setStyleName("panelRodape");
		
		initWidget(vPanelFinal01);
	}

}
