package fi.paivola.mapserver.core;

import fi.paivola.mapserver.core.setting.SettingMaster;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Meet WSServer, our communications manager.
 * @author juhani
 */
public class WSServer extends WebSocketServer {
    
    private final JSONParser parser = new JSONParser();
    private final Map<String, GameThread> threads;
    private final static Logger log = Logger.getLogger("mapserver");

    public WSServer(int port) throws UnknownHostException {
        super(new InetSocketAddress(port));
        threads = new HashMap<>();
        log.log(Level.INFO, "created server on port: {0}", port);
    }

    public WSServer(InetSocketAddress address) {
        super(address);
        threads = new HashMap<>();
        log.log(Level.INFO, "created server on port: {0}", address.getPort());
    }

    @Override
    public void onOpen(WebSocket ws, ClientHandshake ch) {

    }

    @Override
    public void onClose(WebSocket ws, int i, String string, boolean bln) {
 
    }

    @Override
    public void onMessage(WebSocket ws, String string) {
        JSONObject obj = null;
        JSONObject responce = new JSONObject();
        
        try {
            obj = (JSONObject)parser.parse(string);
        } catch (ParseException ex) {
            Logger.getLogger(WSServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(obj == null || !obj.containsKey("action")) {
            return;
        }
        
        responce.put("action", obj.get("action"));
        
        switch(obj.get("action").toString()) {
            case "create":
                this.callCreate(obj, responce);
                break;
            case "start":
                this.callStart(obj, responce);
                break;
            case "add":
                this.callAdd(obj, responce);
                break;
            case "getdata":
                this.callGetdata(obj, responce);
                break;
            case "info":
                this.callInfo(obj, responce);
                break;
            default:
                break;
        }
        
        ws.send(obj.toJSONString());
    }
    
    private void callCreate(JSONObject in, JSONObject out) {
        int ticks;
        if(in.containsKey("ticks")) {
            ticks = Integer.parseInt(in.get("ticks").toString());
        }else{
            ticks = Integer.parseInt(SettingsParser.settings.get("default_ticks").toString());
        }
        GameThread newthread = new GameThread(ticks);
        this.threads.put(""+this.threads.size(), newthread);
        out.put("manager_id", ""+(this.threads.size()-1));
    }
    
    private void callStart(JSONObject in, JSONObject out) {
        GameThread gt;
        if((gt = getThread(in, out)) == null)
            return;
        gt.start();
        success(out);
    }
    
    private void callAdd(JSONObject in, JSONObject out) {
        GameThread gt;
        if((gt = getThread(in, out)) == null)
            return;
        
        String type = "undefined";
        SettingMaster sm;
        if(in.containsKey("type"))
            type = in.get("type").toString();
        if(in.containsKey("settings"))
            sm = SettingMaster.fromJSON(in.get("settings").toString());
        else
            return;
        Model mod = gt.game.createModel(type, sm);
        gt.game.addModel(mod, type);
        out.put("model_id", mod.id);
        success(out);
    }
    
    private void callInfo(JSONObject in, JSONObject out) {
        GameThread gt;
        if((gt = getThread(in, out)) == null)
            return;
        Model mod;
        if((mod = getModel(in, out, gt.game)) == null)
            return;
        
        out.put("model", mod);
        success(out);
    }
    
    private void callGetdata(JSONObject in, JSONObject out) {
        GameThread gt;
        if((gt = getThread(in, out)) == null)
            return;
        if(!gt.game.ready) {
            error(out, "Run not completed yet");
            return;
        }
        out.put("data", gt.game.getData());
        success(out);
    }
    
    private void error(JSONObject out, String message) {
        out.put("status", "error");
        out.put("error", message);
    }
    
    private void success(JSONObject out) {
        out.put("status", "success");
    }
    
    private GameThread getThread(JSONObject in, JSONObject out) {
        if(!in.containsKey("manager_id") || !this.threads.containsKey(in.get("manager_id").toString())) {
            error(out, "You need to provide a existing manager_id!");
            return null;
        }
        return this.threads.get(in.get("manager_id").toString());
    }
    
    private Model getModel(JSONObject in, JSONObject out, GameManager gm) {
        if(in.containsKey("model_id") || !gm.containsModel(Integer.parseInt(in.get("model_id").toString()))) {
            error(out, "You need to provide a existing model_id!");
            return null;
        }
        return gm.getActive(in.get("model_id").toString());
    }

    @Override
    public void onError(WebSocket ws, Exception excptn) {

    }

}
