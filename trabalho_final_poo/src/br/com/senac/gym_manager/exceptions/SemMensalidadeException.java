
package br.com.senac.gym_manager.exceptions;

public class SemMensalidadeException extends Exception {
    public SemMensalidadeException(String message){
        super(message);
    }
    public SemMensalidadeException(Throwable t){
        super(t);
    }

}
