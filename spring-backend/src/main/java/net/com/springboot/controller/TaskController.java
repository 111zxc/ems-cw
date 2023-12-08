package net.javaguides.springboot.controller;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Task;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Получить все задачи
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Создать новую задачу
    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    // Получить задачу по id
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + id));
        return ResponseEntity.ok(task);
    }

    // Обновить информацию о задаче
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + id));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setEmployees(task.getEmployees());

        Task updatedTask = taskRepository.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    // Удалить задачу
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + id));

        taskRepository.delete(task);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // Добавить сотрудника в задачу по ID
    @PostMapping("/tasks/{taskId}/employees/{employeeId}")
    public ResponseEntity<Task> addEmployeeToTask(@PathVariable Long taskId, @PathVariable Long employeeId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + taskId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + employeeId));

        task.getEmployees().add(employee);
        taskRepository.save(task);

        return ResponseEntity.ok(task);
    }

    // Удалить сотрудника из задачи по ID
    @DeleteMapping("/tasks/{taskId}/employees/{employeeId}")
    public ResponseEntity<Task> removeEmployeeFromTask(@PathVariable Long taskId, @PathVariable Long employeeId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + taskId));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + employeeId));

        task.getEmployees().remove(employee);
        taskRepository.save(task);

        return ResponseEntity.ok(task);
    }

}

