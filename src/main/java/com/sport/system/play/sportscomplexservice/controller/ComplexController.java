package com.sport.system.play.sportscomplexservice.controller;

import com.sport.system.play.sportscomplexservice.presenter.ComplexPresenter;
import com.sport.system.play.sportscomplexservice.presenter.Paginator;
import com.sport.system.play.sportscomplexservice.service.ComplexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
public class ComplexController {

    @Autowired
    ComplexService complexService;

    @GetMapping("/complex/findComplex")
    public ResponseEntity<Paginator> getAllComplex(@RequestParam Integer page,
                                        @RequestParam Integer size,
                                        @RequestParam String idCompany){
     return ResponseEntity.ok(complexService.findAllByIdCompany(idCompany, page, size));
    }

    @PostMapping("/complex/save")
    public ComplexPresenter saveComplex(@RequestBody ComplexPresenter complexPresenter){
        return complexService.save(complexPresenter);
    }

    @PutMapping("/complex/update")
    public ComplexPresenter updateComplex(@RequestBody ComplexPresenter complexPresenter) throws Exception {
        return complexService.update(complexPresenter);
    }

    @DeleteMapping("/complex/delete")
    public void deleteComplex(@RequestParam String idComplex) throws Exception {
        complexService.delete(idComplex);
    }

    @PostMapping("/complex/uploadLogo")
    public ComplexPresenter uploadFile(@RequestBody MultipartFile file, @RequestParam String IdComplex) throws IOException {
        return complexService.updateLogo(file, IdComplex);
    }

}
