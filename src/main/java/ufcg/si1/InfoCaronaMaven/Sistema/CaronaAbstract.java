package ufcg.si1.InfoCaronaMaven.Sistema;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.DataInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.HoraInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.OrigemInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.SessaoInvalidaException;
import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.VagaInvalidaException;

public abstract class CaronaAbstract implements Carona{
	private String origem, destino, data, hora, idCarona;
	private int vagas;
	private List<SugestaoDePontoDeEncontro> listaDeSugestoes;
	private List<SolicitacaoDeVaga> listaDeSolicitacao;
	private List<String> listaPontosDeEncontroPermitidos;
	private Map<Usuario, String> reviewsCarona;
	private Usuario donoDaCarona;
	protected TiposCarona tipoCarona;
	/**
	 * Construtor da classe Carona
	 * @param origem - recebe uma String informando a origem da corana
	 * @param destino - recebe uma String informando o destino da carona
	 * @param data - recebe uma String no mormato xx/xx/xxxx e verifica se essa data é válida e nao aconteceu ainda.
	 * @param hora - recebe uma String no formato xx:xx informando a hora que a carona vai acontecer, e verifica se é um hora válida
	 * @param vagas - recebe um int informando o numero de vagas disponivel na carona
	 * @param idCarona - recebe uma String identifcador da carona
	 * @param donoDaCarona - recebe uma String com o login do dono da carona.
	 * @throws SessaoInvalidaException
	 * @throws OrigemInvalidaException - retorna uma exceção caso a origem passada seja null ou vazia
	 * @throws DestinoInvalidoException - retorna uma exceção caso o destino passado seja null ou vazio
	 * @throws DataInvalidaException - retorna uma exceção caso a data passada seja null, vazia ou data "passada"
	 * @throws HoraInvalidaException- retorna uma exceção caso a hora passada seja null, vazia ou data em formato inválida
	 * @throws VagaInvalidaException - retorna uma exceção caso o numero de vagsa seja negativo ou nao seja um numéro válido.
	 */
	public CaronaAbstract(String origem, String destino, String data, String hora, int vagas, String idCarona, Usuario donoDaCarona) throws SessaoInvalidaException, OrigemInvalidaException, CaronaException, DataInvalidaException, HoraInvalidaException, VagaInvalidaException {
        setOrigem(origem);
        setDestino(destino);
        setData(data);
        setHora(hora);
        setVagas(vagas);
        
        this.donoDaCarona = donoDaCarona;
        this.idCarona = idCarona;
        this.listaDeSugestoes = new LinkedList<SugestaoDePontoDeEncontro>();
        this.listaDeSolicitacao = new LinkedList<SolicitacaoDeVaga>();
        this.listaPontosDeEncontroPermitidos = new LinkedList<String>();
        reviewsCarona = new HashMap<Usuario, String>();
	}
	/**
	 * Metodo que retorna os pontos de encontro permitidos pelo dono da carona para um usuário solicitar vaga e sugerir
	 * @return - retorna uma List<String> com todos os pontos válidos
	 */
	public List<String> getListaPontosDeEncontroPermitidos() {
		return listaPontosDeEncontroPermitidos;
	}
	/**
	 * Metodo que retorna todas as solicitações de vaga que foram feitas para a carona
	 * @return - retorna uma List<SolicitacaoDeVaga> com todas as solicitações realizadas
	 */
	public List<SolicitacaoDeVaga> getListaDeSolicitacao() {
		return listaDeSolicitacao;
	}
	/**
	 * Metodo que retorna todos os objetos de sugestão de pontos de encontro feitos para a carona
	 * @return - retorna uma lista List<SugestaoDePontoDeEncontro> com todos os objetos de sugestao de pontos de encontro
	 */
	public List<SugestaoDePontoDeEncontro> getListaDeSugestoes(){
		return this.listaDeSugestoes;
	}
	
	public String getOrigem() {
		return this.origem;
	}
	
	public Usuario getDonoDaCarona(){
		return this.donoDaCarona;
	}
	
	public TiposCarona getTipoCarona(){
		return tipoCarona;
	}
	

	public void setOrigem(String origem) throws OrigemInvalidaException {
        if((origem == null) || (origem.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*")) ||(origem.trim().equals(""))){
        	throw new OrigemInvalidaException();
        }
        this.origem = origem.trim();
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) throws CaronaException {
        if ((destino == null) || (destino.matches("[\\-/.\\[_\\]()!\"+,:;<=>{|}#@$%¨&*0-9].*")) ||(destino.trim().equals(""))) {
                throw new CaronaException("Destino inválido");
        }
        this.destino = destino.trim();
	}

	public String getData() {
		return data;
	}

	public void setData(String data) throws DataInvalidaException {
        if ((data == null) || (data.trim().equals("")) || !(checaData(data))) {
                throw new DataInvalidaException();
        }
        this.data = data.trim();
}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) throws HoraInvalidaException {
        if ((hora == null) || hora.trim().equals("") || !checaHoraInvalida(hora)) {
                throw new HoraInvalidaException();
        }
        this.hora = hora.trim();
}

