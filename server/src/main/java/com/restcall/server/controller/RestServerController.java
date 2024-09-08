package com.restcall.server.controller;

import com.restcall.server.dto.RequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/server")
public class RestServerController {

    @PostMapping("/receive")
    public ResponseEntity<String> receiveRestCall(@RequestBody RequestDto requestDto) {
        String response = String.format("message: %s & priority: %s", requestDto.getMessage(), requestDto.getPriority());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/receive-get/{message}/{priority}")
    public ResponseEntity<RequestDto> receiveGetRestCall(@PathVariable String message, @PathVariable String priority) {
        RequestDto response = new RequestDto(message, priority);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
