package com.brickup.backend.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.brickup.backend.model.Task;
import com.brickup.backend.repository.TaskRepository;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable UUID taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        return task.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/tasks")
    public ResponseEntity<String> createTask(
            @RequestParam("title") String title,
            @RequestParam("completed") boolean completed,
            @RequestParam("photoPath") MultipartFile file) {
        try {
            // Obtenha os bytes do arquivo
            byte[] photoPath = file.getBytes();

            // Salve o objeto Task no banco de dados, incluindo os bytes do arquivo
            Task task = new Task(title, completed, photoPath);
            taskRepository.save(task);

            return ResponseEntity.ok("Tarefa criada com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao processar o arquivo.");
        }
    }

    
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(
            @PathVariable UUID taskId,
            @RequestParam("title") String title,
            @RequestParam("completed") boolean completed,
            @RequestParam("photoPath") MultipartFile photo) {
        try {
            Optional<Task> existingTaskOptional = taskRepository.findById(taskId);
            if (existingTaskOptional.isPresent()) {
                Task existingTask = existingTaskOptional.get();
                existingTask.setTitle(title);
                existingTask.setCompleted(completed);

                if (photo != null) {
                    byte[] photoPathBytes = photo.getBytes();
                    existingTask.setPhotoPath(photoPathBytes);
                }

                Task updatedTask = taskRepository.save(existingTask);
                return ResponseEntity.ok(updatedTask);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Task> deleteTask(@PathVariable UUID taskId) {
        Optional<Task> taskToDelete = taskRepository.findById(taskId);
        if (taskToDelete.isPresent()) {
            taskRepository.deleteById(taskId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    
    @PostMapping("/modifyStatus/{taskId}")
    public ResponseEntity<Task> modifyStatusTask(@PathVariable UUID taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCompleted(true);
            taskRepository.save(task);
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
