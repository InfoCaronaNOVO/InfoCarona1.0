package ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona;


public class IDCaronaInvalidoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Identificador do carona é inválido";
    }
}
