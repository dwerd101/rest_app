package com.example.service;
import com.example.model.ProfileTask;
import com.example.model.ProfileTaskView;
import com.example.model.dto.ProfileTaskDto;
import com.example.repository.ProfileTaskViewDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ProfileTaskService {

    private ProfileTaskViewDetailRepository profileTaskViewRep;

    @Autowired
    public void setProfileTaskViewRep(ProfileTaskViewDetailRepository profileTaskViewRep) {
        this.profileTaskViewRep = profileTaskViewRep;
    }

    private ProfileTask toProfileTaskEntity(ProfileTaskDto profileTaskDto) {
        ProfileTask profileTask = new ProfileTask();
        profileTask.setId(profileTask.getId());
        return profileTask;
    }

    private ProfileTaskView toProfileTaskViewEntity(ProfileTaskDto profileTaskDto)  {
        ProfileTaskView profileTaskView = new ProfileTaskView();
        profileTaskView.setKey(profileTaskDto.getKey());
        profileTaskView.setComment(profileTaskDto.getComment());
        profileTaskView.setDomain(profileTaskDto.getDomain());
        profileTaskView.setFieldId(profileTaskDto.getFieldId());
        profileTaskView.setFieldName(profileTaskDto.getFieldName());
        profileTaskView.setOwnerName(profileTaskDto.getOwnerName());
        profileTaskView.setProfileTaskId(profileTaskDto.getProfileTaskId());
        profileTaskView.setTableName(profileTaskDto.getTableName());
        return profileTaskView;
    }

    private ProfileTaskDto toProfileTaskDto(ProfileTaskView profileTaskView) {
        ProfileTaskDto profileTaskDto = new ProfileTaskDto();
        profileTaskDto.setKey(profileTaskView.getKey());
        profileTaskDto.setComment(profileTaskView.getComment());
        profileTaskDto.setDomain(profileTaskView.getDomain());
        profileTaskDto.setFieldId(profileTaskView.getFieldId());
        profileTaskDto.setFieldName(profileTaskView.getFieldName());
        profileTaskDto.setOwnerName(profileTaskView.getOwnerName());
        profileTaskDto.setProfileTaskId(profileTaskView.getProfileTaskId());
        profileTaskDto.setTableName(profileTaskView.getTableName());
        return profileTaskDto;
    }

    public Page<ProfileTaskDto> findAllByProfileTaskId(Long id, Pageable pageable) {

        return profileTaskViewRep.findAllByProfileTaskId(id, pageable).map(this::toProfileTaskDto);
    }



    public Page<ProfileTaskDto> findBySearch(Long id, Pageable pageable) {
        return profileTaskViewRep.findAllByProfileTaskId(id,pageable).map(this::toProfileTaskDto);
    }


    public Page<ProfileTaskDto> findBySearch(Long id, String parameter, Pageable pageable) {
        if(!parameter.contains("Таблица"))
           return profileTaskViewRep.findAllByProfileTaskIdAndDomain(id,parameter,pageable)
                   .map(this::toProfileTaskDto);
        else return profileTaskViewRep.findAllByProfileTaskIdAndTableName(id,parameter,pageable)
                .map(this::toProfileTaskDto);
    }


    public Page<ProfileTaskDto> findBySearch(Long id, String parameter, String parameter2, Pageable pageable) {
        return profileTaskViewRep.findAllByProfileTaskIdAndDomainAndTableName(id,parameter,parameter2,pageable)
                .map(this::toProfileTaskDto);
    }

}
