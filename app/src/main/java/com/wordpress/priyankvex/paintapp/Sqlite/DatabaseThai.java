package com.wordpress.priyankvex.paintapp.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseThai extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "databaseletterthai";

    // Table Name
    private static final String TABLE_NAME = "tableletterthai";



	public DatabaseThai(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	    public static final String COLUMN_ID ="code";
	    public static final String COL_username_ = "username";
	    public static final String COL_letter_thai = "letterthai";
	    public static final String COL_point_thai = "pointthai";


	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE "+ TABLE_NAME +" (code INTEGER PRIMARY KEY AUTOINCREMENT, "
        		+ COL_username_ + " TEXT(100), "
				+ COL_letter_thai + " TEXT(100), "
        		+ COL_point_thai + " TEXT(100));");

	    Log.e("CREATE TABLE","Create Table Successfully.");
	}

	//Insert data
		public long InsertData(String Code, String Ponit_uername, String Ponit_letter_thai, String Ponit_thai
							   ){
			try {
			SQLiteDatabase db;
			db = this.getWritableDatabase(); //write data

			ContentValues Val = new ContentValues();
			Val.put(COLUMN_ID, Code);
			Val.put(COL_username_, Ponit_uername);
			Val.put(COL_letter_thai, Ponit_letter_thai);
			Val.put(COL_point_thai, Ponit_thai);


			long rows = db.insert(TABLE_NAME, null, Val);
			db.close();
			return rows; //return rows inserted.
			} catch (Exception e) {
				return -1;
			}
		}

		//select data
				public String[] SelectDataCode(String Code) {
					try {
						String arrData[] = null;
						SQLiteDatabase db;
						db = this.getReadableDatabase(); // Read Data

						Cursor cursor = db.query(TABLE_NAME, new String[]{ "*" },
								"Code=?",
								new String[] {String.valueOf(Code)}, null, null, null, null);
						if(cursor != null){
							if (cursor.moveToFirst()){
								arrData = new String[cursor.getColumnCount()];

								arrData[0] = cursor.getString(0);
								arrData[1] = cursor.getString(1);
								arrData[2] = cursor.getString(2);
								arrData[3] = cursor.getString(3);
								arrData[4] = cursor.getString(4);
								arrData[5] = cursor.getString(5);
								arrData[6] = cursor.getString(6);
								arrData[7] = cursor.getString(7);
								arrData[8] = cursor.getString(8);
								arrData[9] = cursor.getString(9);
								arrData[10] = cursor.getString(10);
							}
						}
						cursor.close();
						db.close();
						return arrData;
					}
					catch (Exception e) {
					return null;
					 }
				}

				//select data
				public String[] SelectDataUser_accout(String User_accout) {
					try {
						String arrData[] = null;
						SQLiteDatabase db;
						db = this.getReadableDatabase(); // Read Data

						Cursor cursor = db.query(TABLE_NAME, new String[]{ "*" },
								"col2=?",
								new String[] {String.valueOf(User_accout)}, null, null, null, null);
						if(cursor != null){
							if (cursor.moveToFirst()){
								arrData = new String[cursor.getColumnCount()];
								/***
								 *  0 = code
								 *  1 = Name
								 *  2 = User
								 */
								arrData[0] = cursor.getString(0);
								arrData[1] = cursor.getString(1);
								arrData[2] = cursor.getString(2);
								arrData[3] = cursor.getString(3);
								arrData[4] = cursor.getString(4);
								arrData[5] = cursor.getString(5);
								arrData[6] = cursor.getString(6);
								arrData[7] = cursor.getString(7);
								arrData[8] = cursor.getString(8);
								arrData[9] = cursor.getString(9);
								arrData[10] = cursor.getString(10);
							}
						}
						cursor.close();
						db.close();
						return arrData;
					}

					catch (Exception e) {
					return null;
					 }

				}//select data
				public String[] SelectDataUser_password(String strpassword) {
					try {
						String arrData[] = null;
						SQLiteDatabase db;
						db = this.getReadableDatabase(); // Read Data

						Cursor cursor = db.query(TABLE_NAME, new String[]{ "*" },
								"user_password=?",
								new String[] {String.valueOf(strpassword)}, null, null, null, null);
						if(cursor != null){
							if (cursor.moveToFirst()){
								arrData = new String[cursor.getColumnCount()];

								arrData[0] = cursor.getString(0);
								arrData[1] = cursor.getString(1);
								arrData[2] = cursor.getString(2);
								arrData[3] = cursor.getString(3);
								arrData[4] = cursor.getString(4);
								arrData[5] = cursor.getString(5);
								arrData[6] = cursor.getString(6);
								arrData[7] = cursor.getString(7);
								arrData[8] = cursor.getString(8);
								arrData[9] = cursor.getString(9);
								arrData[10] = cursor.getString(10);
							}
						}
						cursor.close();
						db.close();
						return arrData;
					}

					catch (Exception e) {
					return null;
					 }

				}

		// Show All Data type
		public ArrayList<HashMap<String, String>> SelectAllData() {
			// TODO Auto-generated method stub

			 try {
				 ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
				 HashMap<String, String> map;
				 SQLiteDatabase db;
				 db = this.getReadableDatabase(); // Read Data
				 String strSQL = "SELECT  * FROM " + TABLE_NAME;
				 Cursor cursor = db.rawQuery(strSQL, null);
				 	if(cursor != null)
				 	{
				 	    if (cursor.moveToFirst()) {
				 	        do {
				 	        	map = new HashMap<String, String>();
				 	        	map.put(COLUMN_ID, cursor.getString(0));
					 	        map.put(COL_username_, cursor.getString(1));
					 	        map.put(COL_letter_thai, cursor.getString(2));
					 	        map.put(COL_point_thai, cursor.getString(3));

					 	        MyArrList.add(map);
				 	        } while (cursor.moveToNext());
				 	    }
				 	}
				 	cursor.close();
				 	db.close();
					return MyArrList;
			 } catch (Exception e) {
			    return null;
			 }
		}

		// Update Data
		public long UpdateData(String Code, String username, String letterThai ,String pointThai) {
			// TODO Auto-generated method stub
			 try {

				SQLiteDatabase db;
	     		db = this.getWritableDatabase(); // Write Data
	            ContentValues Val = new ContentValues();
	        	//Val.put("Code", Code);
				Val.put(COL_username_, username);
				Val.put(COL_letter_thai, letterThai);
				Val.put(COL_point_thai, pointThai);

				 Log.e(" UpdateDat code",Code);
				 Log.e(" UpdateDat ausername",username);
				 Log.e("UpdateData letterThai",letterThai);
				 Log.e("UpdateData pointThai",pointThai);


	            long rows = db.update(TABLE_NAME, Val, " Code = ?",
	                    new String[] { String.valueOf(Code) });

	     		db.close();

                 Log.e("UpdateDataThai", String.valueOf(rows));
	     		return rows; // return rows updated.

			 } catch (Exception e) {
			    return -1;
			 }

		}

		// Delete Data
		public long DeleteData(String Code) {
			// TODO Auto-generated method stub
			 try {
				SQLiteDatabase db;
	     		db = this.getWritableDatabase(); // Write Data
	     		long rows = db.delete(TABLE_NAME, "Code = ?",new String[] { String.valueOf(Code) });
	     		db.close();
	     		return rows; // return rows deleted.
			 } catch (Exception e) {
			    return -1;
			 }

		}
		public void removeAll()
		{
		    // db.delete(String tableName, String whereClause, String[] whereArgs);
		    // If whereClause is null, it will delete all rows.
		    SQLiteDatabase db = this.getWritableDatabase(); // helper is object extends SQLiteOpenHelper
		    db.delete(TABLE_NAME, null, null);
		}

		public Cursor readAllDATA() {
			 SQLiteDatabase db;
			 db = this.getReadableDatabase(); // Read Data
			 String strSQL = "SELECT  * FROM " + TABLE_NAME;
			 Cursor cursor = db.rawQuery(strSQL, null);
			Cursor mCursor = db.query(TABLE_NAME, new String[] {
					COLUMN_ID, COL_username_, COL_letter_thai, COL_point_thai}, null, null, null,
					null, null);
			if (mCursor != null) {
				mCursor.moveToFirst();
					}
			return mCursor;
				}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
	        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	        // Re Create on method  onCreate
	        onCreate(db);
		}

	}














