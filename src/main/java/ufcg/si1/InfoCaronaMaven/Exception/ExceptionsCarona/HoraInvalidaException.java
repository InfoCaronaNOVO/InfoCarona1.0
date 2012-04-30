package ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona;


public class HoraInvalidaException extends Exception {
	
     @Override
    public String getMessage() {
        return "Hora inválida";
    }
}
