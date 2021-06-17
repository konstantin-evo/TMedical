package com.tsystems.javaschool.service.implementation;

import com.tsystems.javaschool.dao.api.TherapyCaseRepository;
import com.tsystems.javaschool.dao.api.TherapyRepository;
import com.tsystems.javaschool.dao.api.UserRepository;
import com.tsystems.javaschool.model.dto.TherapyCaseDto;
import com.tsystems.javaschool.model.dto.TherapyDto;
import com.tsystems.javaschool.model.dto.TreatmentDto;
import com.tsystems.javaschool.model.entity.Therapy;
import com.tsystems.javaschool.model.entity.TherapyCase;
import com.tsystems.javaschool.model.entity.Treatment;
import com.tsystems.javaschool.model.entity.enums.TherapyStatus;
import com.tsystems.javaschool.service.api.MapperService;
import com.tsystems.javaschool.service.api.TherapyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TherapyServiceImpl implements TherapyService {

    public final MapperService mapper;
    public final TherapyRepository dao;
    public final TherapyCaseRepository therapyCaseRepository;
    public final UserRepository userRepository;
    public final MessageSender messageSender;

    @Autowired
    public TherapyServiceImpl (TherapyRepository dao, TherapyCaseRepository therapyCaseRepository, UserRepository userRepository, MessageSender messageSender, MapperService mapper) {
        this.dao = dao;
        this.mapper = mapper;
        this.therapyCaseRepository = therapyCaseRepository;
        this.userRepository = userRepository;
        this.messageSender = messageSender;
    }

    @Override
    public List<TherapyDto> findByTreatmentId(int id) {
        Collection<Therapy> list = dao.findTherapyByTreatmentId(id);
        list.forEach(therapy -> changeTherapyStatus(therapy.getId()));
        return list.stream()
                .map(mapper::convertToDto)
                .sorted(Comparator.comparing(TherapyDto::getStartDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<TherapyCaseDto> findCasesByDay(String day) {
        List<TherapyCase> list = therapyCaseRepository.findTherapyCaseByDate(LocalDate.parse(day));
        return list.stream().map(mapper::converToDto).collect(Collectors.toList());
    }

    @Override
    public TherapyCaseDto findCaseById(int id) {
        TherapyCase therapyCase = therapyCaseRepository.findById(id);
        return mapper.converToDto(therapyCase);
    }

    @Override
    public TherapyDto findById(int id) {
        Therapy therapy = dao.findById(id);
        return mapper.convertToDto(therapy);
    }

    @Override
    public TreatmentDto findTreatmentByCaseId(int id){
        Treatment treatment = therapyCaseRepository.findById(id).getTherapy().getTreatment();
        return mapper.convertToDto(treatment);
    }

    @Override
    public void setCaseStatus(int id, String email, String status) {
        TherapyCase therapyCase = therapyCaseRepository.findById(id);
        therapyCase.setStatus(TherapyStatus.valueOf(status));
        therapyCase.setNurse(userRepository.findByEmail(email));
        changeTherapyStatus(therapyCase.getTherapy().getId());
        therapyCaseRepository.update(therapyCase);
    }

    @Override
    public void changeTherapyStatus(int id){
        Therapy therapy = dao.findById(id);
        List<TherapyCase> therapyCases = therapy.getTherapyCases();
        TherapyStatus status = getTherapyStatus(therapyCases);
        therapy.setStatus(status);
    }

    private TherapyStatus getTherapyStatus(List<TherapyCase> therapyCases) {

        if (therapyCases.stream().allMatch(therapyCase -> therapyCase.getStatus().toString().equals("PLANNED"))) {
            return TherapyStatus.valueOf("PLANNED");
        } else if (therapyCases.stream().allMatch(therapyCase -> therapyCase.getStatus().toString().equals("CANCELED"))) {
            return TherapyStatus.valueOf("CANCELED");
        } else if (therapyCases.stream().allMatch(therapyCase -> therapyCase.getStatus().toString().equals("DONE"))) {
            return TherapyStatus.valueOf("DONE");
        } else if ((therapyCases.stream().anyMatch(therapyCase -> therapyCase.getStatus().toString().equals("DONE")))
                    && (therapyCases.stream().noneMatch(therapyCase -> therapyCase.getStatus().toString().equals("PLANNED")))) {
            return TherapyStatus.valueOf("PARTIALLYCOMPLETED");
        } else if ((therapyCases.stream().anyMatch(therapyCase -> therapyCase.getStatus().toString().equals("PLANNED")))
                    && (therapyCases.stream().noneMatch(therapyCase -> therapyCase.getStatus().toString().equals("CANCELED")))) {
            return TherapyStatus.valueOf("INPROGRESS");
        }
        else {
            return TherapyStatus.valueOf("PLANNED");
        }
    }

    @Override
    public void deleteTherapy(int id, String email) {
        Therapy therapy = dao.findById(id);
        boolean condition = therapy.getTherapyCases().stream().
                anyMatch(therapyCase -> therapyCase.getStatus().toString().equals("DONE"));
        if (condition) {
            cancelTherapyCases(id);
        } else {
            dao.deleteTherapy(id);
        }
        sendMessageByDay(String.valueOf(LocalDate.now()));
    }

    public void sendMessageByDay(String day) {
        List<TherapyCaseDto> listDto = findCasesByDay(day);
        messageSender.sendMessage(listDto);
    }

    private void cancelTherapyCases(int id){
        List<TherapyCase> listDto = dao.findById(id).getTherapyCases();

        for (TherapyCase therapyCase : listDto) {
            if(therapyCase.getStatus().toString().equals("PLANNED")){
                therapyCase.setStatus(TherapyStatus.valueOf("CANCELED"));
            }
        }
    }

}