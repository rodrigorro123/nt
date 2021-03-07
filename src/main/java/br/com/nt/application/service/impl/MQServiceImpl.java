package br.com.nt.application.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import br.com.nt.application.exception.ApiException;
import br.com.nt.application.service.MQService;
import br.com.nt.infra.stream.ChannelStreams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class MQServiceImpl implements MQService{
	
	private final ChannelStreams channelStreams;
	
@Override
public boolean sendQueue(Long idSessao) throws ApiException {
	try {
        final Message message = MessageBuilder.withPayload(idSessao).build();

        boolean success = channelStreams.votacaoOutput().send(message);

        log.info("Envio para fila: " + idSessao.toString());

        return success;
	} catch (Exception e) {
		log.info("Erro ao enviar para fila- " + e);
        throw ApiException.builder()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .code(ApiException.GENERAL_ERROR)
        .message("Erro ao enviar para fila")
        .build();
		}
	}
}


