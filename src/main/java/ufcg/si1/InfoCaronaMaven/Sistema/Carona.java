package ufcg.si1.InfoCaronaMaven.Sistema;

import java.util.List;

import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.DataInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.DestinoInvalidoException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.HoraInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.OrigemInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.VagaInvalidaException;

public interface Carona {
	public List<String> getListaPontosDeEncontroPermitidos();
	public List<SolicitacaoDeVaga> getListaDeSolicitacao();
	public List<SugestaoDePontoDeEncontro> getListaDeSugestoes();
	
	public String getOrigem();
	
	public Usuario getDonoDaCarona();
	

	public void setOrigem(String origem) throws OrigemInvalidaException;

	public String getDestino();

	public void setDestino(String destino) throws DestinoInvalidoException;

	public String getData();

	public void setData(String data) throws DataInvalidaException;

	public String getHora();

	public void setHora(String hora) throws HoraInvalidaException;

	public int getVagas();

	public void setVagas(int vagas) throws VagaInvalidaException;
	
	
	public String getIdCarona();
	
	public TiposCarona getTipoCarona();
	/**
	 * Metodo que retorna os atributos  de uma carona
	 * @param atributo - recebe como parametro uma String q pode ser "origem", "vagas", "destino", "data"
	 * @return - retorna uma String de acordo com o atributo solicitado.
	 */
	public String getAtributo(String atributo);
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
