package net.javaguides.springboot.controller;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.model.Rank;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.repository.RankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class RankController {

    @Autowired
    private RankRepository rankRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    // Получить все должности
    @GetMapping("/ranks")
    public List<Rank> getAllRanks() {
        return rankRepository.findAll();
    }

    // Создать новую должность
    @PostMapping("/ranks")
    public Rank createRank(@RequestBody Rank rank) {
        return rankRepository.save(rank);
    }

    // Получить должность по id
    @GetMapping("/ranks/{id}")
    public ResponseEntity<Rank> getRankById(@PathVariable Long id) {
        Rank rank = rankRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rank not exist with id :" + id));
        return ResponseEntity.ok(rank);
    }

    // Обновить информацию о должности
    @PutMapping("/ranks/{id}")
    public ResponseEntity<Rank> updateRank(@PathVariable Long id, @RequestBody Rank rankDetails) {
        Rank rank = rankRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rank not exist with id :" + id));

        rank.setTitle(rankDetails.getTitle());

        Rank updatedRank = rankRepository.save(rank);
        return ResponseEntity.ok(updatedRank);
    }

    // Удалить должность
    @DeleteMapping("/ranks/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRank(@PathVariable Long id) {
        Rank rank = rankRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rank not exist with id :" + id));

        rankRepository.delete(rank);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // Получить всех сотрудников по должности (рангу) по ее идентификатору
    @GetMapping("/ranks/{id}/employees")
    public List<Employee> getAllEmployeesByRankId(@PathVariable Long id) {
        Rank rank = rankRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rank not exist with id :" + id));

        return employeeRepository.findByRankId(id);
    }
}

