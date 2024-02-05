package com.senior.hr.mapper;

import com.senior.hr.DTO.ManagerDTO;
import com.senior.hr.model.Manager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ManagerMapper {
    private final BenefitMapper benefitMapper;
    /*public Manager managerDTOToManager(ManagerDTO managerDTO) {
        Manager manager = new Manager();
        manager.setFirstName(managerDTO.getFirstName());
        manager.setLastName(managerDTO.getLastName());
        manager.setUsername(managerDTO.getUsername());
        manager.setPassword(managerDTO.getPassword());
        manager.setDegree(managerDTO.getDegree());
        manager.setNumber(managerDTO.getNumber());
        manager.setFatherName(managerDTO.getFatherName());
        manager.setResidence(managerDTO.getResidence());
        manager.setContractNumber(managerDTO.getContractNumber());
        manager.setSsn(managerDTO.getSsn());
        if (manager.getDateOfBirth() != null) {
            manager.setDateOfBirth(Date.valueOf(managerDTO.getDateOfBirth()));
        }
        manager.setMotherName(managerDTO.getMotherName());
        return manager;
    }*/

    public ManagerDTO managerToManagerDTO(Manager manager) {
        ManagerDTO managerDTO = new ManagerDTO();
        managerDTO.setId(manager.getId());
        managerDTO.setUsername(manager.getUsername());
        managerDTO.setFirstName(manager.getFirstName());
        managerDTO.setLastName(manager.getLastName());
        managerDTO.setFatherName(manager.getFatherName());
        managerDTO.setMotherName(manager.getMotherName());
        managerDTO.setSsn(manager.getSsn());
        managerDTO.setPlaceOfBirth(manager.getPlaceOfBirth());
        managerDTO.setDegree(manager.getDegree());
        managerDTO.setContractNumber(manager.getContractNumber());
        managerDTO.setNumber(manager.getNumber());
        managerDTO.setSalary(manager.getSalary());
        managerDTO.setEmail(manager.getEmail());
        Date date = manager.getDateOfBirth();
        if (date != null) {
            managerDTO.setDateOfBirth(date.toString());
        }
        managerDTO.setBenefits(manager.getBenefits().stream().map(benefitMapper::benefitToBenefitDTO).collect(Collectors.toList()));
        managerDTO.setResidence(manager.getResidence());
        return managerDTO;
    }
}
