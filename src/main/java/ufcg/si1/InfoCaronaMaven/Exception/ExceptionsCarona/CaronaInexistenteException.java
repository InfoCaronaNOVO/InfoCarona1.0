package ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona;


public class CaronaInexistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Carona Inexistente";
    }
}
