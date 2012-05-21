package ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona;

public class CidadeInexistenteException extends Exception {
	  @Override
	    public String getMessage() {
	        return "Cidade inexistente";
	    }
}
