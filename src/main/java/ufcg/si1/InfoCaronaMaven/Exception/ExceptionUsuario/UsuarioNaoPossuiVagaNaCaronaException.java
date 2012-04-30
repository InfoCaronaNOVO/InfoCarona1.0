package ufcg.si1.InfoCaronaMaven.Exception.ExceptionUsuario;


public class UsuarioNaoPossuiVagaNaCaronaException extends Exception {
	
     @Override
    public String getMessage() {
        return "Usuário não possui vaga na carona.";
    }
}
