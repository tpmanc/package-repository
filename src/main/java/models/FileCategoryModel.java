package models;

import db.Database2;
import models.helpers.CategoryFile;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.SQLException;
import java.util.*;

public class FileCategoryModel extends BaseModel implements ModelInterface {
    private int id;
    private int fileId;
    private int categoryId;


    private static final String saveNew = "INSERT INTO fileCategory(fileId, categoryId) VALUES (:fileId, :categoryId)";
    private static final String deleteById = "DELETE FROM fileCategory WHERE id = :id";
    private static final String getByFile = "SELECT category.title, category.id FROM fileCategory LEFT JOIN category ON category.id = fileCategory.categoryId WHERE fileId = :fileId";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean update() throws SQLException {
        // todo
        return false;
    }

    public static ArrayList<HashMap> findByFile(int fileId) {
        ArrayList<HashMap> result = new ArrayList<HashMap>();
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(Database2.getInstance().getBds());
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("fileId", fileId);
        List<Map<String, Object>> rows = template.queryForList(getByFile, parameters);
        for (Map row : rows) {
            HashMap<String, String> info = new HashMap<String, String>();
            String categoryId = String.valueOf(row.get("id"));
            String title = String.valueOf(row.get("title"));
            info.put("id", categoryId);
            info.put("title", title);
            result.add(info);
        }
        return result;
    }

    public boolean add() throws SQLException {
        if (validate()) {
            NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(Database2.getInstance().getBds());
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            KeyHolder keyHolder = new GeneratedKeyHolder();
            parameters.addValue("fileId", fileId);
            parameters.addValue("categoryId", categoryId);
            template.update(saveNew, parameters, keyHolder);
            this.id = keyHolder.getKey().intValue();
            return true;
        }
        return false;
    }

    public boolean validate() {
        boolean isError = false;
        if (categoryId <= 0) {
            isError = true;
        }
        if (fileId <= 0) {
            isError = true;
        }

        return isError;
    }

    public boolean delete() throws SQLException {
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(Database2.getInstance().getBds());
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        int rows = template.update(deleteById, parameters);
        return rows > 0;
    }
}
