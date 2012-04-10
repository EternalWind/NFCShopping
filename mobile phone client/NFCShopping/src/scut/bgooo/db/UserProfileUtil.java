package scut.bgooo.db;

import scut.bgooo.entities.Profile;
import scut.bgooo.entities.User;
import weibo4android.http.AccessToken;
import android.content.Context;
import android.content.SharedPreferences;

public class UserProfileUtil {
	// ����������֤�û���Access_Token
	public static void saveProfile(Context con, User user, String password) {
		SharedPreferences sp = con.getSharedPreferences("profile",
				Context.MODE_PRIVATE);
		sp.edit().putString("username", user.getProperty(2).toString())
				.putString("password", password)
				.putInt("id", (Integer) user.getProperty(1)).commit();
	}

	// ��ȡ�û���֤��Access Token
	public static Profile readProfile(Context con) {
		Profile at = null;
		SharedPreferences sp = con.getSharedPreferences("profile",
				Context.MODE_PRIVATE);
		String username = sp.getString("username", null);
		String password = sp.getString("password", null);
		int id = sp.getInt("id", 0);
		if (username != null && password != null) {
			at = new Profile(id, username, password);
		}
		return at;
	}
}
