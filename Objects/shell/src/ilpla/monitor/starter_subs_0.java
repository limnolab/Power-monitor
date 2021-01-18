package ilpla.monitor;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class starter_subs_0 {


public static RemoteObject  _application_error(RemoteObject _error,RemoteObject _stacktrace) throws Exception{
try {
		Debug.PushSubsStack("Application_Error (starter) ","starter",1,starter.processBA,starter.mostCurrent,22);
if (RapidSub.canDelegate("application_error")) { return ilpla.monitor.starter.remoteMe.runUserSub(false, "starter","application_error", _error, _stacktrace);}
Debug.locals.put("Error", _error);
Debug.locals.put("StackTrace", _stacktrace);
 BA.debugLineNum = 22;BA.debugLine="Sub Application_Error (Error As Exception, StackTr";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 23;BA.debugLine="Return True";
Debug.ShouldStop(4194304);
if (true) return starter.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 24;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable(false);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _cargarconfig() throws Exception{
try {
		Debug.PushSubsStack("CargarConfig (starter) ","starter",1,starter.processBA,starter.mostCurrent,30);
if (RapidSub.canDelegate("cargarconfig")) { return ilpla.monitor.starter.remoteMe.runUserSub(false, "starter","cargarconfig");}
RemoteObject _map1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 30;BA.debugLine="Sub CargarConfig";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 31;BA.debugLine="If File.Exists(File.DirInternal, \"settings.txt\")";
Debug.ShouldStop(1073741824);
if (RemoteObject.solveBoolean("=",starter.mostCurrent.__c.getField(false,"File").runMethod(true,"Exists",(Object)(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("settings.txt"))),starter.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 32;BA.debugLine="Dim Map1 As Map";
Debug.ShouldStop(-2147483648);
_map1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("Map1", _map1);
 BA.debugLineNum = 33;BA.debugLine="Map1 = File.ReadMap(File.DirInternal, \"settings.";
Debug.ShouldStop(1);
_map1 = starter.mostCurrent.__c.getField(false,"File").runMethod(false,"ReadMap",(Object)(starter.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("settings.txt")));Debug.locals.put("Map1", _map1);
 BA.debugLineNum = 34;BA.debugLine="Main.telefonos = Map1.Get(\"telefonos\")";
Debug.ShouldStop(2);
starter.mostCurrent._main._telefonos /*RemoteObject*/  = BA.ObjectToString(_map1.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("telefonos")))));
 BA.debugLineNum = 35;BA.debugLine="Main.minDisconnect = Map1.Get(\"minDisconnect\")";
Debug.ShouldStop(4);
starter.mostCurrent._main._mindisconnect /*RemoteObject*/  = BA.numberCast(int.class, _map1.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("minDisconnect")))));
 BA.debugLineNum = 36;BA.debugLine="Main.minConnect = Map1.Get(\"minConnect\")";
Debug.ShouldStop(8);
starter.mostCurrent._main._minconnect /*RemoteObject*/  = BA.numberCast(int.class, _map1.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("minConnect")))));
 BA.debugLineNum = 38;BA.debugLine="If Main.telefonos = \"\" Then";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("=",starter.mostCurrent._main._telefonos /*RemoteObject*/ ,BA.ObjectToString(""))) { 
 BA.debugLineNum = 39;BA.debugLine="Main.telefonos = \"\"";
Debug.ShouldStop(64);
starter.mostCurrent._main._telefonos /*RemoteObject*/  = BA.ObjectToString("");
 };
 BA.debugLineNum = 41;BA.debugLine="If Main.minConnect = 0 Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("=",starter.mostCurrent._main._minconnect /*RemoteObject*/ ,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 42;BA.debugLine="Main.minConnect = 600000";
Debug.ShouldStop(512);
starter.mostCurrent._main._minconnect /*RemoteObject*/  = BA.numberCast(int.class, 600000);
 };
 BA.debugLineNum = 44;BA.debugLine="If Main.minDisconnect = 0 Then";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean("=",starter.mostCurrent._main._mindisconnect /*RemoteObject*/ ,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 45;BA.debugLine="Main.minDisconnect = 60000";
Debug.ShouldStop(4096);
starter.mostCurrent._main._mindisconnect /*RemoteObject*/  = BA.numberCast(int.class, 60000);
 };
 }else {
 BA.debugLineNum = 49;BA.debugLine="Main.minDisconnect = 600000";
Debug.ShouldStop(65536);
starter.mostCurrent._main._mindisconnect /*RemoteObject*/  = BA.numberCast(int.class, 600000);
 BA.debugLineNum = 50;BA.debugLine="Main.minConnect = 600000";
Debug.ShouldStop(131072);
starter.mostCurrent._main._minconnect /*RemoteObject*/  = BA.numberCast(int.class, 600000);
 };
 BA.debugLineNum = 52;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
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
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _service_create() throws Exception{
try {
		Debug.PushSubsStack("Service_Create (starter) ","starter",1,starter.processBA,starter.mostCurrent,9);
if (RapidSub.canDelegate("service_create")) { return ilpla.monitor.starter.remoteMe.runUserSub(false, "starter","service_create");}
 BA.debugLineNum = 9;BA.debugLine="Sub Service_Create";
Debug.ShouldStop(256);
 BA.debugLineNum = 12;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
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
		Debug.PushSubsStack("Service_Destroy (starter) ","starter",1,starter.processBA,starter.mostCurrent,26);
if (RapidSub.canDelegate("service_destroy")) { return ilpla.monitor.starter.remoteMe.runUserSub(false, "starter","service_destroy");}
 BA.debugLineNum = 26;BA.debugLine="Sub Service_Destroy";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 28;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
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
		Debug.PushSubsStack("Service_Start (starter) ","starter",1,starter.processBA,starter.mostCurrent,14);
if (RapidSub.canDelegate("service_start")) { return ilpla.monitor.starter.remoteMe.runUserSub(false, "starter","service_start", _startingintent);}
Debug.locals.put("StartingIntent", _startingintent);
 BA.debugLineNum = 14;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
Debug.ShouldStop(8192);
 BA.debugLineNum = 15;BA.debugLine="CargarConfig";
Debug.ShouldStop(16384);
_cargarconfig();
 BA.debugLineNum = 17;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _service_taskremoved() throws Exception{
try {
		Debug.PushSubsStack("Service_TaskRemoved (starter) ","starter",1,starter.processBA,starter.mostCurrent,19);
if (RapidSub.canDelegate("service_taskremoved")) { return ilpla.monitor.starter.remoteMe.runUserSub(false, "starter","service_taskremoved");}
 BA.debugLineNum = 19;BA.debugLine="Sub Service_TaskRemoved";
Debug.ShouldStop(262144);
 BA.debugLineNum = 20;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}