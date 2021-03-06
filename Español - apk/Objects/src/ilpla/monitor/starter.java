package ilpla.monitor;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class starter extends  android.app.Service{
	public static class starter_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
            BA.LogInfo("** Receiver (starter) OnReceive **");
			android.content.Intent in = new android.content.Intent(context, starter.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, true, BA.class);
		}

	}
    static starter mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return starter.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "ilpla.monitor", "ilpla.monitor.starter");
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
			processBA.raiseEvent2(null, true, "CREATE", true, "ilpla.monitor.starter", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!true && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (starter) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (true) {
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
                    BA.LogInfo("** Service (starter) Create **");
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
        if (true)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (starter) Start **");
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
        if (true) {
            BA.LogInfo("** Service (starter) Destroy (ignored)**");
        }
        else {
            BA.LogInfo("** Service (starter) Destroy **");
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
public ilpla.monitor.main _main = null;
public ilpla.monitor.tracker_energia _tracker_energia = null;
public static boolean  _application_error(anywheresoftware.b4a.objects.B4AException _error,String _stacktrace) throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Application_Error (Error As Exception, StackTr";
 //BA.debugLineNum = 24;BA.debugLine="Return True";
if (true) return anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return false;
}
public static String  _cargarconfig() throws Exception{
anywheresoftware.b4a.objects.collections.Map _map1 = null;
 //BA.debugLineNum = 31;BA.debugLine="Sub CargarConfig";
 //BA.debugLineNum = 32;BA.debugLine="If File.Exists(File.DirInternal, \"settings.txt\")";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"settings.txt")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 33;BA.debugLine="Dim Map1 As Map";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 34;BA.debugLine="Map1 = File.ReadMap(File.DirInternal, \"settings.";
_map1 = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"settings.txt");
 //BA.debugLineNum = 35;BA.debugLine="Main.telefonos = Map1.Get(\"telefonos\")";
mostCurrent._main._telefonos /*String*/  = BA.ObjectToString(_map1.Get((Object)("telefonos")));
 //BA.debugLineNum = 36;BA.debugLine="Main.minDisconnect = Map1.Get(\"minDisconnect\") *";
mostCurrent._main._mindisconnect /*int*/  = (int) ((double)(BA.ObjectToNumber(_map1.Get((Object)("minDisconnect"))))*1000);
 //BA.debugLineNum = 37;BA.debugLine="Main.minConnect = Map1.Get(\"minConnect\") * 1000";
mostCurrent._main._minconnect /*int*/  = (int) ((double)(BA.ObjectToNumber(_map1.Get((Object)("minConnect"))))*1000);
 //BA.debugLineNum = 39;BA.debugLine="If Main.telefonos = \"\" Then";
if ((mostCurrent._main._telefonos /*String*/ ).equals("")) { 
 //BA.debugLineNum = 40;BA.debugLine="Main.telefonos = \"\"";
mostCurrent._main._telefonos /*String*/  = "";
 };
 //BA.debugLineNum = 42;BA.debugLine="If Main.minConnect = 0 Then";
if (mostCurrent._main._minconnect /*int*/ ==0) { 
 //BA.debugLineNum = 43;BA.debugLine="Main.minConnect = 6000";
mostCurrent._main._minconnect /*int*/  = (int) (6000);
 };
 //BA.debugLineNum = 45;BA.debugLine="If Main.minDisconnect = 0 Then";
if (mostCurrent._main._mindisconnect /*int*/ ==0) { 
 //BA.debugLineNum = 46;BA.debugLine="Main.minDisconnect = 6000";
mostCurrent._main._mindisconnect /*int*/  = (int) (6000);
 };
 }else {
 //BA.debugLineNum = 49;BA.debugLine="Dim Map1 As Map";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 50;BA.debugLine="Map1 = File.ReadMap(File.DirAssets, \"settings.tx";
_map1 = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),"settings.txt");
 //BA.debugLineNum = 51;BA.debugLine="Main.telefonos = Map1.Get(\"telefonos\")";
mostCurrent._main._telefonos /*String*/  = BA.ObjectToString(_map1.Get((Object)("telefonos")));
 //BA.debugLineNum = 52;BA.debugLine="Main.minDisconnect = Map1.Get(\"minDisconnect\") *";
mostCurrent._main._mindisconnect /*int*/  = (int) ((double)(BA.ObjectToNumber(_map1.Get((Object)("minDisconnect"))))*1000);
 //BA.debugLineNum = 53;BA.debugLine="Main.minConnect = Map1.Get(\"minConnect\") * 1000";
mostCurrent._main._minconnect /*int*/  = (int) ((double)(BA.ObjectToNumber(_map1.Get((Object)("minConnect"))))*1000);
 //BA.debugLineNum = 56;BA.debugLine="Main.minDisconnect = 6000";
mostCurrent._main._mindisconnect /*int*/  = (int) (6000);
 //BA.debugLineNum = 57;BA.debugLine="Main.minConnect = 6000";
mostCurrent._main._minconnect /*int*/  = (int) (6000);
 };
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 7;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 16;BA.debugLine="CargarConfig";
_cargarconfig();
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public static String  _service_taskremoved() throws Exception{
 //BA.debugLineNum = 20;BA.debugLine="Sub Service_TaskRemoved";
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return "";
}
}
