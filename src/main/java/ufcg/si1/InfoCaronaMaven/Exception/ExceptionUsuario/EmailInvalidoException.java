package ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario;


public class EmailInvalidoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Email inválido";
    }
}