	public int getVagas() {
		return vagas;
	}

	public void setVagas(int vagas) throws VagaInvalidaException {
		if(vagas < 0){
			throw new VagaInvalidaException();
		}
		this.vagas = vagas;
	}
	
	
	public String getIdCarona(){
		return this.idCarona;
	}
	/**
	 * Metodo que retorna os atributos  de uma carona
	 * @param atributo - recebe como parametro uma String q pode ser "origem", "vagas", "destino", "data"
	 * @return - retorna uma String de acordo com o atributo solicitado.
	 */
	public String getAtributo(String atributo){
		String retorno = null;
		
		if(atributo.equals("origem")){
			retorno = this.origem;
		}else if(atributo.equals("vagas")){
			retorno = (this.vagas+"");
		}else if(atributo.equals("destino")){
			retorno = this.destino;
		}else if(atributo.equals("data")){
			retorno = this.data;
		}
		
		return retorno;
	}
	/**
	 * Metodo que retorna o to String das caronas
	 * @return - retorna uma String no formato "ORIGEM para DESTINO no dia XX/XX/XXXX as XX:XX
	 */
	@Override
	public String toString(){
		return (this.origem + " para " + this.destino + ", no dia " + this.data + ", as " + this.hora);
	}
	
	public String getDadosCarona(){
		return ("origem=" + origem + " destino=" + destino + " data=" + data + " hora=" + hora + " vagas=" + vagas);
	}
	public void addNovaSolicitacao(SolicitacaoDeVaga novaSolicitacao){
		listaDeSolicitacao.add(novaSolicitacao);
	}
	
	//Metodos de verificacao
    private boolean checaData(String stringData) {
            boolean retorno = true;
            Calendar data = Calendar.getInstance();
            try {
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    formato.setLenient(false);
                    data.setTime(formato.parse(stringData));
            } catch (ParseException e) {
                    retorno = false;
            }
            Calendar dataAtual = Calendar.getInstance();
            if (dataAtual.getTime().compareTo(data.getTime()) == 1) { // -1 data valida, 1 data invalida
                    retorno = false;
            }
            return retorno;
    }
    
    
    private boolean checaHoraInvalida(String hora) {
        boolean retorno = true;
        String[] listaHoraMinuto = hora.split(":");
        try {
                int horas = Integer.parseInt(listaHoraMinuto[0]);
                int minutos = Integer.parseInt(listaHoraMinuto[1]);
                if ((horas >= 24) || (minutos >= 60)) {
                        retorno = false;
                }
        } catch (Exception e) {
                retorno = false;
        }
        return retorno;
    }
    
    public void removeSolicitacao(SolicitacaoDeVaga solicitacao){
    	if(solicitacao.isSolicitacaoAceita()){
    		vagas++;
    	}
    	listaDeSolicitacao.remove(solicitacao);
    }
	
    @Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Carona)) {
			return false;
		}
		
		if (!(((Carona) obj).getIdCarona().equals(this.idCarona))){
			return false;
		}
		
		return true;
	}
    
    public List<SolicitacaoDeVaga> getSolicitacoesConfirmadas() {
		List<SolicitacaoDeVaga> retorno = new LinkedList<SolicitacaoDeVaga>();
		for (SolicitacaoDeVaga solicitacao : listaDeSolicitacao) {
			if(solicitacao.isSolicitacaoAceita()){
				retorno.add(solicitacao);
			}
		}
		return retorno;
	}
    
    public List<SolicitacaoDeVaga> getSolicitacoesPendentes(){
    	List<SolicitacaoDeVaga> retorno = new LinkedList<SolicitacaoDeVaga>();
		for (SolicitacaoDeVaga solicitacao : listaDeSolicitacao) {
			if(solicitacao.isSolicitacaoPendente()){
				retorno.add(solicitacao);
			}
		}
		return retorno;
    }
    
    public void addPontoEncontroPermitido(String ponto){
    	listaPontosDeEncontroPermitidos.add(ponto);
    }
    
    public void addReviewCarona(Usuario usuario, String review){
 	   if(review.equals("segura e tranquila")){
 		   this.donoDaCarona.setCaronasSeguras();
 	   }else if(review.equals("não funcionou")){
 		   this.donoDaCarona.setCaronasNaoFuncionaram();
 	   }
 	   
 	   reviewsCarona.put(usuario, review);
    }
}
