package com.example.service;

import com.example.model.ProfileResult;
import com.example.model.ProfileResultView;
import com.example.model.dto.ProfileResultDto;
import com.example.repository.ProfileResultDetailRepository;
import com.example.repository.ProfileResultViewDetailRepository;
import com.example.repository.ProfileResultViewRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileResultService {

    ProfileResultViewRep profileResultViewRep;
    ProfileResultDetailRepository profileResultDetailRepository;
    ProfileResultViewDetailRepository profileResultViewDetailRepository;

    private ProfileResult toProfileResultEntity(ProfileResultDto profileResultDto) {
        ProfileResult profileResult = new ProfileResult();
        profileResult.setId(profileResultDto.getProfileResultId());
        profileResult.setProfileTaskId(profileResult.getProfileTaskId());
        profileResult.setComment(profileResultDto.getComment());
        profileResult.setDomain(profileResultDto.getNameDomain());
        return profileResult;
    }

    private ProfileResultView toProfileResultViewEntity(ProfileResultDto profileResultDto) {
        ProfileResultView profileResultView = new ProfileResultView();
        profileResultView.setProfileResultId(profileResultDto.getProfileResultId());
        profileResultView.setProfileTaskId(profileResultDto.getProfileTaskId());
        profileResultView.setComment(profileResultDto.getComment());
        profileResultView.setNameDomain(profileResultDto.getNameDomain());
        profileResultView.setFieldName(profileResultDto.getFieldName());
        profileResultView.setSourceName(profileResultDto.getSourceName());
        profileResultView.setTablesName(profileResultDto.getTablesName());
        profileResultView.setOwnersName(profileResultDto.getOwnersName());
        return profileResultView;
    }

    private ProfileResultDto toProfileResultDto(ProfileResultView profileResultView) {
        ProfileResultDto profileResultDto = new ProfileResultDto();
        profileResultDto.setProfileResultId(profileResultView.getProfileResultId());
        profileResultDto.setProfileTaskId(profileResultView.getProfileTaskId());
        profileResultDto.setComment(profileResultView.getComment());
        profileResultDto.setNameDomain(profileResultView.getNameDomain());
        profileResultDto.setFieldName(profileResultView.getFieldName());
        profileResultDto.setSourceName(profileResultView.getSourceName());
        profileResultDto.setTablesName(profileResultView.getTablesName());
        profileResultDto.setOwnersName(profileResultView.getOwnersName());
        return profileResultDto;
    }

    @Autowired
    public void setProfileResultViewRep(ProfileResultViewRep profileResultViewRep) {
        this.profileResultViewRep = profileResultViewRep;
    }
    @Autowired
    public void setProfileResultDetailRepository(ProfileResultDetailRepository profileResultDetailRepository) {
        this.profileResultDetailRepository = profileResultDetailRepository;
    }

    @Autowired
    public void setProfileResultViewDetailRepository(ProfileResultViewDetailRepository profileResultViewDetailRepository) {
        this.profileResultViewDetailRepository = profileResultViewDetailRepository;
    }

    public void updateAll(List<ProfileResultDto> profileResultDtoList) {
       List<ProfileResult> profileResultList = profileResultDtoList.stream()
               .map(this::toProfileResultEntity)
               .collect(Collectors.toList());
        profileResultDetailRepository.updateAll(profileResultList);
    }




    public Page<ProfileResultDto> getPageByTaskId(Long id, Pageable pageable) {

      Page<ProfileResultView> profileResultViewPage = profileResultViewRep.findAllByProfileTaskId(id,pageable);
      return profileResultViewPage.map(this::toProfileResultDto);

    }

    public List<ProfileResultDto> saveAll(List<ProfileResultView> profileResultViewList) {

      return  profileResultViewRep.saveAll(profileResultViewList)
              .stream()
              .map(this::toProfileResultDto)
              .collect(Collectors.toList());
    }


    public Page<ProfileResultDto> findAll(Pageable pageable) {

        return profileResultViewRep.findAll(pageable).map(this::toProfileResultDto);
    }


    public Page<ProfileResultDto> findAllById(Long id, Pageable pageable) {

        return profileResultViewRep.findAllByProfileResultId(id,pageable).map(this::toProfileResultDto);
    }


    public List<ProfileResultDto> findAllById(Long id) {
        return profileResultViewRep.findAllByProfileResultId(id)
                .stream()
                .map(this::toProfileResultDto)
                .collect(Collectors.toList());
    }


    public List<ProfileResultDto> findAllBySourceId(Long id) {
        return profileResultViewDetailRepository.findAllBySourceId(id)
                .stream()
                .map(this::toProfileResultDto)
                .collect(Collectors.toList());
    }


    public Page<ProfileResultDto> findAllBySourceId(Long id, Pageable pageable) {
        return profileResultViewDetailRepository.findAllBySourceId(id,pageable).map(this::toProfileResultDto);
    }

    public Page<ProfileResultDto> findAllByProfileTaskId(Long id, Pageable pageable) {
        return profileResultViewDetailRepository.findAllByProfileTaskId(id,pageable).map(this::toProfileResultDto);
    }
}
