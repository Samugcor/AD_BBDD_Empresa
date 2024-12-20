package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Conexion {
    String url = "jdbc:mysql://localhost:3306/bdempresa";
    String user = "root";
    String password = "";
    Connection conexion;

    public Conexion() {

        try{
            this.conexion = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet consultaSt(String query) {
        ResultSet respuesta;
        try {
            Statement st = conexion.createStatement();
            respuesta = st.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return respuesta;
    }

    public int updateSt(String query) {
        int respuesta;
        try(Statement st = conexion.createStatement()) {
            respuesta= st.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return respuesta;
    }

    public int cambiarDepartamentoProxecto(String nomeDepartamento, String nomeProxecto) {
        String query = "UPDATE proxecto SET Num_departamento = (SELECT Num_departamento FROM departamento WHERE nome_departamento = ?) WHERE nome_proxecto = ?";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nomeDepartamento);
            ps.setString(2, nomeProxecto);

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error cambiando al proyecto de departamento: " + e.getMessage(), e);
        }
    }
    public int nuevoProxecto(Proxecto proxecto) {
        String query = "INSERT INTO proxecto VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, proxecto.getNumProxecto());
            ps.setString(2, proxecto.getNomeProxecto());
            ps.setString(3, proxecto.getLugar());
            ps.setInt(4, proxecto.getNumDepartamento());

            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting new project: " + e.getMessage(), e);
        }
    }
    public int borrarProxecto(int numProxecto) {
        String deleteAssignments = "DELETE FROM empregado_proxecto WHERE Num_proxecto = ?";
        String deleteProject = "DELETE FROM proxecto WHERE Num_proxecto = ?";

        try (PreparedStatement ps1 = conexion.prepareStatement(deleteAssignments);
             PreparedStatement ps2 = conexion.prepareStatement(deleteProject)) {

            ps1.setInt(1, numProxecto);
            ps1.executeUpdate();

            ps2.setInt(1, numProxecto);

            return ps2.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting project: " + e.getMessage(), e);
        }
    }

    public List<Proxecto> obterProxectosPorDepartamento(String nomeDepartamento) {
        List<Proxecto> proxectosPorDep = new ArrayList<>();

        String query = "SELECT proxecto.Num_proxecto, proxecto.nome_proxecto, proxecto.lugar, proxecto.Num_departamento " +
                "FROM proxecto JOIN departamento " +
                "ON proxecto.Num_departamento = departamento.Num_departamento " +
                "WHERE departamento.nome_departamento = ?";

        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nomeDepartamento);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Proxecto proxecto = new Proxecto();
                proxecto.setNumProxecto(rs.getInt("Num_proxecto"));
                proxecto.setNomeProxecto(rs.getString("nome_proxecto"));
                proxecto.setLugar(rs.getString("lugar"));
                proxecto.setNumDepartamento(rs.getInt("Num_departamento"));
                proxectosPorDep.add(proxecto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter proxectos por departamento: " + e.getMessage(), e);
        }
        return proxectosPorDep;
    }

    //EJ2_5
    public void cambioDomicilio(int nss, String rua, int numero, String piso, String cp, String localidade) {
        String call = "{CALL pr_cambioDomicilio(?, ?, ?, ?, ?, ?)}";

        try (CallableStatement cs = conexion.prepareCall(call)) {
            cs.setInt(1, nss);
            cs.setString(2, rua);
            cs.setInt(3, numero);
            cs.setString(4, piso);
            cs.setString(5, cp);
            cs.setString(6, localidade);
            cs.execute();

            System.out.println("Domicilio actualizado correctamente.");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao actualizar o domicilio: " + e.getMessage(), e);
        }
    }

    public Proxecto obterDatosProxecto(int numProxecto) {
        String call = "{CALL pr_DatosProxectos(?, ?, ?, ?)}";
        Proxecto proxecto = new Proxecto();

        try (CallableStatement cs = conexion.prepareCall(call)) {
            cs.setInt(1, numProxecto);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.VARCHAR);
            cs.registerOutParameter(4, Types.INTEGER);

            cs.execute();

            proxecto.setNumProxecto(numProxecto);
            proxecto.setNomeProxecto(cs.getString(2));
            proxecto.setLugar(cs.getString(3));
            proxecto.setNumDepartamento(cs.getInt(4));

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter datos do proxecto: " + e.getMessage(), e);
        }
        return proxecto;
    }

    public void departamentosControlanProxectos(int numProxectos) {
        String call = "{CALL pr_DepartControlaProxec(?)}";
        try (PreparedStatement ps = conexion.prepareStatement(call)) {
            ps.setInt(1, numProxectos);
            ResultSet rs = ps.executeQuery();

            System.out.println("Departamentos que controlan " + numProxectos + " ou máis proxectos:\n");
            while (rs.next()) {
                System.out.println("Número Departamento: " + rs.getInt("Num_departamento"));
                System.out.println("Nome: " + rs.getString("nome_departamento"));
                System.out.println("NSS_dirige: " + rs.getInt("NSS_dirige"));
                System.out.println("data_direccion: " + rs.getDate("data_direccion"));
                System.out.println("--------------------------------------------\n");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter departamentos: " + e.getMessage(), e);
        }
    }
    public int obterNumeroEmpregadosDepartamento(String nomeDepartamento) {
        String query = "SELECT fn_nEmpDepart(?) AS numEmpregados";
        int numEmpregados = 0;
        try (PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, nomeDepartamento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                numEmpregados = rs.getInt("numEmpregados");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao obter número de empregados: " + e.getMessage(), e);
        }
        return numEmpregados;
    }

    //METADATOS EJERCICIO 3
    public void mostrarInformacionBaseDatos() {
        try {
            // Obter información da base de datos
            DatabaseMetaData metaData = conexion.getMetaData();

            System.out.println("Información sobre a base de datos e o conector JDBC:");
            System.out.println("----------------------------------------------------");
            System.out.println("Nome do SXBD: " + metaData.getDatabaseProductName());
            System.out.println("Número de versión do SXBD: " + metaData.getDatabaseProductVersion());
            System.out.println("Número de versión principal do SXBD: " + metaData.getDatabaseMajorVersion());
            System.out.println("Número de versión secundaria do SXBD: " + metaData.getDatabaseMinorVersion());
            System.out.println("Nome do conectador JDBC utilizado: " + metaData.getDriverName());
            System.out.println("Número da versión principal do conectador JDBC: " + metaData.getDriverMajorVersion());
            System.out.println("Número da versión secundaria do conectador JDBC: " + metaData.getDriverMinorVersion());
            System.out.println("Número de versión do conectador JDBC utilizado: " + metaData.getDriverVersion());
            System.out.println("URL da base de datos: " + metaData.getURL());
            System.out.println("Nome do usuario actual conectado: " + metaData.getUserName());
            System.out.println("É de só lectura a base de datos?: " + metaData.isReadOnly());
        } catch (SQLException e) {
            System.out.println("Erro ao obter información da base de datos: " + e.getMessage());
        }
    }

    public void mostrarInfoTablas() {
        try {
            DatabaseMetaData metaData = conexion.getMetaData();
            ResultSet tablas = metaData.getTables(null, null, "%", new String[]{"TABLE"});
            System.out.println("Táboas de usuario na base de datos:");
            while (tablas.next()) {
                System.out.println("Esquema: " + tablas.getString("TABLE_SCHEM"));
                System.out.println("Táboa: " + tablas.getString("TABLE_NAME"));
                System.out.println("Tipo: " + tablas.getString("TABLE_TYPE"));
                System.out.println("----------------------");
            }
            tablas.close();
        } catch (SQLException e) {
            System.out.println("Erro ao obter as táboas de usuario: " + e.getMessage());
        }
    }

    public void mostrarColumnasTabla(String esquema, String tabla) {
        try {
            DatabaseMetaData metaData = conexion.getMetaData();
            ResultSet columnas = metaData.getColumns(null, esquema, tabla, null);
            System.out.println("Columnas da táboa " + tabla + ":");
            while (columnas.next()) {
                System.out.println("Nome: " + columnas.getString("COLUMN_NAME"));
                System.out.println("Tipo de datos: " + columnas.getString("TYPE_NAME"));
                System.out.println("Tamaño: " + columnas.getInt("COLUMN_SIZE"));
                System.out.println("Admite valores nulos: " + columnas.getString("IS_NULLABLE"));
                System.out.println("----------------------");
            }
            columnas.close();
        } catch (SQLException e) {
            System.out.println("Erro ao obter columnas da táboa: " + e.getMessage());
        }
    }

    public void mostrarProcedementos() {
        try {
            DatabaseMetaData metaData = conexion.getMetaData();
            ResultSet procedementos = metaData.getProcedures(null, null, "%");
            System.out.println("Procedementos almacenados na base de datos:");
            while (procedementos.next()) {
                System.out.println("Nome do procedemento: " + procedementos.getString("PROCEDURE_NAME"));
                System.out.println("Esquema: " + procedementos.getString("PROCEDURE_SCHEM"));
                System.out.println("Tipo: " + procedementos.getString("PROCEDURE_TYPE"));
                System.out.println("----------------------");
            }
            procedementos.close();
        } catch (SQLException e) {
            System.out.println("Erro ao obter procedementos: " + e.getMessage());
        }
    }
    public void mostrarClavePrimaria(String esquema, String tabla) {
        try {
            DatabaseMetaData metaData = conexion.getMetaData();
            ResultSet pk = metaData.getPrimaryKeys(null, esquema, tabla);
            System.out.println("Claves primarias da táboa " + tabla + ":");
            while (pk.next()) {
                System.out.println("Nome da columna: " + pk.getString("COLUMN_NAME"));
                System.out.println("Nome da clave: " + pk.getString("PK_NAME"));
                System.out.println("----------------------");
            }
            pk.close();
        } catch (SQLException e) {
            System.out.println("Erro ao obter claves primarias: " + e.getMessage());
        }
    }

    public void mostrarClaveForanea(String esquema, String tabla) {
        try {
            DatabaseMetaData metaData = conexion.getMetaData();
            ResultSet fk = metaData.getImportedKeys(null, esquema, tabla);

            ResultSetMetaData metaDataFK = fk.getMetaData();
            System.out.println("Columnas no ResultSet:");
            for (int i = 1; i <= metaDataFK.getColumnCount(); i++) {
                System.out.println(" - " + metaDataFK.getColumnName(i));
            }

            System.out.println("Claves foráneas da táboa " + tabla + ":");
            while (fk.next()) {
                System.out.println("Columna de clave foránea: " + fk.getString("FKCOLUMN_NAME"));
                System.out.println("Táboa referenciada: " + fk.getString("PKTABLE_NAME"));
                System.out.println("Columna referenciada: " + fk.getString("PKCOLUMN_NAME"));
                System.out.println("----------------------");
            }
            fk.close();
        } catch (SQLException e) {
            System.out.println("Erro ao obter claves foráneas: " + e.getMessage());
        }
    }

    public void mostrarDatosTaboasProcedementos(){
        try{
            DatabaseMetaData metaData = conexion.getMetaData();
            System.out.println("Funcions de cadea: " + metaData.getStringFunctions());
            System.out.println("Funcions de data e hora: " + metaData.getTimeDateFunctions());
            System.out.println("Funcions matematicas: " + metaData.getNumericFunctions());
            System.out.println("Funcions de sistema: " + metaData.getSystemFunctions());
            System.out.println("Palabras reservadas: " + metaData.getSQLKeywords());
            System.out.println("Cadea para delimitar identificadores: " + metaData.getIdentifierQuoteString());
            System.out.println("Cadea escape de caracter comodin: " + metaData.getSearchStringEscape());
            System.out.println("Usuario pode chamar a todos os procedementos: " + metaData.allProceduresAreCallable());
            System.out.println("Usuario pode acceder a todas as táboas: " + metaData.allTablesAreSelectable());

        }catch (SQLException e){
            System.out.println("Erro ao mostrar caracteristicas de funciones e procedementos.");
            e.printStackTrace();
        }
    }
    public void mostrarLimitesConectador(){
        try{
            DatabaseMetaData metaData = conexion.getMetaData();
            System.out.println("Número máx. de conexiones simultáneas: "+ (metaData.getMaxConnections() == 0? "Sen limite": metaData.getMaxConnections()));
            System.out.println("Número máx. de sentencias simultáneas: "+ (metaData.getMaxStatements() == 0? "Sen limite": metaData.getMaxStatements()));
            System.out.println("Número máx. de tablas en una consulta SELECT: "+metaData.getMaxTablesInSelect());
            System.out.println("Longitud máx. del nombre de una tabla: "+metaData.getMaxTableNameLength());
            System.out.println("Longitud máx. del nombre de una columna: "+metaData.getMaxColumnNameLength());
            System.out.println("Longitud máx. del nombre de una sentencia: "+metaData.getMaxStatementLength());
            System.out.println("Longitud máx. del nombre de una fila: "+metaData.getMaxRowSize());
            System.out.println("Longitud máx. del nombre de una procedimiento: "+metaData.getMaxProcedureNameLength());
            System.out.println("Número máx. de columnas que se pueden usar en un ORDER: "+metaData.getMaxColumnsInOrderBy());
            System.out.println("Número máx. de columnas que se pueden usar en un SELECT: "+metaData.getMaxColumnsInSelect());
            System.out.println("Número máx. de columnas que se pueden usar en un GROUP BY: "+metaData.getMaxColumnsInGroupBy());

        }catch (SQLException e){
            System.out.println("Erro ao mostrar os límites do conectador.");
            e.printStackTrace();
        }
    }

    public void mostrarInfoTransacciones(){
        try{
            DatabaseMetaData metaData = conexion.getMetaData();
            System.out.println("Soporta transacciones: "+(metaData.supportsTransactions()?"Sí":"No"));
            System.out.println("Nivel de aislamiento de las transacciones predeterminado: "+metaData.getDefaultTransactionIsolation());
            System.out.println("Soporta sentenzas de manipulación de datos e de definición de datos dentro das transaccións: "+metaData.supportsDataDefinitionAndDataManipulationTransactions());

        }catch (SQLException e){
            System.out.println("Erro ao mostrar información sobre transaccions");
            e.printStackTrace();
        }
    }

    public void mostrarSoporteCaract(){
        try{
            DatabaseMetaData metaData = conexion.getMetaData();
            System.out.println("La instrucción ALTER TABLE se puede utilizar ADD COLUMN y DROP COLUMN: "+metaData.supportsAlterTableWithAddColumn());
            System.out.println("Los alias de columnas se puede utilizar la palabra AS: "+metaData.supportsColumnAliasing());
            System.out.println("El resultado de concatenar un valor NULL con uno NOT NULL da como resultado NULL: "+metaData.nullPlusNonNullIsNull());
            System.out.println("Se soportan las conversiones entre tipos de datos JDBC: "+metaData.supportsConvert());
            System.out.println("Se soportan los nombres de tablas correlacionadas: "+metaData.supportsTableCorrelationNames());
            System.out.println("Se permite usar una columna que no esté en la instrucción SELECT en una cláusula ORDER BY: "+metaData.supportsOrderByUnrelated());
            System.out.println("Se soporta la cláusula GROUP BY: "+metaData.supportsGroupBy());
            System.out.println("Se permite el uso de una columna que no esté en la instrucción SELECT en una cláusula GROUP BY: "+metaData.supportsGroupByUnrelated());
            System.out.println("Se soportan las cláusulas LIKE: "+metaData.supportsLikeEscapeClause());
            System.out.println("Se soportan los outer joins: "+metaData.supportsOuterJoins());
            System.out.println("Se soportan subconsultas EXISTS: "+metaData.supportsSubqueriesInExists());
            System.out.println("Se soportan subconsultas en expresiones de comparación IN: "+metaData.supportsSubqueriesInIns());
            System.out.println("Se soportan subconsultas en expresiones de comparación en expresiones cuantificadas.: "+metaData.supportsSubqueriesInQuantifieds());

        }catch (SQLException e){
            System.out.println("Erro ao mostrar información sobre o soporte de características");
            e.printStackTrace();
        }
    }

    public void mostrarMetaDatosResultSet(String query){
        try(Statement st = conexion.createStatement()){
            ResultSet rs = st.executeQuery(query);
            ResultSetMetaData metaData = rs.getMetaData();
            int numColumnas = metaData.getColumnCount();

            System.out.println("\niNFORMACIÓN SOBRE COLUMNAS. TOTAL DE COLUMNAS EN LA CONSULTA: "+numColumnas);

            for (int i = 1; i <= numColumnas; i++){
                System.out.println("Nome columna: "+metaData.getColumnName(i));
                System.out.println("Tipo: "+metaData.getColumnTypeName(i));
                System.out.println("Tamaño: "+metaData.getColumnDisplaySize(i));
                System.out.println("Pode ser null: "+metaData.isNullable(i));
            }

        }catch (SQLException e){
            System.out.println("Erro ao mostrar metadatos do ResultSet");
            e.printStackTrace();
        }
    }
    public void closeConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Se ha cerrado la conexión");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
