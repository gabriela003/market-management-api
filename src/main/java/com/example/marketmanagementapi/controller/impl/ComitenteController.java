package com.example.marketmanagementapi.controller.impl;

import com.example.marketmanagementapi.model.dto.ComitenteDto;
import com.example.marketmanagementapi.service.ComitenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comitente")
public class ComitenteController {

    private ComitenteService comitenteService;

    public ComitenteController(ComitenteService comitenteService) {
        this.comitenteService = comitenteService;
    }

    @PostMapping("/add-comitente")
    public ResponseEntity<?> addComitente(@RequestBody ComitenteDto newComitente){
        return new ResponseEntity<>(comitenteService.createComitente(newComitente),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getComitenteById(@PathVariable Long id){
        return new ResponseEntity<>(comitenteService.getComitente(id), HttpStatus.OK);
    }
    @GetMapping("/get-all-comitentes")
    public ResponseEntity<?> getComitentes(){
        return new ResponseEntity<>(comitenteService.getAllComitentes(), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteComitente(@PathVariable Long id){
        return new ResponseEntity<>(comitenteService.deleteComitente(id), HttpStatus.OK);
    }



}
