package ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona;


public class DestinoInvalidoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Destino inválido";
    }
}
