# testePP

Aplicacão escrita em JAVA com API rest desenvolvida no framework Jersey e implementação JSON Web Token(JWT).

INSTALAÇÃO:

Bando de dados Postgres(9.3 ou superior), Maven e servidor Apache TomCat devem estar previamente instalados.

rodar o script contido na pasta SQL para a criação das tabelas.

configurar o data source para o servidor Tomcat conectar ao banco(alterar o arquivo context detro de META-INF ou adicionar diretamente no arquivo server.xml dentro da pasta do tomcat)

entrar no diretorio root da aplicação e digitar o seguinte comando:

mvn tomcat7:deploy -Dbaseurl=<ip do servidor + porta> -Didserver=<id_do_server> -Duser=<_usuario> -Dpass=<_senha>

id do server, usuario e senha devem ser os mesmo do arquivo settings do maven
