
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

public class tracker_energia implements IRemote{
	public static tracker_energia mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public tracker_energia() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
public boolean isSingleton() {
		return true;
	}
    static {
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("tracker_energia"), "ilpla.monitor.tracker_energia");
	}
     public static RemoteObject getObject() {
		return myClass;
	 }
	public RemoteObject _service;
    private PCBA pcBA;

	public PCBA create(Object[] args) throws ClassNotFoundException{
		processBA = (RemoteObject) args[1];
        _service = (RemoteObject) args[2];
        remoteMe = RemoteObject.declareNull("ilpla.monitor.tracker_energia");
        anywheresoftware.b4a.keywords.Common.Density = (Float)args[3];
		pcBA = new PCBA(this, tracker_energia.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _lock = RemoteObject.declareNull("anywheresoftware.b4a.phone.Phone.PhoneWakeState");
public static RemoteObject _phoneevent = RemoteObject.declareNull("anywheresoftware.b4a.phone.PhoneEvents");
public static ilpla.monitor.main _main = null;
public static ilpla.monitor.starter _starter = null;
  public Object[] GetGlobals() {
		return new Object[] {"lock",tracker_energia._lock,"Main",Debug.moduleToString(ilpla.monitor.main.class),"PhoneEvent",tracker_energia._phoneevent,"Service",tracker_energia.mostCurrent._service,"Starter",Debug.moduleToString(ilpla.monitor.starter.class)};
}
}