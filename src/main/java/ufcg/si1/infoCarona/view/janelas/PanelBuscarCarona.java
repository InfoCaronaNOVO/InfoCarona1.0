package ufcg.si1.infoCarona.view.janelas;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;

public class PanelBuscarCarona extends Composite {

	private static List<CaronaInfo> listaCaronas;
	
	public PanelBuscarCarona() {
		listaCaronas = new ArrayList<CaronaInfo>();
		
		VerticalPanel panelBuscarCarona = new VerticalPanel();
		panelBuscarCarona.setWidth("100%");		
		//tabela para colocar as caronas
	    final CellTable<CaronaInfo> tabelaCaronas = new CellTable<CaronaInfo>();
	    //pager para passar as paginas da tabela
	    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
	    SimplePager pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
	    pager.setDisplay(tabelaCaronas);
	    pager.setPageSize(10);

	    
	    //coluna selecao
	    final MultiSelectionModel<CaronaInfo> selectionModel = new MultiSelectionModel<CaronaInfo>();
		tabelaCaronas.setSelectionModel(selectionModel,DefaultSelectionEventManager.<CaronaInfo> createCheckboxManager());
		Column<CaronaInfo, Boolean> colunaCheck = new Column<CaronaInfo, Boolean>( new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(CaronaInfo object) {
				return selectionModel.isSelected(object);
			}
		};
		//coluna motorista
	    TextColumn<CaronaInfo> colunaMotorista = new TextColumn<CaronaInfo>() {
	      @Override
	      public String getValue(CaronaInfo carona) {
	        return carona.getMotorista();
	      }
	    };
	    
	    //coluna origem
	    TextColumn<CaronaInfo> colunaOrigem = new TextColumn<CaronaInfo>() {
		      @Override
		      public String getValue(CaronaInfo carona) {
		        return carona.getOrigem();
		      }
		};
		
		//coluna destino
		TextColumn<CaronaInfo> colunaDestino = new TextColumn<CaronaInfo>() {
		      @Override
		      public String getValue(CaronaInfo carona) {
		        return carona.getDestino();
		      }
		};
		
		//coluna data
		TextColumn<CaronaInfo> colunaData = new TextColumn<CaronaInfo>() {
		      @Override
		      public String getValue(CaronaInfo carona) {
		        return carona.getData();
		      }
		};
		
		//coluna hora
		TextColumn<CaronaInfo> colunaHora = new TextColumn<CaronaInfo>() {
		      @Override
		      public String getValue(CaronaInfo carona) {
		        return carona.getHora();
		      }
		};

		//coluna vagas
		TextColumn<CaronaInfo> colunaVagas = new TextColumn<CaronaInfo>() {
		      @Override
		      public String getValue(CaronaInfo carona) {
		        return carona.getVagas();
		      }
		};
		
		
	    //adicionando caronas na lista de caronas
		for (int i = 1; i < 37; i++) {
			listaCaronas.add(new CaronaInfo("Carona "+i, "Campina Grande", "João Pessoa","06/01/2013","14:00","2"));
		}
		//
		
		tabelaCaronas.addColumn(colunaCheck);
	    tabelaCaronas.addColumn(colunaMotorista,"Motorista");
	    tabelaCaronas.addColumn(colunaOrigem,"Origem");
	    tabelaCaronas.addColumn(colunaDestino,"Destino");
	    tabelaCaronas.addColumn(colunaData,"Data");
	    tabelaCaronas.addColumn(colunaHora,"Hora");
	    tabelaCaronas.addColumn(colunaVagas,"Vagas");
	    colunaData.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	    colunaHora.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	    colunaVagas.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	    	    
	    tabelaCaronas.setRowCount(listaCaronas.size(), true);
	    tabelaCaronas.setRowData(0, listaCaronas);
	    	    
	    ListDataProvider<CaronaInfo> dataProvider = new ListDataProvider<CaronaInfo>();
	    dataProvider.setList(listaCaronas);  
	    dataProvider.addDataDisplay(tabelaCaronas);


		//panel com pesquisa de carona, filtro, sugerirPontoEncontro e solicitarVagaPontoEncontro
		HorizontalPanel hPanel01 = new HorizontalPanel();
		final DialogFiltroBusca dialogFiltro = new DialogFiltroBusca();
		final TextBox boxEfetuarBusca = new TextBox();
		boxEfetuarBusca.setStyleName("boxModificada");
		boxEfetuarBusca.setWidth("200px");
		
		Button botaoEfetuarBusca = new Button("");
		botaoEfetuarBusca.setTitle("Efetuar Busca");
		botaoEfetuarBusca.setStyleName("botaoModificado");
		Image imagemBusca = new Image("imagens/busca01.png");
		imagemBusca.setSize("15px", "15px");
		botaoEfetuarBusca.getElement().appendChild(imagemBusca.getElement());
		botaoEfetuarBusca.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				List<CaronaInfo> listaCaronasEncontradas = buscaCarona(listaCaronas, boxEfetuarBusca.getText());
				tabelaCaronas.setRowCount(listaCaronasEncontradas.size(), true);
				tabelaCaronas.setRowData(0, listaCaronasEncontradas);
				ListDataProvider<CaronaInfo> dataProvider = new ListDataProvider<CaronaInfo>();
				dataProvider.setList(listaCaronasEncontradas);  
				dataProvider.addDataDisplay(tabelaCaronas);
			}
		});
		
		
		
		
		Button botaoCriarFiltro = new Button("");
		botaoCriarFiltro.setTitle("Configurar Filtros");
		botaoCriarFiltro.setStyleName("botaoModificado");
		Image imageFiltro = new Image("imagens/filtro01.png");
		imageFiltro.setSize("15px", "15px");
		botaoCriarFiltro.getElement().appendChild(imageFiltro.getElement());
		
		botaoCriarFiltro.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				dialogFiltro.show();
			}
		});
		
		Button botaoSugerirPontoEncontro = new Button("Sugerir Ponto de Encontro");
		botaoSugerirPontoEncontro.setStyleName("botaoModificado");
		
		Button botaoSolicitarVagaPontoEncontro = new Button("Solicitar Vaga Ponto de Encontro");
		botaoSolicitarVagaPontoEncontro.setStyleName("botaoModificado");
		
		hPanel01.add(boxEfetuarBusca);
		hPanel01.add(botaoEfetuarBusca);
		hPanel01.add(botaoCriarFiltro);
		hPanel01.add(botaoSugerirPontoEncontro);
		hPanel01.add(botaoSolicitarVagaPontoEncontro);
		hPanel01.setCellVerticalAlignment(boxEfetuarBusca, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel01.setCellVerticalAlignment(botaoEfetuarBusca, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel01.setCellVerticalAlignment(botaoCriarFiltro, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel01.setCellVerticalAlignment(botaoSugerirPontoEncontro, HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel01.setCellHorizontalAlignment(botaoSugerirPontoEncontro, HasHorizontalAlignment.ALIGN_RIGHT);
		hPanel01.setSpacing(8);
				
		panelBuscarCarona.add(hPanel01);
		panelBuscarCarona.add(tabelaCaronas);
		panelBuscarCarona.add(pager);		
		
		panelBuscarCarona.setCellHorizontalAlignment(tabelaCaronas, HasHorizontalAlignment.ALIGN_CENTER);
		panelBuscarCarona.setCellHorizontalAlignment(pager, HasHorizontalAlignment.ALIGN_CENTER);
		panelBuscarCarona.setSpacing(8);
		
		
		initWidget(panelBuscarCarona);
	    
		
	}

	
	public List<CaronaInfo> buscaCarona(List<CaronaInfo> listaCaronas, String palavraChave) {
		List<CaronaInfo> caronasEncontradas = new ArrayList<CaronaInfo>();
		for (CaronaInfo carona : listaCaronas) {
					if(carona.toString().contains(palavraChave)) {
						caronasEncontradas.add(carona);
					}
		}
		return caronasEncontradas;
		
	}
}
