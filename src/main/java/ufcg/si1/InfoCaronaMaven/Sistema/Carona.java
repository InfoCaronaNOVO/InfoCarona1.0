package ufcg.si1.InfoCaronaMaven.Sistema;

import java.util.Calendar;
import java.util.List;

public interface Carona {
	public List<String> getListaPontosDeEncontroPermitidos();
	public List<SolicitacaoDeVaga> getListaDeSolicitacao();
	public List<SugestaoDePontoDeEncontro> getListaDeSugestoes();
	
	public String getOrigem();
	
	public Usuario getDonoDaCarona();
	

	public void setOrigem(String origem) throws CaronaException;

	public String getDestino();

	public void setDestino(String destino) throws CaronaException;

	public Calendar getCalendario();
	
	public void setCalendario(Calendar calendario);

	public int getVagas();

	public void setVagas(int vagas);
	
	
	public String getIdCarona();
	
	public TiposCarona getTipoCarona();
	/**
	 * Metodo que retorna o to String das caronas
	 * @return - retorna uma String no formato "ORIGEM para DESTINO no dia XX/XX/XXXX as XX:XX
	 */
	@Override
	public String toString();
	
	public String getDadosCarona();
	public void addNovaSolicitacao(SolicitacaoDeVaga novaSolicitacao);
	
	
    
    public void removeSolicitacao(SolicitacaoDeVaga solicitacao);
    @Override
	public boolean equals(Object obj);
    
    public List<SolicitacaoDeVaga> getSolicitacoesConfirmadas();
    
    public List<SolicitacaoDeVaga> getSolicitacoesPendentes();
    
    public void addPontoEncontroPermitido(String ponto);
    
    public void addReviewCarona(Usuario usuario, String review);
}
