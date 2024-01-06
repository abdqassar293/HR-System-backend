package com.senior.hr.mapper;

import com.senior.hr.DTO.ManagerDTO;
import com.senior.hr.model.Manager;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class ManagerMapper {
    public Manager managerDTOToManager(ManagerDTO managerDTO) {
        Manager manager = new Manager();
        manager.setFirstName(managerDTO.getFirstName());
        manager.setLastName(managerDTO.getLastName());
        manager.setUsername(managerDTO.getUsername());
        manager.setPassword(managerDTO.getPassword());
        manager.setDegree(managerDTO.getDegree());
        manager.setNumber(managerDTO.getNumber());
        manager.setFatherName(managerDTO.getFatherName());
        manager.setResidence(managerDTO.getResidence());
        manager.setSsn(managerDTO.getSsn());
        if (manager.getDateOfBirth() != null) {
            manager.setDateOfBirth(Date.valueOf(managerDTO.getDateOfBirth()));
        }
        manager.setMotherName(managerDTO.getMotherName());
        return manager;
    }

    public ManagerDTO managerToManagerDTO(Manager manager) {
        ManagerDTO managerDTO = new ManagerDTO();
        managerDTO.setId(manager.getId());
        managerDTO.setUsername(manager.getUsername());
        managerDTO.setFirstName(manager.getFirstName());
        managerDTO.setLastName(manager.getLastName());
        managerDTO.setSsn(manager.getSsn());
        managerDTO.setPlaceOfBirth(manager.getPlaceOfBirth());
        managerDTO.setDegree(manager.getDegree());
        managerDTO.setNumber(manager.getNumber());
        Date date = manager.getDateOfBirth();
        if (date != null) {
            managerDTO.setDateOfBirth(date.toString());
        }
        managerDTO.setResidence(manager.getResidence());
        return managerDTO;
    }
}
