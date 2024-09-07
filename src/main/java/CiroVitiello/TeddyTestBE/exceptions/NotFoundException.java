package CiroVitiello.TeddyTestBE.exceptions;

public class NotFoundException extends  RuntimeException{

     public NotFoundException(int id) {
        super("elemento con " + id + " non trovato");

    }

    public NotFoundException(String message) {
        super(message);
    }
}
