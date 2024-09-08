package com.restcall.caller.controller;

import com.restcall.caller.dto.RequestDto;
import com.restcall.caller.service.RestCallerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/caller")
@RequiredArgsConstructor
public class RestCallerController {

    private final RestCallerService restCallerService;

    @PostMapping(value = "/call")
    public ResponseEntity<?> doRestCall (@RequestBody RequestDto requestDto) {
        return new ResponseEntity<>(restCallerService.call(requestDto, String.class), HttpStatus.OK);
    }

    @GetMapping(value = "/get-call/{message}/{priority}")
    public ResponseEntity<?> doGetRestCall(@PathVariable String message, @PathVariable String priority) {
        return new ResponseEntity<>(restCallerService.getCall(message, priority), HttpStatus.OK);
    }
}
