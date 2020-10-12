package com.example.repository;

import com.example.model.ProfileTaskView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileTaskViewDetailRepository {

    private ProfileTaskViewRep profileTaskViewRep;

    public Page<ProfileTaskView> findAllByProfileTaskId(Long id, Pageable pageable) {
        return profileTaskViewRep.findAllByProfileTaskId(id, pageable);
    }



   public Page<ProfileTaskView> findAllByProfileTaskIdAndDomain(Long id, String domain, Pageable page) {
        return profileTaskViewRep.findAllByProfileTaskIdAndDomain(id,domain, page);
    }
   public Page<ProfileTaskView> findAllByProfileTaskIdAndTableName(Long id, String tableName, Pageable page) {
        return profileTaskViewRep.findAllByProfileTaskIdAndTableName(id,tableName, page);
    }
   public Page<ProfileTaskView> findAllByProfileTaskIdAndDomainAndTableName(Long id, String domain, String  tableName, Pageable page) {
        return profileTaskViewRep.findAllByProfileTaskIdAndDomainAndTableName(id,domain,tableName,page);
    }
    @Autowired
    public void setProfileTaskViewRep(ProfileTaskViewRep profileTaskViewRep) {
        this.profileTaskViewRep = profileTaskViewRep;
    }
}
