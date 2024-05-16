package com.example.marketmanagementapi.controller;

import com.example.marketmanagementapi.model.dto.MercadoDto;
import com.example.marketmanagementapi.service.MercadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mercado")
public class MercadoController {

    private MercadoService mercadoService;

    public MercadoController(MercadoService mercadoService) {
        this.mercadoService = mercadoService;
    }

    @PostMapping("/add-mercado")
    public ResponseEntity<?> addMercado(@RequestBody MercadoDto newMercado){
        return new ResponseEntity<>(mercadoService.createMercado(newMercado), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMercadoById(@PathVariable Long id){
        return new ResponseEntity<>(mercadoService.getMercado(id), HttpStatus.OK);
    }
    @GetMapping("/get-all-mercados")
    public ResponseEntity<?> getMercados(){
        return new ResponseEntity<>(mercadoService.getAllMercados(), HttpStatus.OK);
    }

    @PostMapping("delete/{id}")
    public ResponseEntity<?> deleteMercado(@PathVariable Long id){
        return new ResponseEntity<>(mercadoService.deleteMercado(id), HttpStatus.OK);
    }
    @PostMapping("/add-new-comitente-to-mercado/{idComitente}/{idMercado}")
    public ResponseEntity<?> addComitenteToMercado(@PathVariable Long idComitente, @PathVariable Long idMercado){
        return new ResponseEntity<>(mercadoService.addComitenteToMercado(idComitente, idMercado), HttpStatus.OK);
    }
    @PostMapping("delete-comitente-from-mercado/{idComitente}/{idMercado}")
    public ResponseEntity<?> deleteComitenteFromMercado(@PathVariable Long idComitente, @PathVariable Long idMercado){
        return new ResponseEntity<>(mercadoService.deleteComitenteFromMercado(idComitente, idMercado), HttpStatus.OK);
    }

}
