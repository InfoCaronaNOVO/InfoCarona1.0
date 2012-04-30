package ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona;


public class SessaoInvalidaException extends Exception {
	
     @Override
    public String getMessage() {
        return "Sessão inválida";
    }
}
