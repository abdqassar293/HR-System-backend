package com.senior.hr.mapper;

import com.senior.hr.DTO.PreviousProjectDTO;
import com.senior.hr.model.PreviousProject;
import org.springframework.stereotype.Service;

@Service
public class PreviousProjectMapper {
    public PreviousProject previousProjectDTOTOPreviousProject(PreviousProjectDTO previousProjectDTO) {
        PreviousProject previousProject = new PreviousProject();
        previousProject.setName(previousProjectDTO.getName());
        previousProject.setCompanyName(previousProjectDTO.getCompanyName());
        previousProject.setDescription(previousProjectDTO.getDescription());
        return previousProject;
    }

    public PreviousProjectDTO previousProjectToPreviousProjectDTO(PreviousProject previousProject) {
        PreviousProjectDTO previousProjectDTO = new PreviousProjectDTO();
        previousProjectDTO.setId(previousProject.getId());
        previousProjectDTO.setName(previousProject.getName());
        previousProjectDTO.setCompanyName(previousProject.getCompanyName());
        previousProjectDTO.setDescription(previousProject.getDescription());
        return previousProjectDTO;
    }
}
