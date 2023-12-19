# Desafio BrickUp - Documentação Resumida

## Techs Usadas:
- **Back-end:**
  - Spring Boot
  - Hibernate
  - Jackson
  - REST
- **Banco de Dados:**
  - MySQL
- **Front-end:**
  - React
  - Ant Design (Opcional)
  - Redux
  - Redux-Saga

 ## Comando para criar a tabela e as colunas
 *(ps: Antes, garanta ter criado o banco de dados antes, e use-o)*

**Comando:**
```
CREATE TABLE tasks (
  id VARCHAR(36) NOT NULL,
  title VARCHAR(255) NOT NULL,
  completed BOOLEAN NOT NULL,
  photoPath LONGBLOB,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)
```
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
