package lab8;

import java.sql.*; 

class PreparedStatementCustom{
	private PreparedStatement stmt = null;

	PreparedStatementCustom(PreparedStatement stmt){
		this.stmt = stmt;
	}

	public PreparedStatementCustom set(int place, String value) throws Exception{
		stmt.setString(place, value);
		return this;
	}

	public PreparedStatementCustom set(int place, int value) throws Exception{
		stmt.setInt(place, value);
		return this;
	}

	public int runUpdate() throws Exception{
		return stmt.executeUpdate();
	}

	public ResultSet runQuery() throws Exception{
		return stmt.executeQuery();
	}
}