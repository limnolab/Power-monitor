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
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, false, anywheresoftware.b4a.ShellBA.class);
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
		    processBA = new anywheresoftware.b4a.ShellBA(this, null, null, "ilpla.monitor", "ilpla.monitor.tracker_energia");
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
	}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.phone.Phone.PhoneWakeState _lock = null;
public static anywheresoftware.b4a.phone.PhoneEvents _phoneevent = null;
public ilpla.monitor.main _main = null;
public ilpla.monitor.starter _starter = null;
public static anywheresoftware.b4a.objects.NotificationWrapper  _createnotification(String _body) throws Exception{
RDebugUtils.currentModule="tracker_energia";
if (Debug.shouldDelegate(processBA, "createnotification", false))
	 {return ((anywheresoftware.b4a.objects.NotificationWrapper) Debug.delegate(processBA, "createnotification", new Object[] {_body}));}
anywheresoftware.b4a.objects.NotificationWrapper _notification = null;
RDebugUtils.currentLine=1703936;
 //BA.debugLineNum = 1703936;BA.debugLine="Sub CreateNotification (Body As String) As Notific";
RDebugUtils.currentLine=1703937;
 //BA.debugLineNum = 1703937;BA.debugLine="Dim notification As Notification";
_notification = new anywheresoftware.b4a.objects.NotificationWrapper();
RDebugUtils.currentLine=1703938;
 //BA.debugLineNum = 1703938;BA.debugLine="notification.Initialize2(notification.IMPORTANCE_";
_notification.Initialize2(_notification.IMPORTANCE_LOW);
RDebugUtils.currentLine=1703939;
 //BA.debugLineNum = 1703939;BA.debugLine="notification.Icon = \"icon\"";
_notification.setIcon("icon");
RDebugUtils.currentLine=1703940;
 //BA.debugLineNum = 1703940;BA.debugLine="notification.SetInfo(\"Controlando energía\", Body,";
_notification.SetInfoNew(processBA,BA.ObjectToCharSequence("Controlando energía"),BA.ObjectToCharSequence(_body),(Object)(mostCurrent._main.getObject()));
RDebugUtils.currentLine=1703941;
 //BA.debugLineNum = 1703941;BA.debugLine="Return notification";
if (true) return _notification;
RDebugUtils.currentLine=1703942;
 //BA.debugLineNum = 1703942;BA.debugLine="End Sub";
return null;
}
public static String  _phoneevent_batterychanged(int _level,int _scale,boolean _plugged,anywheresoftware.b4a.objects.IntentWrapper _intent) throws Exception{
RDebugUtils.currentModule="tracker_energia";
if (Debug.shouldDelegate(processBA, "phoneevent_batterychanged", false))
	 {return ((String) Debug.delegate(processBA, "phoneevent_batterychanged", new Object[] {_level,_scale,_plugged,_intent}));}
boolean _estadoanterior = false;
RDebugUtils.currentLine=1769472;
 //BA.debugLineNum = 1769472;BA.debugLine="Sub PhoneEvent_BatteryChanged( Level As Int, Scale";
RDebugUtils.currentLine=1769474;
 //BA.debugLineNum = 1769474;BA.debugLine="Dim estadoAnterior As Boolean";
_estadoanterior = false;
RDebugUtils.currentLine=1769475;
 //BA.debugLineNum = 1769475;BA.debugLine="If Main.hayEnergia = True Then";
if (mostCurrent._main._hayenergia /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=1769476;
 //BA.debugLineNum = 1769476;BA.debugLine="estadoAnterior = True";
_estadoanterior = anywheresoftware.b4a.keywords.Common.True;
 };
RDebugUtils.currentLine=1769479;
 //BA.debugLineNum = 1769479;BA.debugLine="If Plugged = False Then";
if (_plugged==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=1769481;
 //BA.debugLineNum = 1769481;BA.debugLine="Main.hayEnergia = False";
mostCurrent._main._hayenergia /*boolean*/  = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=1769482;
 //BA.debugLineNum = 1769482;BA.debugLine="If Main.monitorOn = True Then";
if (mostCurrent._main._monitoron /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=1769484;
 //BA.debugLineNum = 1769484;BA.debugLine="If estadoAnterior = True Then";
if (_estadoanterior==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=1769485;
 //BA.debugLineNum = 1769485;BA.debugLine="ToastMessageShow(\"Power out! Starting SMS time";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Power out! Starting SMS timer"),anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1769486;
 //BA.debugLineNum = 1769486;BA.debugLine="CallSubDelayed(Main, \"Desconexion\")";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._main.getObject()),"Desconexion");
 };
 };
 }else 
{RDebugUtils.currentLine=1769490;
 //BA.debugLineNum = 1769490;BA.debugLine="Else If Plugged = True Then";
if (_plugged==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=1769492;
 //BA.debugLineNum = 1769492;BA.debugLine="Main.hayEnergia = True";
mostCurrent._main._hayenergia /*boolean*/  = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=1769494;
 //BA.debugLineNum = 1769494;BA.debugLine="If Main.monitorOn = True Then";
if (mostCurrent._main._monitoron /*boolean*/ ==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=1769496;
 //BA.debugLineNum = 1769496;BA.debugLine="If estadoAnterior = False Then";
if (_estadoanterior==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=1769497;
 //BA.debugLineNum = 1769497;BA.debugLine="ToastMessageShow(\"Power back! Starting SMS tim";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Power back! Starting SMS timer"),anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1769498;
 //BA.debugLineNum = 1769498;BA.debugLine="CallSubDelayed(Main, \"Conexion\")";
anywheresoftware.b4a.keywords.Common.CallSubDelayed(processBA,(Object)(mostCurrent._main.getObject()),"Conexion");
 };
 };
 }}
;
RDebugUtils.currentLine=1769502;
 //BA.debugLineNum = 1769502;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
RDebugUtils.currentModule="tracker_energia";
if (Debug.shouldDelegate(processBA, "service_create", false))
	 {return ((String) Debug.delegate(processBA, "service_create", null));}
RDebugUtils.currentLine=1507328;
 //BA.debugLineNum = 1507328;BA.debugLine="Sub Service_Create";
RDebugUtils.currentLine=1507329;
 //BA.debugLineNum = 1507329;BA.debugLine="Service.AutomaticForegroundMode = Service.AUTOMAT";
mostCurrent._service.AutomaticForegroundMode = mostCurrent._service.AUTOMATIC_FOREGROUND_NEVER;
RDebugUtils.currentLine=1507330;
 //BA.debugLineNum = 1507330;BA.debugLine="lock.PartialLock";
_lock.PartialLock(processBA);
RDebugUtils.currentLine=1507331;
 //BA.debugLineNum = 1507331;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
RDebugUtils.currentModule="tracker_energia";
if (Debug.shouldDelegate(processBA, "service_destroy", false))
	 {return ((String) Debug.delegate(processBA, "service_destroy", null));}
RDebugUtils.currentLine=1638400;
 //BA.debugLineNum = 1638400;BA.debugLine="Sub Service_Destroy";
RDebugUtils.currentLine=1638401;
 //BA.debugLineNum = 1638401;BA.debugLine="lock.ReleasePartialLock";
_lock.ReleasePartialLock();
RDebugUtils.currentLine=1638402;
 //BA.debugLineNum = 1638402;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
RDebugUtils.currentModule="tracker_energia";
if (Debug.shouldDelegate(processBA, "service_start", false))
	 {return ((String) Debug.delegate(processBA, "service_start", new Object[] {_startingintent}));}
RDebugUtils.currentLine=1572864;
 //BA.debugLineNum = 1572864;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
RDebugUtils.currentLine=1572865;
 //BA.debugLineNum = 1572865;BA.debugLine="Service.StartForeground(1, CreateNotification(\"..";
mostCurrent._service.StartForeground((int) (1),(android.app.Notification)(_createnotification("...").getObject()));
RDebugUtils.currentLine=1572866;
 //BA.debugLineNum = 1572866;BA.debugLine="PhoneEvent.Initialize(\"PhoneEvent\")";
_phoneevent.Initialize(processBA,"PhoneEvent");
RDebugUtils.currentLine=1572867;
 //BA.debugLineNum = 1572867;BA.debugLine="End Sub";
return "";
}
}