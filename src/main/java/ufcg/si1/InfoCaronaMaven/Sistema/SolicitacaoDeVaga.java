package ufcg.si1.InfoCaronaMaven.Sistema;


import ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona.VagaInvalidaException;

public class SolicitacaoDeVaga {
	
	private Carona carona;
	private Usuario donoSolicitacao;
	private String ponto, idSolicitacao;
	private boolean solicitacaoAceita = false;
	private boolean solicitacaoPendente = true;
	private boolean solicitacaoRejeitada = false;
	
	public SolicitacaoDeVaga(Carona carona, String ponto, String idSolicitacao, Usuario donoSolicitacao){
		this.carona = carona;
		this.ponto = ponto;
		this.idSolicitacao = idSolicitacao;
		this.donoSolicitacao = donoSolicitacao;
	}
	
	public Usuario getDonoSolicitacao() {
		return donoSolicitacao;
	}

	public String getOrigem(){
		return this.carona.getOrigem();
	}
	
	public String getDestino(){
		return this.carona.getDestino();
	}

	public String getDonoDaCarona(){
		return this.carona.getDonoDaCarona();
	}
	

	public String getPonto(){
		return this.ponto;
	}
	
	public String getIdSolicitacao(){
		return this.idSolicitacao;
	}
	
	public String getAtributoSolicitacao(String atributo){
		String retorno = "";
		if(atributo.equals("origem")){
			retorno = this.getOrigem();
		}else if(atributo.equals("destino")){
			retorno = this.getDestino();
		}else if(atributo.equals("Dono da carona")){
			retorno = this.getDonoDaCarona();
		}else if(atributo.equals("Ponto de Encontro")){
			retorno = this.ponto;
		}
		
		return retorno;
	}
	
	public void solicitacaoAceita() throws VagaInvalidaException{
		this.solicitacaoAceita = true;
		this.solicitacaoPendente = false;
		carona.setVagas(carona.getVagas()-1);	
	}
	
	public void solicitacaoRejeitada(){
		this.solicitacaoRejeitada = true;
		this.solicitacaoPendente = false;
			
	}
	
	public boolean isSolicitacaoAceita(){
		return this.solicitacaoAceita;
	}
	
	public boolean isSolicitacaoRejeitada(){
		return this.solicitacaoRejeitada;
	}
	
	public boolean isSolicitacaoPendente(){
		return this.solicitacaoPendente;
	}
	public Carona getCarona(){
		return this.carona;
	}
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof SolicitacaoDeVaga)) {
			return false;
		}
		
		if (!(((SolicitacaoDeVaga) obj).getIdSolicitacao().equals(this.idSolicitacao))){
			return false;
		}
		
		return true;
	}
	
	
	
}
