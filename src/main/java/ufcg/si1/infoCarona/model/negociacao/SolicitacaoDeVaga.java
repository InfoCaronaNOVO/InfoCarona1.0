package ufcg.si1.infoCarona.model.negociacao;

import ufcg.si1.infoCarona.model.carona.Carona;
import ufcg.si1.infoCarona.model.usuario.Usuario;

public class SolicitacaoDeVaga {
	
	private Carona carona;
	private Usuario donoSolicitacao;
	private String ponto, idSolicitacao;
	private EstadoSolicitacao estado;
	
	public SolicitacaoDeVaga(Carona carona, String ponto, String idSolicitacao, Usuario donoSolicitacao){
		this.carona = carona;
		this.ponto = ponto;
		this.idSolicitacao = idSolicitacao;
		this.donoSolicitacao = donoSolicitacao;
		this.estado = EstadoSolicitacao.PENDENTE;
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

	public Usuario getDonoDaCarona(){
		return this.carona.getDonoDaCarona();
	}

	public String getPonto(){
		return this.ponto;
	}
	
	public String getIdSolicitacao(){
		return this.idSolicitacao;
	}
	
	public void solicitacaoAceita(){
		this.setEstado(EstadoSolicitacao.ACEITA);
		carona.setVagas(carona.getVagas()-1);	
	}
	
	public void solicitacaoRejeitada(){
		this.setEstado(EstadoSolicitacao.REJEITADA);
	}
	
	public EstadoSolicitacao getEstado(){
		return this.estado;
	}
	
	public void setEstado(EstadoSolicitacao estado){
		this.estado = estado;
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
