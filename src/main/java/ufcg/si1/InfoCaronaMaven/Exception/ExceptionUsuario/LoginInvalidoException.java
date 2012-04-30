package ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario;


public class LoginInvalidoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Login inválido";
    }
}
