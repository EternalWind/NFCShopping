package scut.bgooo.weibouser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	//��������	UserID��Access Token��Access Secret	�ı���
	    public static final String TB_NAME="users";
	
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	//������
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE IF NOT EXISTS "+
                TB_NAME+"("+
                WeiboUserItem.ID+" integer primary key,"+
                WeiboUserItem.USERID+" varchar,"+
                WeiboUserItem.TOKEN+" varchar,"+
                WeiboUserItem.TOKENSECRET+" varchar,"+
                WeiboUserItem.USERNAME+" varchar,"+
                WeiboUserItem.USERLOCATION+" varchar,"+
                WeiboUserItem.ISDEFAULT+" boolean," +
                WeiboUserItem.USERICON+" blob"+                  
                ")"
                );
        Log.e("Database","onCreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	/**
	 * ��������������ݿ��
	 * @param db
	 * @param oldColumn
	 * @param newColumn
	 * @param typeColumn
	 */
	public void updateColumn(SQLiteDatabase db, String oldColumn, String newColumn, String typeColumn){
        try{
            db.execSQL("ALTER TABLE " +
                    TB_NAME + " CHANGE " +
                    oldColumn + " "+ newColumn +
                    " " + typeColumn
            );
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
