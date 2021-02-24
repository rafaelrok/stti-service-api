package br.com.serviceapi.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var campos = new ArrayList<ExceptionAtributos.Campo>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = error.getDefaultMessage();
			
			campos.add(new ExceptionAtributos.Campo(nome, mensagem));
		}
		
		var mensage =  new ExceptionAtributos();
		mensage.setStatus(status.value());
		mensage.setTitulo("Existe campos invalidos. "
				+ "Preencha os campos corretamente novamente");
		mensage.setDataHora(LocalDateTime.now());
		mensage.setCampos(campos);
		
		return super.handleExceptionInternal(ex, mensage, headers, status, request);
	}
}
