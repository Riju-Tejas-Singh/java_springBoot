package com.restcall.caller.controller;

import com.restcall.caller.dto.RequestDto;
import com.restcall.caller.service.RestCallerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/caller")
@RequiredArgsConstructor
public class RestCallerController {

    private final RestCallerService restCallerService;

    @PostMapping(value = "/call")
    public ResponseEntity<?> doRestCall (@RequestBody RequestDto requestDto) {
        return new ResponseEntity<>(restCallerService.call(requestDto, String.class), HttpStatus.OK);
    }
}
