//package com.bluebox.james;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//
//import com.bluebox.james.model.DeviceModel;
// 
//public class DeviceDBHelper {
// 
//	private static final int 	DB_VERSION = 1;
//	private static final String DB_NAME = "data.db";
// 
//	private SQLiteDatabase		mDB;
//	private DBHelper 			mDBHelper;
// 
//	public DeviceDBHelper(Context context){
//		mDBHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
//	}
// 
//	public void open() {
//		mDB = mDBHelper.getWritableDatabase();
//	}
// 
//	public void close() {
//		mDB.close();
//	}
// 
//	public SQLiteDatabase getDB() {
//		return mDB;
//	}
// 
//	public long addDevice(DeviceModel device) {
//		ContentValues values = new ContentValues();
//		
//		values.put(DBHelper.T_PROBE_COL_DEVICE_ID, device.getId());
//		values.put(DBHelper.T_PROBE_COL_DEVICE_NAME, device.getName());
//
//		return mDB.insert(DBHelper.T_PROBE, null, values);
//	}
// 
//	public int update(int id, DeviceModel device) {
//		ContentValues values = new ContentValues();
//		values.put(DBHelper.T_PROBE_COL_DEVICE_ID, device.getId());
//		values.put(DBHelper.T_PROBE_COL_DEVICE_NAME, device.getName());
//		return mDB.update(DBHelper.T_PROBE, values, DBHelper.T_PROBE_COL_ID + " = " + id, null);
//	}
// 
//	public int removeDevice(int id) {
//		return mDB.delete(DBHelper.T_PROBE, DBHelper.T_PROBE_COL_ID + " = " + id, null);
//	}
// 
////	public DeviceModel getLivreWithTitre(String titre){
////		//Récupère dans un Cursor les valeur correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
////		Cursor c = mDB.query(TABLE_LIVRES, new String[] {COL_ID, COL_ISBN, COL_TITRE}, COL_TITRE + " LIKE \"" + titre +"\"", null, null, null, null);
////		return cursorToLivre(c);
////	}
// 
////	private DeviceModel cursorToLivre(Cursor c) {
////		//si aucun élément n'a été retourné dans la requête, on renvoie null
////		if (c.getCount() == 0)
////			return null;
//// 
////		//Sinon on se place sur le premier élément
////		c.moveToFirst();
////		//On créé un livre
////		Livre livre = new Livre();
////		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
////		livre.setId(c.getInt(NUM_COL_ID));
////		livre.setIsbn(c.getString(NUM_COL_ISBN));
////		livre.setTitre(c.getString(NUM_COL_TITRE));
////		//On ferme le cursor
////		c.close();
//// 
////		//On retourne le livre
////		return livre;
////	}
//}