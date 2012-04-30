package ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario;


public class EmailExistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Já existe um usuário com este email";
    }
}
