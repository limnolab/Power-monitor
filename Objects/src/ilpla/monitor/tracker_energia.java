package ilpla.monitor;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class tracker_energia extends  android.app.Service{
	public static class tracker_energia_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
            BA.LogInfo("** Receiver (tracker_energia) OnReceive **");
			android.content.Intent in = new android.content.Intent(context, tracker_energia.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, false, BA.class);
		}

	}
    static tracker_energia mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return tracker_energia.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "ilpla.monitor", "ilpla.monitor.tracker_energia");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "ilpla.monitor.tracker_energia", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!false && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (tracker_energia) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (false) {
			ServiceHelper.StarterHelper.runWaitForLayouts();
		}
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		onStartCommand(intent, 0, 0);
    }
    @Override
    public int onStartCommand(final android.content.Intent intent, int flags, int startId) {
    	if (ServiceHelper.StarterHelper.onStartCommand(processBA, new Runnable() {
            public void run() {
                handleStart(intent);
            }}))
			;
		else {
			ServiceHelper.StarterHelper.addWaitForLayout (new Runnable() {
				public void run() {
                    processBA.setActivityPaused(false);
                    BA.LogInfo("** Service (tracker_energia) Create **");
                    processBA.raiseEvent(null, "service_create");
					handleStart(intent);
                    ServiceHelper.StarterHelper.removeWaitForLayout();
				}
			});
		}
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    public void onTaskRemoved(android.content.Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (false)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (tracker_energia) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = ServiceHelper.StarterHelper.handleStartIntent(intent, _service, processBA);
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	
	@Override
	public void onDestroy() {
        super.onDestroy();
        if (false) {
            BA.LogInfo("** Service (tracker_energia) Destroy (ignored)**");
        }
        else {
            BA.LogInfo("** Service (tracker_energia) Destroy **");
		    processBA.raiseEvent(null, "service_destroy");
            processBA.service = null;
		    mostCurrent = null;
		    processBA.setActivityPaused(true);
            processBA.runHook("ondestroy", this, null);
        }
	}

@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.phone.Phone.PhoneWakeState _lock = null;
public static anywheresoftware.b4a.phone.PhoneEvents _phoneevent = null;
public ilpla.monitor.main _main = null;
public ilpla.monitor.starter _starter = null;
public static anywheresoftware.b4a.objects.NotificationWrapper  _createnotification(String _body) throws Exception{
anywheresoftware.b4a.objects.NotificationWrapper _notification = null;
 //BA.debugLineNum = 26;BA.debugLine="Sub CreateNotification (Body As String) As Notific";
 //BA.debugLineNum = 27;BA.debugLine="Dim notification As Notification";
_notification = new anywheresoftware.b4a.objects.NotificationWrapper();
 //BA.debugLineNum = 28;BA.debugLine="notification.Initialize2(notification.IMPORTANCE_";
_notification.Initialize2(_notification.IMPORTANCE_LOW);
 //BA.debugLineNum = 29;BA.debugLine="notification.Icon = \"icon\"";
_notification.setIcon("icon");
 //BA.debugLineNum = 30;BA.debugLine="notification.SetInfo(\"Checking power\", Body, Main";
_notification.SetInfoNew(processBA,BA.ObjectToCharSequence("Checking power"),BA.ObjectToCharSequence(_body),(Object)(mostCurrent._main.getObject()));
 //BA.debugLineNum = 31;BA.debugLine="Return notification";
if (true) return _notification;
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return null;
}
public static String  _phoneevent_batterychanged(int _level,int _scale,boolean _plugged,anywheresoftware.b4a.objects.IntentWrapper _intent) throws Exception{
boolean _estadoanterior = false;
 //BA.debugLineNum = 34;BA.debugLine="Sub PhoneEvent_BatteryChanged( Level As Int, Scale";
 //BA.debugLineNum = 36;BA.debugLine="Dim estadoAnterior As Boolean";
_estadoanterior = false;
 //BA.debugLineNum = 37;BA.debugLine="If Main.hayEnergia = True Then";
if (mostCurrent._main._hayenergia /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 38;BA.debugLine="estadoAnterior = True";
_estadoanterior = anywheresoftware.b4a.keywords.Common.True;
 };
 //BA.debugLineNum = 41;BA.debugLine="If Plugged = False Then";
if (_plugged==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 43;BA.debugLine="Main.hayEnergia = False";
mostCurrent._main._hayenergia /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 44;BA.debugLine="If Main.monitorOn = True Then";
if (mostCurrent._main._monitoron /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 46;BA.debugLine="If estadoAnterior = True Then";
if (_estadoanterior==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 47;BA.debugLine="ToastMessageShow(\"Power out! Starting SMS time";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Power out! Starting SMS timer"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 48;BA.debugLine="CallSubDelayed(Main, \"Desconexion\")";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._main.getObject()),"Desconexion");
 };
 };
 }else if(_plugged==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 54;BA.debugLine="Main.hayEnergia = True";
mostCurrent._main._hayenergia /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 56;BA.debugLine="If Main.monitorOn = True Then";
if (mostCurrent._main._monitoron /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 58;BA.debugLine="If estadoAnterior = False Then";
if (_estadoanterior==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 59;BA.debugLine="ToastMessageShow(\"Power back! Starting SMS tim";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Power back! Starting SMS timer"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 60;BA.debugLine="CallSubDelayed(Main, \"Conexion\")";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._main.getObject()),"Conexion");
 };
 };
 };
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="Private lock As PhoneWakeState";
_lock = new anywheresoftware.b4a.phone.Phone.PhoneWakeState();
 //BA.debugLineNum = 9;BA.debugLine="Dim PhoneEvent As PhoneEvents";
_phoneevent = new anywheresoftware.b4a.phone.PhoneEvents();
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 13;BA.debugLine="Service.AutomaticForegroundMode = Service.AUTOMAT";
mostCurrent._service.AutomaticForegroundMode = mostCurrent._service.AUTOMATIC_FOREGROUND_NEVER;
 //BA.debugLineNum = 14;BA.debugLine="lock.PartialLock";
_lock.PartialLock(processBA);
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 22;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 23;BA.debugLine="lock.ReleasePartialLock";
_lock.ReleasePartialLock();
 //BA.debugLineNum = 24;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 17;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 18;BA.debugLine="Service.StartForeground(1, CreateNotification(\"..";
mostCurrent._service.StartForeground((int) (1),(android.app.Notification)(_createnotification("...").getObject()));
 //BA.debugLineNum = 19;BA.debugLine="PhoneEvent.Initialize(\"PhoneEvent\")";
_phoneevent.Initialize(processBA,"PhoneEvent");
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
}
