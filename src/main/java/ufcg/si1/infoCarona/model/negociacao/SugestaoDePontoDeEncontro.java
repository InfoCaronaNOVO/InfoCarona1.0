package ufcg.si1.infoCarona.model.negociacao;

import java.util.LinkedList;
import java.util.List;

import ufcg.si1.infoCarona.model.usuario.Usuario;

public class SugestaoDePontoDeEncontro {
	
	private String idSugestao;
	private List<String> listaDeSugestaoDePontosDeEncontro;
	private List<String> listaDeRespostasDePontosDeEncontro;
	private Usuario usuarioQueSugeriu;
	
	public SugestaoDePontoDeEncontro(String idSugestao, Usuario usuarioQueSugeriu) {
		this.usuarioQueSugeriu = usuarioQueSugeriu;
		this.idSugestao = idSugestao;
		this.listaDeSugestaoDePontosDeEncontro = new LinkedList<String>();
		this.listaDeRespostasDePontosDeEncontro = new LinkedList<String>();
	}
	

	public String getIdSugestao(){
		return this.idSugestao;
	}
	
	public Usuario getUsuarioQueSugeriu() {
		return usuarioQueSugeriu;
	}

	/**
	 * 
	 * @return lista de sugestão de ponto de encontro
	 */
	public List<String> getListaDeSugestaoDePontosDeEncontro(){
		return this.listaDeSugestaoDePontosDeEncontro;
	}
	
	public List<String> getlistaDeRespostasDePontosDeEncontro(){
		return this.listaDeRespostasDePontosDeEncontro;
	}
	public boolean existeSugestao(String pontoSugestao){
		boolean retorno = false;
		for (String sugestao : listaDeSugestaoDePontosDeEncontro) {
			if(sugestao.equals(pontoSugestao)){
				retorno = true;
				break;
		}
		}
		return retorno;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof SugestaoDePontoDeEncontro)) {
			return false;
		}
		
		if (!(((SugestaoDePontoDeEncontro) obj).getIdSugestao().equals(this.idSugestao))){
			return false;
		}
		
		return true;
	}
}
