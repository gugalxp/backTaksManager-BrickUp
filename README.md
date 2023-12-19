# Desafio BrickUp - Documentação Resumida

## Techs Usadas:
- **Back-end:**
  - Spring Boot
  - Hibernate
  - Jackson
  - REST
- **Banco de Dados:**
  - MySQL
 
## Endpoints do Controller

### GET /tasks
Retorna todas as tarefas cadastradas.

### GET /tasks/{taskId}
Retorna uma tarefa específica com base no ID.

### POST /tasks
Cria uma nova tarefa com título, estado de conclusão e uma foto.

### PUT /tasks/{taskId}
Atualiza uma tarefa existente com base no ID, permitindo alteração do título, estado de conclusão e foto.

### DELETE /tasks/{taskId}
Exclui uma tarefa com base no ID.

### POST /modifyStatus/{taskId}
Modifica o status de uma tarefa para concluída com base no ID.
