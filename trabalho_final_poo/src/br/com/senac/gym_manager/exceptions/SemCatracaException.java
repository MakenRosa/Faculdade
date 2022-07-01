
package br.com.senac.gym_manager.exceptions;

public class SemCatracaException extends Exception {
    public SemCatracaException(String message){
        super(message);
    }
    public SemCatracaException(Throwable t){
        super(t);
    }

}
