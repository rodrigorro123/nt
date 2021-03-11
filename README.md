# NT - api de votação
### Avaliação técnica back-end V1

- ##### Para consulta e teste via heroku, pode-se utilizar os link's abaixo
    https://nt-votacao.herokuapp.com/

- ##### Para testar a api, pode-se utilizar o swagger disponivel pelo link 
    https://nt-votacao.herokuapp.com/swagger-ui.html#

- ##### Estrutura utilizada para criação da api
    * Versao java: 1.8 
    * Banco de dados: Mysql
    * Acesso manipulacao de dados: utilizado o padrao JPA
    * Mensageira: Heroku Rabbit 
    * Consumo de Api's externa: Feign
    * Geracao de swagger da api: springfox-swagger
    * Biblioteca de geração de codigo: Lombok
    
* ## Schedule
    *   Criado um schedule para envio para uma fila(cloudmq - Heroku) das sessoes finalizadas, 
        *   OBS porem não foi feita a leitura da fila, pois não ficou claro para quem deveria notificar ou ação a gerenciar.

