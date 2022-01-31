package pt.ipbeja.catlogoeletrnico.models;

import android.content.Context;

public class SessionRepository {
    private Context context;

    public SessionRepository(Context context){
        this.context = context;
    }

    public User getActiveSession(){
        return SessionManager.getActiveSession(this.context);
    }

    public boolean getTheme(){
        return SessionManager.getTheme(this.context);
    }

    public void saveSession(User user){
        SessionManager.saveSession(this.context,user);
    }

    public void saveTheme(boolean b){
        SessionManager.saveTheme(this.context,b);
    }

    public void clearSession(){
        SessionManager.clearSession(this.context);
    }

    public void clearTheme(){
        SessionManager.clearTheme(this.context);
    }
}
