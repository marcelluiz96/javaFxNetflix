Professor, n�o consegui gerar um JAR execut�vel. Ele n�o abre nem a pau. Mas estou enviando o c�digo em anexo.
As depend�ncias est�o no pom.xml, e s�o as bibliotecas mais comuns pra projetos assim 
(junit, postgres e hibernate-core)

Estou enviando junto com a atividade um SQL que gera o banco exatamente como estamos usando.

Em anexo tamb�m segue um documento mostrando, em verde, o que foi feito. Em vermelho, o que n�o foi feito,
e em laranja, o que est� pronto em termos de base para tanto, mas n�o ficaram conclu�das.

Ex laranja: Editar filme est� atualizando o nome do filme no BD, mas por quest�o de prioridade, n�o est� atualizando o resto,
pois pulamos para a funcionalidade seguinte (ciente que ela ir� funcionar ao setar o resto dos campos no update).

O username do banco est� no hibernate.cfg.xml (user: postgres pass: admin).

O username que estou utilizando � o: Login - a senha - s

IMPORTANTE: Trabalhamos bastante na base do c�digo, Daos gen�ricos, entidades, etc. Ainda n�o chegamos ao ponto
de tratar erros, ou exibir dialogs como "Opera��o conclu�da!" ou "Erro!" em praticamente todas as telas. Mas o c�digo
j� est� prevendo e tratando erros, em geral. Mas n�o estamos enviando informa��es para a view(ainda!)

