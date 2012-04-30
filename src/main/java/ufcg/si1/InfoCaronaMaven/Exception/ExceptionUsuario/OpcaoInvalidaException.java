package ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario;


public class OpcaoInvalidaException extends Exception {
	
     @Override
    public String getMessage() {
        return "Opção inválida.";
    }
}
