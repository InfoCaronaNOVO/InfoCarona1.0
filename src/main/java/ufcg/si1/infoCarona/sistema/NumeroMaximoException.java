package ufcg.si1.infoCarona.sistema;


public class NumeroMaximoException extends Exception {
	
     @Override
    public String getMessage() {
        return "Numero maximo de Id permitido";
    }
}
