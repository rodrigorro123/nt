package br.com.nt.infra.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ChannelStreams {

	
    final String VOTACAO_OUTPUT = "votacaoOutput";

    @Output(ChannelStreams.VOTACAO_OUTPUT)
    MessageChannel votacaoOutput();
}
