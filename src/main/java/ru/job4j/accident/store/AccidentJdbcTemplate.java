package ru.job4j.accident.store;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Set;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Accident create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into accidents (name, text, address, type_id) values (?, ?, ?, ?)",
                    new String[] { "id" }
            );
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        Integer generatedId = (Integer) keyHolder.getKey();
        accident.setId(generatedId != null ? generatedId : 0);
        Set<Rule> rules = accident.getRules();
        rules.forEach(rule -> setAccidentRules(accident, rule));
        return accident;
    }

    private void setAccidentRules(Accident accident, Rule rule) {
        jdbc.update(
                "insert into accident_rule (accident_id, rule_id) values (?, ?)",
                accident.getId(), rule.getId()
        );
    }

    public List<Accident> findAll() {
        List<Accident> accidents = jdbc.query("select id, name, text, address, type_id " +
                "from accidents", accidentRowMapper);
        accidents.forEach(this::setAttributesForAccident);
        return accidents;
    }

    public void update(Accident accident) {
        jdbc.update("update accidents set name = ?, text = ?, address = ?, type_id = ? " +
                "where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId(), accident.getId());
        jdbc.update("delete from accident_rule ar WHERE ar.accident_id = ?", accident.getId());
        accident.getRules().forEach(rule -> setAccidentRules(accident, rule));
    }

    public Accident getAccidentById(int id) {
        Accident accident = findAccidentById(id);
        setAttributesForAccident(accident);
        return accident;
    }

    private void setAttributesForAccident(Accident accident) {
        accident.setType(findTypeById(accident.getType().getId()));
        List<Rule> rules = findRulesByAccidentId(accident.getId());
        rules.forEach(rule -> accident.getRules().add(rule));
    }

    private final RowMapper<Accident> accidentRowMapper = (resultSet, rowNum) -> {
        Accident accident = new Accident();
        accident.setId(resultSet.getInt("id"));
        accident.setName(resultSet.getString("name"));
        accident.setText(resultSet.getString("text"));
        accident.setAddress(resultSet.getString("address"));
        AccidentType type = AccidentType.of(resultSet.getInt("type_id"), null);
        accident.setType(type);
        return accident;
    };

    private Accident findAccidentById(int id) {
        return jdbc.queryForObject("select id, name, text, address, type_id from accidents where id = ?", accidentRowMapper, id);
    }

    private final RowMapper<AccidentType> typeRowMapper = (resultSet, rowNum) ->
            AccidentType.of(resultSet.getInt("id"), resultSet.getString("name"));

    private AccidentType findTypeById(int typeId) {
        return jdbc.queryForObject("select id, name from types " +
                        "where id = ?",
                typeRowMapper, typeId);
    }

    private final RowMapper<Rule> ruleRowMapper = (resultSet, rowNum) ->
            Rule.of(resultSet.getInt("id"), resultSet.getString("name"));

    private List<Rule> findRulesByAccidentId(int accidentId) {
        return jdbc.query("select r.id as id, r.name as name from rules r " +
                "join accident_rule ar on r.id = ar.rule_id " +
                "join accidents a on ar.accident_id = a.id " +
                "where a.id = ?", ruleRowMapper, accidentId);
    }
}
