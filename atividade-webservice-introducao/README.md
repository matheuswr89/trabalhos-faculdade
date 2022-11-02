Atividade de contextualização da disciplina

Implemente um programa que utilize comunicação via rede e arquitetura cliente-servidor, utilizando duas
linguagens de programação diferentes (escolha livre).

No programa cliente, o usuário poderá criar turmas e adicionar alunos à mesma (apenas em memória, não é
necessário utilizar persistência). Após o cadastro, os dados serão enviados para o programa servidor, que, para
cada turma, exibirá a seguinte mensagem na tela:

```A turma {nome_turma} de {ano_turma} do curso {nome_curso} possui {qtd_alunos_turma} alunos, dos quais {qtd_alunos_matriculados_turma} estão devidamente matriculados.```

Como usar

1. Baixe o projeto

2. Execute o comando para instalar as dependências:
   `py -r requirements.txt`

3. Execute o comando para iniciar o server:
   `py main.py`

4. Navegue até <b>http://localhost:4000</b> e teste.
