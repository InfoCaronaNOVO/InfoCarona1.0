package ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona;


public class SugestaoInexistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Sugestao inexistente";
    }
}
