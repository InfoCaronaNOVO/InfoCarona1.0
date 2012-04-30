package ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona;


public class TrajetoInexistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Trajeto Inexistente";
    }
}
