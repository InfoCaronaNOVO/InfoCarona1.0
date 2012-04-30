package ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona;


public class SessaoInexistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Sessão inexistente";
    }
}
