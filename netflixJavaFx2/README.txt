Professor, não consegui gerar um JAR executável. Ele não abre nem a pau. Mas estou enviando o código em anexo.
As dependências estão no pom.xml, e são as bibliotecas mais comuns pra projetos assim 
(junit, postgres e hibernate-core)

Estou enviando junto com a atividade um SQL que gera o banco exatamente como estamos usando.

Em anexo também segue um documento mostrando, em verde, o que foi feito. Em vermelho, o que não foi feito,
e em laranja, o que está pronto em termos de base para tanto, mas não ficaram concluídas.

Ex laranja: Editar filme está atualizando o nome do filme no BD, mas por questão de prioridade, não está atualizando o resto,
pois pulamos para a funcionalidade seguinte (ciente que ela irá funcionar ao setar o resto dos campos no update).

O username do banco está no hibernate.cfg.xml (user: postgres pass: admin).

O username que estou utilizando é o: Login - a senha - s

IMPORTANTE: Trabalhamos bastante na base do código, Daos genéricos, entidades, etc. Ainda não chegamos ao ponto
de tratar erros, ou exibir dialogs como "Operação concluída!" ou "Erro!" em praticamente todas as telas. Mas o código
já está prevendo e tratando erros, em geral. Mas não estamos enviando informações para a view(ainda!)

