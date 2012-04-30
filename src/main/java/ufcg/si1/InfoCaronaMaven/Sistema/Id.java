package ufcg.si1.InfoCaronaMaven.Sistema;

import ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario.numeroMaximoException;

public class Id {
	
	private int numeroDeDigitos;
	private int cont = 0;
	private String ultimoId;
	
	public Id(int numeroDeDigitos){
		  this.numeroDeDigitos = numeroDeDigitos;
	}
	
	public String gerarId() throws numeroMaximoException{
		String retorno = "";
		String stringCont = cont+"";
		cont++;
		
		if (stringCont.length() > numeroDeDigitos){
			throw new numeroMaximoException();
		}
		
		for (int i = stringCont.length(); i < numeroDeDigitos; i++) {
			retorno += 0;
		}
		
		ultimoId = retorno+stringCont;
		
		return (ultimoId);
	}
	
	public int getNumeroDeDigitos(){
		return numeroDeDigitos;
	}
	
	public String getUltimoId(){
		return ultimoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cont;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof Id)) {
			return false;
		}
		
		if (!(((Id) obj).getUltimoId().equals(this.ultimoId))){
			return false;
		}
		
		return true;
	}
	
	@Override
	public String toString(){
		String retorno = "O n�mero de digitos � " + this.numeroDeDigitos + " e o ultimo ID gerado foi " + this.ultimoId;
		return  retorno;
	}
}
