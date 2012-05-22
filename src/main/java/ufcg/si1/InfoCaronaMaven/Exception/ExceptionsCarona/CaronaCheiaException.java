package ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona;


public class CaronaCheiaException extends Exception {
	
     @Override
    public String getMessage() {
        return "Carona já está completa.";
    }
}
