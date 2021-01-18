package ilpla.monitor;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class tracker_energia_subs_0 {


public static RemoteObject  _createnotification(RemoteObject _body) throws Exception{
try {
		Debug.PushSubsStack("CreateNotification (tracker_energia) ","tracker_energia",2,tracker_energia.processBA,tracker_energia.mostCurrent,25);
if (RapidSub.canDelegate("createnotification")) { return ilpla.monitor.tracker_energia.remoteMe.runUserSub(false, "tracker_energia","createnotification", _body);}
RemoteObject _notification = RemoteObject.declareNull("anywheresoftware.b4a.objects.NotificationWrapper");
Debug.locals.put("Body", _body);
 BA.debugLineNum = 25;BA.debugLine="Sub CreateNotification (Body As String) As Notific";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 26;BA.debugLine="Dim notification As Notification";
Debug.ShouldStop(33554432);
_notification = RemoteObject.createNew ("anywheresoftware.b4a.objects.NotificationWrapper");Debug.locals.put("notification", _notification);
 BA.debugLineNum = 27;BA.debugLine="notification.Initialize2(notification.IMPORTANCE_";
Debug.ShouldStop(67108864);
_notification.runVoidMethod ("Initialize2",(Object)(_notification.getField(true,"IMPORTANCE_LOW")));
 BA.debugLineNum = 28;BA.debugLine="notification.Icon = \"icon\"";
Debug.ShouldStop(134217728);
_notification.runVoidMethod ("setIcon",BA.ObjectToString("icon"));
 BA.debugLineNum = 29;BA.debugLine="notification.SetInfo(\"Controlando energía\", Body,";
Debug.ShouldStop(268435456);
_notification.runVoidMethod ("SetInfoNew",tracker_energia.processBA,(Object)(BA.ObjectToCharSequence("Controlando energía")),(Object)(BA.ObjectToCharSequence(_body)),(Object)((tracker_energia.mostCurrent._main.getObject())));
 BA.debugLineNum = 30;BA.debugLine="Return notification";
Debug.ShouldStop(536870912);
if (true) return _notification;
 BA.debugLineNum = 31;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable(null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _phoneevent_batterychanged(RemoteObject _level,RemoteObject _scale,RemoteObject _plugged,RemoteObject _intent) throws Exception{
try {
		Debug.PushSubsStack("PhoneEvent_BatteryChanged (tracker_energia) ","tracker_energia",2,tracker_energia.processBA,tracker_energia.mostCurrent,33);
if (RapidSub.canDelegate("phoneevent_batterychanged")) { return ilpla.monitor.tracker_energia.remoteMe.runUserSub(false, "tracker_energia","phoneevent_batterychanged", _level, _scale, _plugged, _intent);}
RemoteObject _estadoanterior = RemoteObject.createImmutable(false);
Debug.locals.put("Level", _level);
Debug.locals.put("Scale", _scale);
Debug.locals.put("Plugged", _plugged);
Debug.locals.put("Intent", _intent);
 BA.debugLineNum = 33;BA.debugLine="Sub PhoneEvent_BatteryChanged( Level As Int, Scale";
Debug.ShouldStop(1);
 BA.debugLineNum = 35;BA.debugLine="Dim estadoAnterior As Boolean";
Debug.ShouldStop(4);
_estadoanterior = RemoteObject.createImmutable(false);Debug.locals.put("estadoAnterior", _estadoanterior);
 BA.debugLineNum = 36;BA.debugLine="If Main.hayEnergia = True Then";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("=",tracker_energia.mostCurrent._main._hayenergia /*RemoteObject*/ ,tracker_energia.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 37;BA.debugLine="estadoAnterior = True";
Debug.ShouldStop(16);
_estadoanterior = tracker_energia.mostCurrent.__c.getField(true,"True");Debug.locals.put("estadoAnterior", _estadoanterior);
 };
 BA.debugLineNum = 40;BA.debugLine="If Plugged = False Then";
Debug.ShouldStop(128);
if (RemoteObject.solveBoolean("=",_plugged,tracker_energia.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 42;BA.debugLine="Main.hayEnergia = False";
Debug.ShouldStop(512);
tracker_energia.mostCurrent._main._hayenergia /*RemoteObject*/  = tracker_energia.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 43;BA.debugLine="If Main.monitorOn = True Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean("=",tracker_energia.mostCurrent._main._monitoron /*RemoteObject*/ ,tracker_energia.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 45;BA.debugLine="If estadoAnterior = True Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",_estadoanterior,tracker_energia.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 46;BA.debugLine="ToastMessageShow(\"Power out! Starting SMS time";
Debug.ShouldStop(8192);
tracker_energia.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Power out! Starting SMS timer")),(Object)(tracker_energia.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 47;BA.debugLine="CallSubDelayed(Main, \"Desconexion\")";
Debug.ShouldStop(16384);
tracker_energia.mostCurrent.__c.runVoidMethod ("CallSubDelayed",tracker_energia.processBA,(Object)((tracker_energia.mostCurrent._main.getObject())),(Object)(RemoteObject.createImmutable("Desconexion")));
 };
 };
 }else 
{ BA.debugLineNum = 51;BA.debugLine="Else If Plugged = True Then";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("=",_plugged,tracker_energia.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 53;BA.debugLine="Main.hayEnergia = True";
Debug.ShouldStop(1048576);
tracker_energia.mostCurrent._main._hayenergia /*RemoteObject*/  = tracker_energia.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 55;BA.debugLine="If Main.monitorOn = True Then";
Debug.ShouldStop(4194304);
if (RemoteObject.solveBoolean("=",tracker_energia.mostCurrent._main._monitoron /*RemoteObject*/ ,tracker_energia.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 57;BA.debugLine="If estadoAnterior = False Then";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("=",_estadoanterior,tracker_energia.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 58;BA.debugLine="ToastMessageShow(\"Power back! Starting SMS tim";
Debug.ShouldStop(33554432);
tracker_energia.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Power back! Starting SMS timer")),(Object)(tracker_energia.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 59;BA.debugLine="CallSubDelayed(Main, \"Conexion\")";
Debug.ShouldStop(67108864);
tracker_energia.mostCurrent.__c.runVoidMethod ("CallSubDelayed",tracker_energia.processBA,(Object)((tracker_energia.mostCurrent._main.getObject())),(Object)(RemoteObject.createImmutable("Conexion")));
 };
 };
 }}
;
 BA.debugLineNum = 63;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Private lock As PhoneWakeState";
tracker_energia._lock = RemoteObject.createNew ("anywheresoftware.b4a.phone.Phone.PhoneWakeState");
 //BA.debugLineNum = 8;BA.debugLine="Dim PhoneEvent As PhoneEvents";
tracker_energia._phoneevent = RemoteObject.createNew ("anywheresoftware.b4a.phone.PhoneEvents");
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _service_create() throws Exception{
try {
		Debug.PushSubsStack("Service_Create (tracker_energia) ","tracker_energia",2,tracker_energia.processBA,tracker_energia.mostCurrent,11);
if (RapidSub.canDelegate("service_create")) { return ilpla.monitor.tracker_energia.remoteMe.runUserSub(false, "tracker_energia","service_create");}
 BA.debugLineNum = 11;BA.debugLine="Sub Service_Create";
Debug.ShouldStop(1024);
 BA.debugLineNum = 12;BA.debugLine="Service.AutomaticForegroundMode = Service.AUTOMAT";
Debug.ShouldStop(2048);
tracker_energia.mostCurrent._service.setField ("AutomaticForegroundMode",tracker_energia.mostCurrent._service.getField(true,"AUTOMATIC_FOREGROUND_NEVER"));
 BA.debugLineNum = 13;BA.debugLine="lock.PartialLock";
Debug.ShouldStop(4096);
tracker_energia._lock.runVoidMethod ("PartialLock",tracker_energia.processBA);
 BA.debugLineNum = 14;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _service_destroy() throws Exception{
try {
		Debug.PushSubsStack("Service_Destroy (tracker_energia) ","tracker_energia",2,tracker_energia.processBA,tracker_energia.mostCurrent,21);
if (RapidSub.canDelegate("service_destroy")) { return ilpla.monitor.tracker_energia.remoteMe.runUserSub(false, "tracker_energia","service_destroy");}
 BA.debugLineNum = 21;BA.debugLine="Sub Service_Destroy";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 22;BA.debugLine="lock.ReleasePartialLock";
Debug.ShouldStop(2097152);
tracker_energia._lock.runVoidMethod ("ReleasePartialLock");
 BA.debugLineNum = 23;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _service_start(RemoteObject _startingintent) throws Exception{
try {
		Debug.PushSubsStack("Service_Start (tracker_energia) ","tracker_energia",2,tracker_energia.processBA,tracker_energia.mostCurrent,16);
if (RapidSub.canDelegate("service_start")) { return ilpla.monitor.tracker_energia.remoteMe.runUserSub(false, "tracker_energia","service_start", _startingintent);}
Debug.locals.put("StartingIntent", _startingintent);
 BA.debugLineNum = 16;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
Debug.ShouldStop(32768);
 BA.debugLineNum = 17;BA.debugLine="Service.StartForeground(1, CreateNotification(\"..";
Debug.ShouldStop(65536);
tracker_energia.mostCurrent._service.runVoidMethod ("StartForeground",(Object)(BA.numberCast(int.class, 1)),(Object)((_createnotification(RemoteObject.createImmutable("...")).getObject())));
 BA.debugLineNum = 18;BA.debugLine="PhoneEvent.Initialize(\"PhoneEvent\")";
Debug.ShouldStop(131072);
tracker_energia._phoneevent.runVoidMethod ("Initialize",tracker_energia.processBA,(Object)(RemoteObject.createImmutable("PhoneEvent")));
 BA.debugLineNum = 19;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}