package ufcg.si1.InfoCaronaMaven.Sistema;

public enum TiposCarona {
	COMUM("Carona Comum"),
	MUNICIPAL("Carona Municipal");
	
	private String nomeTipo;
	TiposCarona(String nomeTipo){
		this.nomeTipo = nomeTipo;
	}
	public String getNomeTipo() {
		return nomeTipo;
	}
	
}
