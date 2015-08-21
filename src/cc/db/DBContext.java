package cc.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**�ڴ����з�װ��SQLite���ݿ�Ĳ���*/
public class DBContext {
	private DBHelper dbHelper;
	public  DBContext(Context context){
		dbHelper=new DBHelper(context,
		"score_list.db", null,1);//1��ʾ�汾��null (CursorFactory)
	}
	

	public long insert(ContentValues values){
	    //������ݿ�
		SQLiteDatabase db=
		dbHelper.getWritableDatabase();
		//ִ�����ݵĲ������
		long id=db.insert("score_list",
		null, values);
		//�ͷ���Դ
		db.close();
		return id;
	}
	
/*	public void delete(int id){
		SQLiteDatabase db=
				dbHelper.getWritableDatabase();
		String sql="delete from score_list where id=?";
		db.execSQL(sql, new String[]{String.valueOf(id)});
	}
**/	
	public Cursor select(){
		SQLiteDatabase db=
				dbHelper.getWritableDatabase();
		String sql="select * from score_list order by score desc";
		
	  return	db.rawQuery(sql, new String[]{});
	}
	
	//����SQLite���ݿ�Ĺ�����
	private class DBHelper extends SQLiteOpenHelper{
		//������û���޲ι��캯��
		public DBHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}
		/**�˷��������ݿⴴ��ʱ�Զ�����
		 * ֻ����1�Σ������ڴ˷�������ʲô
		 * ȡ���ھ���ҵ��*/
		
		
		
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			 String sql="create table score_list(_id int primary_key auto increment," +
			 		"score int,"
				    +"name varchar(100))";
			   db.execSQL(sql);		
		 
		}
		/**�˷��������ݿ�����ʱ�Զ�����
		 * һ��ָ�������ݿ�İ汾�����仯��
		 * �����ڴ˷�������ʲô
		 * ȡ���ھ���ҵ��*/
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.i("TAG", "onUpgrade");
			/*String sql="drop table notetab";
			db.execSQL(sql);*/
		}
		
	}
}
