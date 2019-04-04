package controller;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import action.Dbtest;

public class Contoller {
	private Dbtest Dbtest;
	
	public ArrayList Dbtest()throws Exception{
		Dbtest dbtest = new Dbtest();
		ArrayList list = dbtest.getDbtest();
		return list;
	}
}
