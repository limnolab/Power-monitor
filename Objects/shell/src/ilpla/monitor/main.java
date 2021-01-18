
package ilpla.monitor;

import java.io.IOException;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RDebug;
import anywheresoftware.b4a.pc.RemoteObject;
import anywheresoftware.b4a.pc.RDebug.IRemote;
import anywheresoftware.b4a.pc.Debug;
import anywheresoftware.b4a.pc.B4XTypes.B4XClass;
import anywheresoftware.b4a.pc.B4XTypes.DeviceClass;

public class main implements IRemote{
	public static main mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public main() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
	public static void main (String[] args) throws Exception {
		new RDebug(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
		RDebug.INSTANCE.waitForTask();

	}
    static {
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("main"), "ilpla.monitor.main");
	}

public boolean isSingleton() {
		return true;
	}
     public static RemoteObject getObject() {
		return myClass;
	 }

	public RemoteObject activityBA;
	public RemoteObject _activity;
    private PCBA pcBA;

	public PCBA create(Object[] args) throws ClassNotFoundException{
		processBA = (RemoteObject) args[1];
		activityBA = (RemoteObject) args[2];
		_activity = (RemoteObject) args[3];
        anywheresoftware.b4a.keywords.Common.Density = (Float)args[4];
        remoteMe = (RemoteObject) args[5];
		pcBA = new PCBA(this, main.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _telefonos = RemoteObject.createImmutable("");
public static RemoteObject _minconnect = RemoteObject.createImmutable(0);
public static RemoteObject _mindisconnect = RemoteObject.createImmutable(0);
public static RemoteObject _monitoron = RemoteObject.createImmutable(false);
public static RemoteObject _avisoenviadoon = RemoteObject.createImmutable(false);
public static RemoteObject _avisoenviadooff = RemoteObject.createImmutable(false);
public static RemoteObject _hayenergia = RemoteObject.createImmutable(false);
public static RemoteObject _timerconnect = RemoteObject.declareNull("anywheresoftware.b4a.objects.Timer");
public static RemoteObject _timerdisconnect = RemoteObject.declareNull("anywheresoftware.b4a.objects.Timer");
public static RemoteObject _timerdisplay = RemoteObject.declareNull("anywheresoftware.b4a.objects.Timer");
public static RemoteObject _p = RemoteObject.declareNull("anywheresoftware.b4a.phone.Phone.PhoneSms");
public static RemoteObject _rp = RemoteObject.declareNull("anywheresoftware.b4a.objects.RuntimePermissions");
public static RemoteObject _pnlfondo = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _btnmonitoron = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _lbltimer = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _lblmonitoron = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _btnsaveconfig = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _txtmindisconnect = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _txtminconnect = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _txttelefonos = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static ilpla.monitor.starter _starter = null;
public static ilpla.monitor.tracker_energia _tracker_energia = null;
  public Object[] GetGlobals() {
		return new Object[] {"Activity",main.mostCurrent._activity,"avisoEnviadoOff",main._avisoenviadooff,"avisoEnviadoOn",main._avisoenviadoon,"btnMonitorOn",main.mostCurrent._btnmonitoron,"btnSaveConfig",main.mostCurrent._btnsaveconfig,"hayEnergia",main._hayenergia,"lblMonitorOn",main.mostCurrent._lblmonitoron,"lblTimer",main.mostCurrent._lbltimer,"minConnect",main._minconnect,"minDisconnect",main._mindisconnect,"monitorOn",main._monitoron,"p",main._p,"pnlFondo",main.mostCurrent._pnlfondo,"rp",main._rp,"Starter",Debug.moduleToString(ilpla.monitor.starter.class),"telefonos",main._telefonos,"timerConnect",main._timerconnect,"timerDisconnect",main._timerdisconnect,"timerDisplay",main._timerdisplay,"Tracker_Energia",Debug.moduleToString(ilpla.monitor.tracker_energia.class),"txtMinConnect",main.mostCurrent._txtminconnect,"txtMinDisconnect",main.mostCurrent._txtmindisconnect,"txtTelefonos",main.mostCurrent._txttelefonos};
}
}