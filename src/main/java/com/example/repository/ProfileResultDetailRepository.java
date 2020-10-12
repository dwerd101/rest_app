package com.example.repository;

import com.example.model.ProfileResult;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProfileResultDetailRepository {


    JdbcTemplate jdbcTemplate;

    //language=sql
    private static final String UPDATE_PROFILE_RESULT_QUERY_TEMPLATE =
            "UPDATE profile_result set domain=?, comment=? where id=?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void updateAll(List<ProfileResult> profileResultList) {
        jdbcTemplate.batchUpdate(UPDATE_PROFILE_RESULT_QUERY_TEMPLATE,
                new ProfileResultDetailRepository.MyBatchPreparedStatementSetterImpl(profileResultList));
    }

    @AllArgsConstructor
    public static class MyBatchPreparedStatementSetterImpl implements BatchPreparedStatementSetter {
        private final List<ProfileResult> profileResultList;

        @Override
        public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
            preparedStatement.setLong(3, profileResultList.get(i).getId());
            preparedStatement.setString(1, profileResultList.get(i).getDomain());
            preparedStatement.setString(2, profileResultList.get(i).getComment());
        }

        @Override
        public int getBatchSize() {
            return profileResultList.size();
        }
    }
}
