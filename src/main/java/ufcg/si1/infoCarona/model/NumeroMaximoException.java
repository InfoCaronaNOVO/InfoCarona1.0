package ufcg.si1.infoCarona.model;


public class NumeroMaximoException extends Exception {
    @Override
    public String getMessage() {
        return "Numero maximo de Id permitido";
    }
}
