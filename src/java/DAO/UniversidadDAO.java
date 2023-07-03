/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Config.Conexion;
import Model.Curso;
import Model.Estudiantes;
import Model.Programa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jhon Mario
 */
public class UniversidadDAO {

    public boolean agregarEstudiante(Estudiantes estudiante) throws Exception {
        Conexion cn = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean repuesta = false;
        int num = 0;
        String query = "INSERT INTO estudiantes (codigo,nombre,edad,fecha_nacimiento,genero,codigo_programa)\n"
                + "VALUES (" + estudiante.getCodigo() + ",'" + estudiante.getNombre() + "'," + estudiante.getEdad() + ","
                + "'" + estudiante.getFechaNacimiento() + "','" + estudiante.getGenero() + "'," + estudiante.getPrograma() + ");";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(query);
            num = ps.executeUpdate();
            if (num > 0) {
                repuesta = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }

        }
        return repuesta;
    }

    public ArrayList listarPrograma() throws Exception {
        Conexion cn = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Programa> list = new ArrayList<>();
        String sql = "select codigo,nombre from programa;";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Programa programa = new Programa();
                programa.setCodigo(rs.getInt("codigo"));
                programa.setNombre(rs.getString("nombre"));
                list.add(programa);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();

            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public ArrayList listarCursoPrograma(Integer id) throws Exception {
        Conexion cn = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Curso> list = new ArrayList<>();
        String sql = "   select cp.codigo,cp.nombre, cp.creditos, cp.codigo_programa\n"
                + "      from programa p\n"
                + "inner join curso_programa cp on cp.codigo_programa = p.codigo\n"
                + "     where p.codigo = (select e.codigo_programa from estudiantes e where e.codigo =" + id + ");";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setCodigo(rs.getInt("codigo"));
                curso.setNombre(rs.getString("nombre"));
                curso.setCreditos(rs.getInt("creditos"));
                curso.setCodigo_programa(rs.getInt("codigo_programa"));
                list.add(curso);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();

            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public ArrayList listarEstudiante() throws Exception {
        Conexion cn = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Estudiantes> list = new ArrayList<>();
        String sql = "select * from estudiantes;";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Estudiantes estudiante = new Estudiantes();
                estudiante.setCodigo(rs.getInt("codigo"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setEdad(rs.getInt("edad"));
                estudiante.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                estudiante.setGenero(rs.getString("genero"));
                estudiante.setPrograma(rs.getInt("codigo_programa"));
                list.add(estudiante);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();

            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public boolean agregarEstudianteCurso(int codigo_programa, int estudiante) throws Exception {
        Conexion cn = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean repuesta = false;
        int num = 0;
        String query = "INSERT INTO curso (codigo_curso_programa, codigo_estudiante) \n"
                + "  SELECT " + codigo_programa + "," + estudiante + " FROM DUAL\n"
                + "WHERE NOT EXISTS \n"
                + "  (SELECT * FROM curso WHERE codigo_curso_programa= " + codigo_programa + " and codigo_estudiante = " + estudiante + ");";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(query);
            num = ps.executeUpdate();
            if (num > 0) {
                repuesta = true;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }

        }
        return repuesta;
    }

}
