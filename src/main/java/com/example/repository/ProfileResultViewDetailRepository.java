package com.example.repository;

import com.example.model.ProfileResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfileResultViewDetailRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProfileResultView> findAllBySourceId(Long id) {
        //language=sql
        final String FIND_ALL_BY_BY_SOURCE_ID = "select profile_result.id as profile_result_id, s.name as source_name," +
                " o.name as owner_name, \"table\".name as table_name, f.field_name, profile_result.profile_task_id as profile_task_id," +
                "  profile_result.domain,  profile_result.comment,  profile_result.profile_task_id\n" +
                "from profile_result inner join profile_task pt on profile_result.profile_task_id = pt.id\n" +
                "                    join source s on pt.source_id = s.id\n" +
                "                    join field f on profile_result.field_id = f.id\n" +
                "                    join owner o on s.id = o.source_id\n" +
                "                    join \"table\" on f.table_id = \"table\".id\n" +
                "where s.name=?";
        return jdbcTemplate.query(FIND_ALL_BY_BY_SOURCE_ID,
                preparedStatement -> preparedStatement.setLong(1, id), (resultSet, i) -> {
                    Long profileResultId = resultSet.getLong(1);
                    String sourceName = resultSet.getString(2);
                    String ownerName = resultSet.getString(3);
                    String tableName = resultSet.getString(4);
                    String fieldName = resultSet.getString(5);
                    String domain = resultSet.getString(6);
                    String comment = resultSet.getString(7);
                    Long profileTaskId = resultSet.getLong(8);
                    return new ProfileResultView(profileResultId, sourceName, ownerName, tableName, fieldName, domain, comment,profileTaskId);
                });
    }

    public Page<ProfileResultView> findAllBySourceId(Long id, Pageable pageable) {

        //language=sql
        final String FIND_ALL_BY_BY_SOURCE_ID = "select profile_result.id as profile_result_id, s.name as source_name," +
                " o.name as owner_name, \"table\".name as table_name, f.field_name, profile_result.domain,  profile_result.comment,  profile_result.profile_task_id\n" +
                "from profile_result inner join profile_task pt on profile_result.profile_task_id = pt.id\n" +
                "                    join source s on pt.source_id = s.id\n" +
                "                    join field f on profile_result.field_id = f.id\n" +
                "                    join owner o on s.id = o.source_id\n" +
                "                    join \"table\" on f.table_id = \"table\".id\n" +
                "where s.name=?"+
                "LIMIT " + pageable.getPageSize() + " " +
                "OFFSET " + pageable.getOffset();

        return getProfileResultViewList(id,pageable,FIND_ALL_BY_BY_SOURCE_ID);
    }


    private Page<ProfileResultView> getProfileResultViewList(Long id, Pageable pageable, String sqlQuery) {
        List<ProfileResultView> profileResultViewList = jdbcTemplate.query(sqlQuery, preparedStatement ->
                        preparedStatement.setLong(1, id),
                (resultSet, i) -> {
                    Long profileResultId = resultSet.getLong(1);
                    String sourceName = resultSet.getString(2);
                    String ownerName = resultSet.getString(3);
                    String tableName = resultSet.getString(4);
                    String fieldName = resultSet.getString(5);
                    String domain = resultSet.getString(6);
                    String comment = resultSet.getString(7);
                    Long profileTaskId = resultSet.getLong(8);
                    return new ProfileResultView(profileResultId, sourceName, ownerName, tableName, fieldName, domain, comment,profileTaskId);
                }
        );
        int total;
        if (profileResultViewList.size() == 0) total = 0;
        else total = profileResultViewList.size();
        return new PageImpl<>(profileResultViewList, pageable, total);
    }

    public Page<ProfileResultView> findAllByProfileTaskId(Long profileTaskId, Pageable pageable) {
        //language=sql
        final String FIND_ALL_BY_PROFILE_TASK_ID = "select profile_result.id as profile_result_id," +
                " s.name as source_name," +
                " o.name as owner_name, \"table\".name as table_name, f.field_name, profile_result.domain, " +
                " profile_result.comment,  profile_result.profile_task_id\n" +
                "from profile_result inner join profile_task pt on profile_result.profile_task_id = pt.id\n" +
                "join source s on pt.source_id = s.id\n" +
                "join field f on profile_result.field_id = f.id\n" +
                "join owner o on s.id = o.source_id\n" +
                "join \"table\" on f.table_id = \"table\".id\n" +
                "where profile_task_id  = ?"+
                "LIMIT " + pageable.getPageSize() + " " +
                "OFFSET " + pageable.getOffset();

        return getProfileResultViewList(profileTaskId, pageable, FIND_ALL_BY_PROFILE_TASK_ID);

    }


}
