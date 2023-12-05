package sio.devoir2graphiques.Tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GraphiqueController
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public GraphiqueController() {
        cnx = ConnexionBDD.getCnx();
    }
    // A vous de jouer

    public HashMap<Integer, Double> getGraphique1Datas()
    {
        HashMap<Integer, Double> datas = new HashMap();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("SELECT employe.ageEmp, AVG(employe.salaireEmp)\n" +
                    "FROM employe\n" +
                    "GROUP BY employe.ageEmp");
            rs = ps.executeQuery();
            while(rs.next())
            {
                datas.put(rs.getInt(1), rs.getDouble(2));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return datas;
    }

    public HashMap<String, ArrayList<Double>> getGraphique2Datas(){
        HashMap<String, ArrayList<Double>> datas = new HashMap<>();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("SELECT  employe.sexe, employe.ageEmp, COUNT(employe.ageEmp) AS nb\n" +
                    "FROM employe\n" +
                    "GROUP BY employe.ageEmp, employe.sexe;\n");
            rs = ps.executeQuery();

            while(rs.next())
            {
                if(!datas.containsKey(rs.getString("sexe")))
                {
                    ArrayList<Double> leSexe = new ArrayList<>();
                    leSexe.add(rs.getDouble("ageEmp"));
                    leSexe.add(rs.getDouble("nb"));
                    datas.put(rs.getString("sexe"),leSexe);
                }
                else
                {
                    datas.get(rs.getString("sexe")).add(rs.getDouble("ageEmp"));
                    datas.get(rs.getString("sexe")).add(rs.getDouble("nb"));
                }

            }
            rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return datas;
    }

    public HashMap<String,Integer> getGraphique3Datas()
    {
        HashMap<String, Integer> datas = new HashMap();
        try {
            cnx = ConnexionBDD.getCnx();
            ps = cnx.prepareStatement("SELECT employe.sexe, COUNT(*) as nb\n" +
                    "FROM employe\n" +
                    "GROUP BY employe.sexe");
            rs = ps.executeQuery();
            while(rs.next())
            {
                datas.put(rs.getString(1), rs.getInt(2));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return datas;
    }}
