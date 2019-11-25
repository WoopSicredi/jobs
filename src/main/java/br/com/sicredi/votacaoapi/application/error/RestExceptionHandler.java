package br.com.sicredi.votacaoapi.application.error;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Ruddy Paz
 */
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleBusinessException(BusinessException businessException) {
		return new ResponseEntity<>(construitDetalhesDoErro(MensagemValidacaoVotacaoEnum.BAD_REQUEST, HttpStatus.BAD_REQUEST, businessException), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<?> handleResourceNotFoundException(RecursoNaoEncontradoException rfnException) {
		return new ResponseEntity<>(construitDetalhesDoErro(MensagemValidacaoVotacaoEnum.RECURSO_NAO_ENCONTRADO, HttpStatus.NOT_FOUND, rfnException), HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException manvException, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String excecaoMensagemValidacaoCampoErro = MensagemValidacaoVotacaoEnum.ERRO_VALIDACAO_CAMPO.getMessage();
		List<FieldError> erroDosCampos = manvException.getBindingResult().getFieldErrors();
		String fields = erroDosCampos.stream().map(FieldError::getField).collect(Collectors.joining(","));
		String mensagemDeErroDosCampos = erroDosCampos.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
		
		DetalhesDoErroDeValidacao veDetails = DetalhesDoErroDeValidacao.Builder
			.newBuilder()
			.comTitulo(excecaoMensagemValidacaoCampoErro)
			.comStatus(HttpStatus.BAD_REQUEST.value())
			.comDetalhes(excecaoMensagemValidacaoCampoErro)
			.comTimestamp(new Date().getTime())
			.comMensagemParaDesenvolvedor(manvException.getClass().getName())
			.comCampo(fields)
			.comMensagemDoCampo(mensagemDeErroDosCampos)
			.constroi();
			
		return new ResponseEntity<>(veDetails, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<Object> handleExceptionInternal(
			Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>(construitDetalhesDoErro(MensagemValidacaoVotacaoEnum.EXCECAO_INTERNA, status, ex), headers, status);
	}
	
	private DetalhesDoErro construitDetalhesDoErro(MensagemValidacaoVotacaoEnum validationMessage, HttpStatus status, Exception ex) {
		return DetalhesDoErro.Builder
				.newBuilder()
				.comTitulo(validationMessage.getMessage())
				.comStatus(status.value())
				.comDetalhes(ex.getMessage())
				.comTimestamp(new Date().getTime())
				.comMensagemParaDesenvolvedor(ex.getClass().getName())
				.constroi();
	}

}
