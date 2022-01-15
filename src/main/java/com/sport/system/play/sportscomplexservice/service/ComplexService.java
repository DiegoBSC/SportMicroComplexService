package com.sport.system.play.sportscomplexservice.service;

import com.sport.system.play.sportscomplexservice.presenter.ComplexPresenter;
import com.sport.system.play.sportscomplexservice.presenter.Paginator;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ComplexService {

    Paginator findAllByIdCompany(String idCompany, Integer page, Integer size);
    ComplexPresenter save(ComplexPresenter presenter);
    ComplexPresenter update(ComplexPresenter presenter) throws Exception;
    void delete(String idComplex) throws Exception;
    ComplexPresenter updateLogo(MultipartFile file, String idComplex) throws IOException;
}
