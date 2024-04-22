package com.thuctaptotnghiem.thuctaptotnghiep.controller;

import com.thuctaptotnghiem.thuctaptotnghiep.entity.HistoryEntity;
import com.thuctaptotnghiem.thuctaptotnghiep.model.request.HistoryRequest;
import com.thuctaptotnghiem.thuctaptotnghiep.model.response.HistoryResponse;
import com.thuctaptotnghiem.thuctaptotnghiep.service.histories.HistoryService;
import jakarta.validation.Valid;
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

    @GetMapping("/bookingId/{bookingId}")
    public HistoryEntity getHistoryByBookingId(@PathVariable long bookingId) {
        return historyService.getHistoryByBookingId(bookingId);
    }

    @PostMapping("/create")
    public HistoryEntity createHistories(@RequestBody @Valid HistoryRequest historyRequest) {
        return historyService.saveHistory(historyRequest);
    }

    @PutMapping("/update/{id}")
    public HistoryEntity updateHistory(@PathVariable long id, @RequestBody @Valid HistoryRequest historyRequest) {
        return historyService.updateHistory(id, historyRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteHistory(@PathVariable long id) {
        historyService.deleteHistory(id);
    }

}
