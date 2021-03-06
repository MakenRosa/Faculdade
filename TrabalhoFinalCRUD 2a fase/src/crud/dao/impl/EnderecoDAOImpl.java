package crud.dao.impl;

import crud.dao.ConnectionManager;
import crud.dao.EnderecoDAO;
import crud.entidades.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAOImpl implements EnderecoDAO{

    @Override
    public void criar(Endereco endereco, long idPaciente) throws Exception {
        if (endereco.getLogradouro() == null){
            throw new Exception("Logradouro não pode ser nulo!");
        }
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "INSERT INTO endereco (logradouro, cep, id_paciente, numero) values(?, ?, ?, ?)";
        try {
            connection = ConnectionManager.openConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, endereco.getLogradouro());
            statement.setString(2, endereco.getCep());
            statement.setLong(3, idPaciente);
            statement.setInt(4, endereco.getNumero());
            statement.executeUpdate();
        } catch (SQLException ex){
            System.err.println("Erro ao criar Endereço! " + ex.getMessage());
        } finally {
            ConnectionManager.closeConnection(connection, statement);
        }
    }

    @Override
    public Endereco alterar(Endereco endereco) throws Exception {
        if (endereco.getLogradouro() == null){
            throw new Exception("Logradouro não pode ser nulo!");
        }
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;
        try {
            connection = ConnectionManager.openConnection();
            String sql = "UPDATE endereco SET logradouro = ?, cep = ?, numero = ? WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, endereco.getLogradouro());
            statement.setString(2, endereco.getCep());
            statement.setInt(3, endereco.getNumero());
            statement.setLong(4, endereco.getId());
            statement.executeUpdate();
        } catch (SQLException ex){
            System.err.println("Erro ao alterar Endereco: "+ ex.getMessage());
        } finally{
            ConnectionManager.closeConnection(connection, statement, resultado);
        }
        return endereco;
    }

    @Override
    public Endereco pesquisarPorId(long id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;
        Endereco endereco = null;
        try {
            connection = ConnectionManager.openConnection();
            String sql = "SELECT * FROM endereco WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            resultado = statement.executeQuery();
            if (resultado.next()){
                endereco = new Endereco();
                endereco.setId(id);
                endereco.setLogradouro(resultado.getString("logradouro"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setNumero(resultado.getInt("numero"));
            }
        } catch (SQLException ex){
            System.err.println("Erro ao pesquisar pelo id: "+ ex.getMessage());
        } finally{
            ConnectionManager.closeConnection(connection, statement, resultado);
        }
        return endereco;
    }

    @Override
    public List<Endereco> pesquisarTodosPaciente(long idPaciente) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;
        List<Endereco> enderecos = new ArrayList();
        try {
            connection = ConnectionManager.openConnection();
            String sql = "SELECT * FROM endereco WHERE id_paciente = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, idPaciente);
            resultado = statement.executeQuery();
            while (resultado.next()){
                Endereco endereco = new Endereco();
                endereco.setId(resultado.getLong("id"));
                endereco.setLogradouro(resultado.getString("logradouro"));
                endereco.setCep(resultado.getString("cep"));
                endereco.setNumero(resultado.getInt("numero"));
                enderecos.add(endereco);
            }
        } catch (SQLException ex){
            System.err.println("Erro ao pesquisar enderecos do paciente: "+ ex.getMessage());
            throw new Exception(ex);
        } finally{
            ConnectionManager.closeConnection(connection, statement, resultado);
        }
        return enderecos;
    }

    @Override
    public void excluir(long id) throws Exception {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;
        try {
            connection = ConnectionManager.openConnection();
            String sql = "DELETE from endereco WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException ex){
            System.err.println("Erro ao remover endereço: "+ ex.getMessage());
        } finally{
            ConnectionManager.closeConnection(connection, statement, resultado);
        }
    }
    
}
