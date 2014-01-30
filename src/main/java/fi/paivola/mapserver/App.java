package fi.paivola.mapserver;

import fi.paivola.mapserver.core.GameManager;
import fi.paivola.mapserver.core.GameThread;
import fi.paivola.mapserver.core.Model;
import fi.paivola.mapserver.core.SettingsParser;
import fi.paivola.mapserver.core.WSServer;
import fi.paivola.mapserver.core.setting.SettingMaster;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.net.UnknownHostException;
import java.util.logging.LogManager;
import org.json.simple.parser.ParseException;

public class App {

    static final boolean profilingRun = false;

    public static void main(String[] args) throws UnknownHostException, IOException, ParseException, InterruptedException {

        
        runTest();
    }

    static void printHelp() {
        System.out.println("q|e|quit|exit   - Quits the program\n"
                + "t|test          - Run the test function\n"
                + "h|help          - Display this help");
    }
    static void runTest() {
        GameThread one = new GameThread((int) Math.floor(1500));
        GameManager gm = one.game;
        
        Model l1 = gm.createModel("Lake");
        SettingMaster sm = gm.getDefaultSM("Lake");
        sm.settings.get("order").setValue("1");
        sm.settings.get("k").setValue("1");
        sm.settings.get("surfaceArea").setValue("255000000.0");
        sm.settings.get("depth").setValue("14.1");
        sm.settings.get("startAmount").setValue("14.1");
        sm.settings.get("flowAmount").setValue("0");
        sm.settings.get("basinArea").setValue("7672290000");
        sm.settings.get("terrainCoefficient").setValue("0.9");
        l1.onActualUpdateSettings(sm);
        
        
        Model l2 = gm.createModel("Lake");
        sm = gm.getDefaultSM("Lake");
        sm.settings.get("order").setValue("1");
        sm.settings.get("k").setValue("1");
        sm.settings.get("surfaceArea").setValue("255000000.0");
        sm.settings.get("depth").setValue("14.1");
        sm.settings.get("startAmount").setValue("14.1");
        sm.settings.get("flowAmount").setValue("0");
        sm.settings.get("basinArea").setValue("7672290000");
        sm.settings.get("terrainCoefficient").setValue("0.9");
        l2.onActualUpdateSettings(sm);
                
        
        Model r1 = gm.createModel("River");
        sm = gm.getDefaultSM("River");
        sm.settings.get("order").setValue("2");
        sm.settings.get("width").setValue("50");
        sm.settings.get("length").setValue("100000");
        sm.settings.get("startDepth").setValue("1");
        sm.settings.get("floodDepth").setValue("4");;
        sm.settings.get("flowDepth").setValue("0.01");
        r1.onActualUpdateSettings(sm);
        
        
        Model r2 = gm.createModel("River");
        sm = gm.getDefaultSM("River");
        sm.settings.get("order").setValue("2");
        sm.settings.get("width").setValue("50");
        sm.settings.get("length").setValue("100000");
        sm.settings.get("startDepth").setValue("1");
        sm.settings.get("floodDepth").setValue("4");;
        sm.settings.get("flowDepth").setValue("0.5");
        r2.onActualUpdateSettings(sm);
                
        
        Model l3 = gm.createModel("Lake");
        sm = gm.getDefaultSM("Lake");
        sm.settings.get("order").setValue("3");
        sm.settings.get("k").setValue("1");
        sm.settings.get("surfaceArea").setValue("255000000.0");
        sm.settings.get("depth").setValue("14.1");
        sm.settings.get("startAmount").setValue("14.1");
        sm.settings.get("flowAmount").setValue("0");
        sm.settings.get("basinArea").setValue("7672290000");
        sm.settings.get("terrainCoefficient").setValue("0.9");
        l3.onActualUpdateSettings(sm);
        
        
        Model r3 = gm.createModel("River");
        sm = gm.getDefaultSM("River");
        sm.settings.get("width").setValue("50");
        sm.settings.get("length").setValue("100000");
        sm.settings.get("startDepth").setValue("1");
        sm.settings.get("floodDepth").setValue("4");;
        sm.settings.get("flowDepth").setValue("0.5");
        r3.onActualUpdateSettings(sm);
              
        
        Model s1 = gm.createModel("Sea");
        sm = gm.getDefaultSM("Sea");
        sm.settings.get("order").setValue("5");
        s1.onActualUpdateSettings(sm);
        
        Model weather = gm.createModel("Weather");
        
        
        gm.linkModelsWith(l1, l3, r1);
        gm.linkModelsWith(l2, l3, r2);
        gm.linkModelsWith(l3, s1, r3);
        
        
        if (!profilingRun) {
            gm.printOnDone = 0;
        }

        // Start the gamethread
        one.start();
    }
}