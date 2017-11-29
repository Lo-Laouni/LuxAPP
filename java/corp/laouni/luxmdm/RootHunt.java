package corp.laouni.luxmdm;

import android.content.Context;

/**
 * Created by Ishimwe on 25/9/2017.
 */

public class RootHunt {

    private final Context hContext;
    private boolean loggingEnabled = true;

    public RootHunt(Context context){
        hContext = context;
    }

    /*public boolean isRooted(){
        return detectRootManagementApps() || detectPotentiallyDangerousApps()
    }*/
}
