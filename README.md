# NT - api de votação
### Avaliação técnica back-end V1

- ##### Para consulta e teste via heroku, pode-se utilizar os link's abaixo
    https://nt-votacao.herokuapp.com/

- ##### Para testar a api via heroku, pode-se utilizar o swagger disponivel pelo link 
    https://nt-votacao.herokuapp.com/swagger-ui.html#

- ##### Estrutura utilizada para criação da api
    * Versao java: 1.8 
    * Banco de dados: Mysql-Heroku
    * Acesso manipulacao de dados: utilizado o padrao JPA
    * Mensageira: Heroku Rabbit 
    * Consumo de Api's externa: Feign
    * Geracao de swagger da api: springfox-swagger
    * Biblioteca de geração de codigo: Lombok
    
* ### Schedule
    *   Criado um schedule para envio para uma fila(cloudmq - Heroku) das sessoes finalizadas, configurado para verificacao a cada minuto.
        *   OBS porem não foi feita a leitura da fila, pois não ficou claro para quem deveria notificar ou ação a gerenciar.

## Exemplo de Uso
* ##### Para criação da pauta, deve-se preencher o objeto Pauta para envio no body da requisição, onde o codigo de retorno 200 representa sucesso na criação.
         Modelo Objeto Pauta
          {  "descricao": "string",
            "id": 0,
            "titulo": "string" }
* Exemplo CURL pra importação
 curl -X POST "https://nt-votacao.herokuapp.com/pauta" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"descricao\": \"string\", \"id\": 0, \"titulo\": \"string\"}"

*  ##### Para criação da sessao, deve-se preencher o objeto Sessao para envio no body da requisição, onde o codigo de retorno 200 representa sucesso na criação.
         Modelo Objeto Sessao
         Para o subOjeto pauta, é necessario o preenchimento do campo ID
         caso o campo dtValidade não seja informado, sera considerado a data atual +1 minuto de tempo expiração
            {  "descricao": "string",
              "dtValidade": "2021-03-11T20:57:08.683Z",
              "id": 0,
              "pauta": {    "descricao": "string",    "id": 1,    "titulo": "string"  },
              "titulo": "string"
            }
* Exmplo CURL para importação
 curl -X POST "https://nt-votacao.herokuapp.com/sessao" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"descricao\": \"string\", \"dtValidade\": \"2021-03-11T20:57:08.683Z\", \"id\": 0, \"pauta\": { \"descricao\": \"string\", \"id\": 0, \"titulo\": \"string\" }, \"titulo\": \"string\"}"


*  ##### Para realizacao do voto, deve-se preencher o objeto Voto para envio no body da requisição, onde o codigo de retorno 200 representa sucesso na criação.
         Modelo Objeto Voto
        {
          "cpf": "string",
          "idSessao": 0,
          "voto": "SIM"
        }
* Exemplo Curl para importação
 curl -X POST "https://nt-votacao.herokuapp.com/votacao" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"cpf\": \"string\", \"idSessao\": 0, \"voto\": \"SIM\"}"

*  ##### Para realizar a apuração da votacao referente sessao desejada, deve-se preencher o parametro idSessao com no numero da sessao a ser pesquisada, onde o codigo de retorno 200 representa sucesso na criação.

* Exemplo de Curl para importação
 curl -X GET "https://nt-votacao.herokuapp.com/votacao/resultado?idSessao=1" -H "accept: */*"

---
## Codigo referente ao Response das requisições

    Code 	Description
    200	    Registro processado com sucesso
    201	    Registro criado com sucesso
    400	    Requisiçao invalida
    401	    Unauthorized
    403	    Forbidden
    404	    O recurso que você estava tentando acessar não foi encontrado
    500	    Erro interno
