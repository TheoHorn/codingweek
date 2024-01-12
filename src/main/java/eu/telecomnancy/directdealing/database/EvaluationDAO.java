package eu.telecomnancy.directdealing.database;

import eu.telecomnancy.directdealing.model.evaluation.Evaluation;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static eu.telecomnancy.directdealing.Main.app;

public class EvaluationDAO {
    public void save(Evaluation evaluation){
        // check if evaluation exist
        String query = "SELECT * FROM evaluation WHERE mailEvaluator = ? AND mailEvaluated = ?";
        ResultSet result = null;
        try (PreparedStatement statement = DatabaseAccess.connection.prepareStatement(query)) {
            statement.setString(1, evaluation.getMailEvaluator());
            statement.setString(2, evaluation.getMailEvaluated());
            result = statement.executeQuery();
            if (result.next()) {
                // update
                query = "UPDATE evaluation SET note = ? WHERE mailEvaluator = ? AND mailEvaluated = ?";
                try (PreparedStatement statement2 = DatabaseAccess.connection.prepareStatement(query)) {
                    statement2.setInt(1, evaluation.getNote());
                    statement2.setString(2, evaluation.getMailEvaluator());
                    statement2.setString(3, evaluation.getMailEvaluated());
                    statement2.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // insert
                query = "INSERT INTO evaluation (mailEvaluator, mailEvaluated, note) VALUES (?, ?, ?)";
                try (PreparedStatement statement2 = DatabaseAccess.connection.prepareStatement(query)) {
                    statement2.setString(1, evaluation.getMailEvaluator());
                    statement2.setString(2, evaluation.getMailEvaluated());
                    statement2.setInt(3, evaluation.getNote());
                    statement2.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Evaluation> get(String mailEvaluated) throws SQLException {
        // get all evaluations of a user
        String query = "SELECT * FROM evaluation WHERE mailEvaluated = ?";
        ResultSet result = null;
        List<Evaluation> evaluations = new ArrayList<Evaluation>();

        try(PreparedStatement statement = DatabaseAccess.connection.prepareStatement(query)){
            statement.setString(1, mailEvaluated);
            result = statement.executeQuery();
            while(result.next()){
                evaluations.add(new Evaluation(result.getString("mailEvaluator"), result.getString("mailEvaluated"), result.getInt("note")));
            }
            return evaluations;
        } catch (Exception e) {
            e.getMessage();
        }
        return evaluations;
    }

    public List<Evaluation> getEvaluator(String mailEvaluator) throws SQLException {
        // get all evaluations of a user
        String query = "SELECT * FROM evaluation WHERE mailEvaluator = ?";
        ResultSet result = null;
        List<Evaluation> evaluations = new ArrayList<Evaluation>();

        try(PreparedStatement statement = DatabaseAccess.connection.prepareStatement(query)){
            statement.setString(1, mailEvaluator);
            result = statement.executeQuery();
            while(result.next()){
                evaluations.add(new Evaluation(result.getString("mailEvaluator"), result.getString("mailEvaluated"), result.getInt("note")));
            }
            return evaluations;
        } catch (Exception e) {
            e.getMessage();
        }
        return evaluations;
    }

    public Evaluation get(String mailEvaluator, String mailEvaluated){
        // get an evaluation of a user
        String query = "SELECT * FROM evaluation WHERE mailEvaluator = ? AND mailEvaluated = ?";
        ResultSet result = null;
        Evaluation evaluation = null;

        try(PreparedStatement statement = DatabaseAccess.connection.prepareStatement(query)){
            statement.setString(1, mailEvaluator);
            statement.setString(2, mailEvaluated);
            result = statement.executeQuery();
            if(result.next()){
                evaluation = new Evaluation(result.getString("mailEvaluator"), result.getString("mailEvaluated"), result.getInt("note"));
            }
            return evaluation;
        } catch (Exception e) {
            e.getMessage();
        }
        return evaluation;
    }


    public void delete(String mailEvaluator, String mailEvaluated) throws SQLException {

        String query = "DELETE FROM EVALUATION WHERE mailEvaluator = ? AND mailEvaluated = ?;";

        PreparedStatement preparedStatement = DatabaseAccess.connection.prepareStatement(query);
        preparedStatement.setString(1, mailEvaluator);
        preparedStatement.setString(2, mailEvaluated);
        preparedStatement.execute();
    }
}
