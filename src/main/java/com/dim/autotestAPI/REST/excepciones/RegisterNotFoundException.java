package com.dim.autotestAPI.REST.excepciones;

public class RegisterNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public RegisterNotFoundException(Long id, String tipo) {
        // Construir el mensaje de excepción indicando que no se ha encontrado el registro específico.
		super("No se ha encontrado el " + tipo + " " + id);
	}

}