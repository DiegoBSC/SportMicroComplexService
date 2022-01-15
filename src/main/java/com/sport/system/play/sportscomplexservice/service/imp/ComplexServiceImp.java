package com.sport.system.play.sportscomplexservice.service.imp;

import com.sport.system.play.sportscomplexservice.entity.Address;
import com.sport.system.play.sportscomplexservice.entity.Complex;
import com.sport.system.play.sportscomplexservice.entity.Contact;
import com.sport.system.play.sportscomplexservice.entity.Court;
import com.sport.system.play.sportscomplexservice.presenter.*;
import com.sport.system.play.sportscomplexservice.repository.ComplexRepository;
import com.sport.system.play.sportscomplexservice.service.ComplexService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ComplexServiceImp implements ComplexService {

    Logger logger = LoggerFactory.getLogger(ComplexServiceImp.class);

    @Autowired
    private ComplexRepository complexRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${routefile}")
    String folder;

    @Override
    public Paginator findAllByIdCompany(String idCompany, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Paginator paginator = new Paginator();
        Page<Complex> result;

        if (idCompany == null || idCompany.isEmpty()){
            result =  complexRepository.findAll(pageable);
        }else{
            result = complexRepository.findAllByIdCompany(idCompany, pageable);
        }

        paginator.setTotalPages(result.getTotalPages());
        paginator.setTotalElements(result.getTotalElements());
        paginator.setData(Set.of(result.get().map(this::buildComplexPresenterFromEntity).collect(Collectors.toList())));
        return paginator;
    }

    @Override
    public ComplexPresenter save(ComplexPresenter presenter) {
        Complex entity = complexRepository.save(buildComplexEntityFromPresenter(presenter));
        return buildComplexPresenterFromEntity(entity);
    }

    @Override
    public ComplexPresenter update(ComplexPresenter presenter) throws Exception {
        Complex entityDb = complexRepository.findById(presenter.getId()).orElse(null);

        if(entityDb == null){
           throw new Exception("Registro no encontrado");
        }

        Complex entity = buildComplexEntityFromPresenter(presenter);
        entity.setId(entityDb.getId());
        return buildComplexPresenterFromEntity(complexRepository.save(entity));
    }

    @Override
    public void delete(String idComplex) {
        complexRepository.deleteById(idComplex);
    }

    @Override
    public ComplexPresenter updateLogo(MultipartFile file, String idComplex) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        Complex entity = complexRepository.findById(idComplex).orElse(null);
        if (entity != null) {
            if (entity.getLogo() != null) {
                deleteImageOld(entity.getLogo());
            }
            entity.setLogo(saveFileToStorage(file));
            return buildComplexPresenterFromEntity(complexRepository.save(entity));
        }
        return null;
    }

    private String saveFileToStorage(MultipartFile file) {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(folder);
            builder.append(File.separator);
            builder.append(file.getOriginalFilename());
            byte[] fileByte = file.getBytes();
            Path path = Paths.get(builder.toString());
            Files.write(path, fileByte);
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void deleteImageOld(String pathDelete) {
        try {
            Files.delete(Path.of(pathDelete));
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }

    private ComplexPresenter buildComplexPresenterFromEntity(Complex complex) {
        return ComplexPresenter.builder()
                .id(complex.getId())
                .descriptionLarge(complex.getDescriptionLarge())
                .descriptionSmall(complex.getDescriptionSmall())
                .name(complex.getName())
                .addresses(complex.getAddresses().stream().map(
                        this::buildAddressPresenterFromEntity).collect(Collectors.toList()))
                .contacts(complex.getContacts().stream().map(
                        this::buildContactPresenterFromEntity).collect(Collectors.toList()))
                .courts(complex.getCourts().stream().map(
                        this::buildCourtPresenterFromEntity).collect(Collectors.toList()))
                .idCompany(complex.getIdCompany())
                .logo(complex.getLogo())
                .primaryColor(complex.getPrimaryColor())
                .secondaryColor(complex.getSecondaryColor())
                .build();
    }

    private ContactPresenter buildContactPresenterFromEntity(Contact contact) {
        ContactPresenter presenter = new ContactPresenter();
        modelMapper.map(contact, presenter);
        return presenter;
    }

    private AddressPresenter buildAddressPresenterFromEntity(Address address) {
        AddressPresenter presenter = new AddressPresenter();
        modelMapper.map(address, presenter);
        return presenter;
    }

    private CourtPresenter buildCourtPresenterFromEntity(Court court) {
        CourtPresenter presenter = new CourtPresenter();
        modelMapper.map(court, presenter);
        return presenter;
    }

    private Complex buildComplexEntityFromPresenter(ComplexPresenter presenter) {
        return Complex.builder()
                .id(presenter.getId())
                .descriptionLarge(presenter.getDescriptionLarge())
                .descriptionSmall(presenter.getDescriptionSmall())
                .name(presenter.getName())
                .addresses(presenter.getAddresses().stream().map(
                        this::buildAddressEntityFromPresenter).collect(Collectors.toList()))
                .contacts(presenter.getContacts().stream().map(
                        this::buildContactEntityFromPresenter).collect(Collectors.toList()))
                .courts(presenter.getCourts().stream().map(
                        this::buildCourtEntityFromPresenter).collect(Collectors.toList()))
                .idCompany(presenter.getIdCompany())
                .logo(presenter.getLogo())
                .primaryColor(presenter.getPrimaryColor())
                .secondaryColor(presenter.getSecondaryColor())
                .build();
    }

    private Contact buildContactEntityFromPresenter(ContactPresenter presenter) {
        Contact entity = new Contact();
        modelMapper.map(presenter, entity);
        return entity;
    }

    private Address buildAddressEntityFromPresenter(AddressPresenter presenter) {
        Address entity = new Address();
        modelMapper.map(presenter, entity);
        return entity;
    }

    private Court buildCourtEntityFromPresenter(CourtPresenter presenter) {
        Court entity = new Court();
        modelMapper.map(presenter, entity);
        return entity;
    }
}
