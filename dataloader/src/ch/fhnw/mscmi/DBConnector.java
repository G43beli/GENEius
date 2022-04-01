package ch.fhnw.mscmi;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBConnector {

    private String connectionStr;
    private String username;
    private String password;
    private Connection connection;
    private Map<String, Integer> geneTypes;
    private String insertCmd;

    public DBConnector(String hostname, int port, String dbName, String username, String password) throws Exception {
        connectionStr = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName;
        this.username = username;
        this.password = password;
        connect();
    }

    private void connect() throws Exception {
        connection = DriverManager.getConnection(connectionStr, username, password);
        geneTypes = new HashMap<>();
        insertCmd = createInsertCommand();
    }

    public void writeRow(Gene gene) throws Exception {
        PreparedStatement stmt = createInsertStatement(gene);
        stmt.executeUpdate();
        stmt.close();
    }

    public void writeRows(List<Gene> genes) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (Gene g : genes) {
            sb.append(createParameterString(g) + ",");
        }
        String multipleInsertCmd = insertCmd.substring(0, insertCmd.indexOf(")") + 1) + " VALUES " + sb.toString();
        multipleInsertCmd = multipleInsertCmd.substring(0, multipleInsertCmd.length() - 1);
        PreparedStatement stmt = connection.prepareStatement(multipleInsertCmd);
        stmt.executeUpdate();
        stmt.close();
    }

    public void disconnect() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private int geneTypeId(Gene gene) throws Exception {
        if (geneTypes.containsKey(gene.getGeneType())) {
            return geneTypes.get(gene.getGeneType());
        }
        Integer id = geneTypes.size() + 1;
        String cmd = "insert into genetype (id, name) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(cmd);
        stmt.setInt(1, id);
        stmt.setString(2, gene.getGeneType());
        stmt.executeUpdate();
        stmt.close();
        geneTypes.put(gene.getGeneType(), id);
        return id;
    }

    private String createInsertCommand() throws Exception {
        ResultSet rs = connection.getMetaData().getColumns(null, null, "allgenes", null);
        String cmd = "insert into allgenes(";
        String parameters = "(";

        while (rs.next()) {
            cmd += rs.getString("COLUMN_NAME") + ",";
            parameters += "?,";
        }

        cmd = cmd.substring(0, cmd.length() - 1) + ")";
        parameters = parameters.substring(0, parameters.length() - 1) + ")";

        return cmd + " VALUES " + parameters;
    }

    private PreparedStatement createInsertStatement(Gene gene) throws Exception {
        PreparedStatement stmt = connection.prepareStatement(insertCmd);

        stmt.setInt(Gene.TAX_ID_IDX+1, gene.getTaxId());
        stmt.setInt(Gene.GENE_ID_IDX+1, gene.getGeneId());
        stmt.setString(Gene.SYMBOL_IDX+1, gene.getSymbol());
        stmt.setString(Gene.LOCUS_TAG_IDX+1, gene.getLocusTag());
        stmt.setString(Gene.SYNONYMS_IDX+1, gene.getSynonyms());
        stmt.setString(Gene.DBXREFS_IDX+1, gene.getDbxrefs());
        stmt.setString(Gene.CHROMOSOME_IDX+1, gene.getChromosome());
        stmt.setString(Gene.MAP_LOCATION_IDX+1, gene.getMapLocation());
        stmt.setString(Gene.DESCRIPTION_IDX+1, gene.getDescription());
        int geneTypeId = geneTypeId(gene);
        stmt.setInt(Gene.TYPE_OF_GENE_IDX+1, geneTypeId);
        stmt.setString(Gene.SFNA_IDX+1, gene.getSfna());
        stmt.setString(Gene.FNFNA_IDX+1, gene.getFnfna());
        stmt.setString(Gene.NOMENCLATURE_STATUS_IDX+1, gene.getNomenclatureStatus());
        stmt.setString(Gene.OTHER_DESIGNATIONS_IDX+1, gene.getOtherDesignations());
        Date modificationDate = null;
        if (gene.getModificationDate() != null) {
            Integer year = Integer.parseInt(gene.getModificationDate().substring(0, 4));
            Integer month = Integer.parseInt(gene.getModificationDate().substring(4, 6));
            Integer day = Integer.parseInt(gene.getModificationDate().substring(6, 8));
            modificationDate = Date.valueOf(year + "-" + month + "-" + day);
        }
        stmt.setDate(Gene.MODIFICATION_DATE_IDX+1, modificationDate);
        stmt.setString(Gene.FEATURE_TYPE_IDX+1, gene.getFeatureType());

        return stmt;
    }

    private String createParameterString(Gene gene) throws Exception {
        PreparedStatement stmt = createInsertStatement(gene);
        String stmtStr = stmt.toString();
        int startIndex = stmtStr.substring(stmtStr.indexOf("VALUES")).indexOf("(");
        stmt.close();
        String result = stmtStr.substring(stmtStr.indexOf("VALUES")).substring(startIndex);
        return result;
    }
}
