package com.thuctaptotnghiem.thuctaptotnghiep.controller;

import com.thuctaptotnghiem.thuctaptotnghiep.model.response.HistoryResponse;
import com.thuctaptotnghiem.thuctaptotnghiep.service.histories.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/histories")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/")
    public List<HistoryResponse> getAllHistories() {
        return historyService.getAllHistories();
    }

    @DeleteMapping("/{id}")
    public void deleteHistory(@PathVariable long id) {
        historyService.deleteHistory(id);
    }



}
