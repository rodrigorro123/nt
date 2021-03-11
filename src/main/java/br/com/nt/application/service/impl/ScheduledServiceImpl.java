package br.com.nt.application.service.impl;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.nt.application.service.MQService;
import br.com.nt.application.service.ScheduledService;
import br.com.nt.application.service.SessaoService;
import br.com.nt.domain.entity.Sessao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Service
public class ScheduledServiceImpl implements ScheduledService {

	private final MQService mq;
	private final SessaoService sessaoService;
	
    @Scheduled(fixedDelay = 60L * 1000L) // todo minuto realizacao a verificação
    //@Scheduled(fixedDelay = 30L * 60L * 1000L) // a cada 1/2hora realizacao a verificação
    public void resendInvoice() {
        try {
        	List<Sessao> finalizadas =  sessaoService.identificaSessaoFinalizada();
        	
        	//envia para a fila as sessoes finalizadas e atualiza na base
        	for (Sessao finalizada: finalizadas) {
				
        		if(mq.sendQueue(finalizada.getId() ) ) {
        			finalizada.setFlgAtivo("NAO");
					sessaoService.atualizaSessaoFinalizada(finalizada);
				}
			}
        
        } catch (Exception e) {
            log.error("Erro ao enviar para a fila no processo de finalizacao: " + e.getMessage(), e);
        }
    }
}
