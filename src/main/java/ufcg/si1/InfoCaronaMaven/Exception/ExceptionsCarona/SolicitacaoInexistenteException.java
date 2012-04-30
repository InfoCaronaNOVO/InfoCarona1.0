package ufcg.si1.InfoCaronaMaven.Exception.ExceptionsCarona;


public class SolicitacaoInexistenteException extends Exception {
	
     @Override
    public String getMessage() {
        return "Solicitação inexistente";
    }
}
