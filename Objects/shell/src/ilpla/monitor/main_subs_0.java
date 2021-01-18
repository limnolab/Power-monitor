package ilpla.monitor;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static void  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,57);
if (RapidSub.canDelegate("activity_create")) { ilpla.monitor.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime); return;}
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(ilpla.monitor.main parent,RemoteObject _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
ilpla.monitor.main parent;
RemoteObject _firsttime;
RemoteObject _permission = RemoteObject.createImmutable("");
RemoteObject _result = RemoteObject.createImmutable(false);
RemoteObject group3;
int index3;
int groupLen3;

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,57);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 58;BA.debugLine="Activity.LoadLayout(\"MainLayout\")";
Debug.ShouldStop(33554432);
parent.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("MainLayout")),main.mostCurrent.activityBA);
 BA.debugLineNum = 60;BA.debugLine="If FirstTime Then";
Debug.ShouldStop(134217728);
if (true) break;

case 1:
//if
this.state = 12;
if (_firsttime.<Boolean>get().booleanValue()) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 BA.debugLineNum = 61;BA.debugLine="For Each Permission As String In Array(rp.PERMIS";
Debug.ShouldStop(268435456);
if (true) break;

case 4:
//for
this.state = 11;
group3 = RemoteObject.createNewArray("Object",new int[] {2},new Object[] {(parent._rp.getField(true,"PERMISSION_READ_PHONE_STATE")),(parent._rp.getField(true,"PERMISSION_SEND_SMS"))});
index3 = 0;
groupLen3 = group3.getField(true,"length").<Integer>get();
Debug.locals.put("Permission", _permission);
this.state = 16;
if (true) break;

case 16:
//C
this.state = 11;
if (index3 < groupLen3) {
this.state = 6;
_permission = BA.ObjectToString(group3.getArrayElement(false,RemoteObject.createImmutable(index3)));Debug.locals.put("Permission", _permission);}
if (true) break;

case 17:
//C
this.state = 16;
index3++;
Debug.locals.put("Permission", _permission);
if (true) break;

case 6:
//C
this.state = 7;
 BA.debugLineNum = 62;BA.debugLine="Sleep(100)";
Debug.ShouldStop(536870912);
parent.mostCurrent.__c.runVoidMethod ("Sleep",main.mostCurrent.activityBA,anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "activity_create"),BA.numberCast(int.class, 100));
this.state = 18;
return;
case 18:
//C
this.state = 7;
;
 BA.debugLineNum = 63;BA.debugLine="rp.CheckAndRequest(Permission)";
Debug.ShouldStop(1073741824);
parent._rp.runVoidMethod ("CheckAndRequest",main.processBA,(Object)(_permission));
 BA.debugLineNum = 64;BA.debugLine="Wait For Activity_PermissionResult (Permission";
Debug.ShouldStop(-2147483648);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","activity_permissionresult", main.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "activity_create"), null);
this.state = 19;
return;
case 19:
//C
this.state = 7;
_permission = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Permission", _permission);
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(1));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 65;BA.debugLine="If Result = False Then";
Debug.ShouldStop(1);
if (true) break;

case 7:
//if
this.state = 10;
if (RemoteObject.solveBoolean("=",_result,parent.mostCurrent.__c.getField(true,"False"))) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 BA.debugLineNum = 66;BA.debugLine="ToastMessageShow(\"No permissions set\", False)";
Debug.ShouldStop(2);
parent.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("No permissions set")),(Object)(parent.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 67;BA.debugLine="Log(\"No Permission: \" & Permission)";
Debug.ShouldStop(4);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","5131082",RemoteObject.concat(RemoteObject.createImmutable("No Permission: "),_permission),0);
 BA.debugLineNum = 68;BA.debugLine="ExitApplication";
Debug.ShouldStop(8);
parent.mostCurrent.__c.runVoidMethod ("ExitApplication");
 if (true) break;

case 10:
//C
this.state = 17;
;
 if (true) break;
if (true) break;

case 11:
//C
this.state = 12;
Debug.locals.put("Permission", _permission);
;
 BA.debugLineNum = 72;BA.debugLine="Log(\"All permissions OK\")";
Debug.ShouldStop(128);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","5131087",RemoteObject.createImmutable("All permissions OK"),0);
 if (true) break;
;
 BA.debugLineNum = 74;BA.debugLine="If FirstTime Then";
Debug.ShouldStop(512);

case 12:
//if
this.state = 15;
if (_firsttime.<Boolean>get().booleanValue()) { 
this.state = 14;
}if (true) break;

case 14:
//C
this.state = 15;
 BA.debugLineNum = 75;BA.debugLine="monitorOn = False";
Debug.ShouldStop(1024);
parent._monitoron = parent.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 76;BA.debugLine="avisoEnviadoOn = False";
Debug.ShouldStop(2048);
parent._avisoenviadoon = parent.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 77;BA.debugLine="avisoEnviadoOff = False";
Debug.ShouldStop(4096);
parent._avisoenviadooff = parent.mostCurrent.__c.getField(true,"False");
 if (true) break;

case 15:
//C
this.state = -1;
;
 BA.debugLineNum = 79;BA.debugLine="CargarConfigTxt";
Debug.ShouldStop(16384);
_cargarconfigtxt();
 BA.debugLineNum = 81;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _activity_permissionresult(RemoteObject _permission,RemoteObject _result) throws Exception{
}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,89);
if (RapidSub.canDelegate("activity_pause")) { return ilpla.monitor.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 89;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 91;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,82);
if (RapidSub.canDelegate("activity_resume")) { ilpla.monitor.main.remoteMe.runUserSub(false, "main","activity_resume"); return;}
ResumableSub_Activity_Resume rsub = new ResumableSub_Activity_Resume(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_Activity_Resume extends BA.ResumableSub {
public ResumableSub_Activity_Resume(ilpla.monitor.main parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
ilpla.monitor.main parent;
RemoteObject _permission = RemoteObject.createImmutable("");
RemoteObject _result = RemoteObject.createImmutable(false);

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,82);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 83;BA.debugLine="Wait For Activity_PermissionResult (Permission As";
Debug.ShouldStop(262144);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","activity_permissionresult", main.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "activity_resume"), null);
this.state = 5;
return;
case 5:
//C
this.state = 1;
_permission = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Permission", _permission);
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(1));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 84;BA.debugLine="If Result Then";
Debug.ShouldStop(524288);
if (true) break;

case 1:
//if
this.state = 4;
if (_result.<Boolean>get().booleanValue()) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 BA.debugLineNum = 86;BA.debugLine="StartService(Tracker_Energia)";
Debug.ShouldStop(2097152);
parent.mostCurrent.__c.runVoidMethod ("StartService",main.processBA,(Object)((parent.mostCurrent._tracker_energia.getObject())));
 if (true) break;

case 4:
//C
this.state = -1;
;
 BA.debugLineNum = 88;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static RemoteObject  _btnmonitoron_click() throws Exception{
try {
		Debug.PushSubsStack("btnMonitorOn_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,203);
if (RapidSub.canDelegate("btnmonitoron_click")) { return ilpla.monitor.main.remoteMe.runUserSub(false, "main","btnmonitoron_click");}
 BA.debugLineNum = 203;BA.debugLine="Sub btnMonitorOn_Click 'BUTTON TURNS POWER MONITOR";
Debug.ShouldStop(1024);
 BA.debugLineNum = 204;BA.debugLine="If monitorOn = False Then";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean("=",main._monitoron,main.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 205;BA.debugLine="cambiarColor 'Changes color";
Debug.ShouldStop(4096);
_cambiarcolor();
 BA.debugLineNum = 206;BA.debugLine="monitorOn = True";
Debug.ShouldStop(8192);
main._monitoron = main.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 207;BA.debugLine="ToastMessageShow(\"Energy monitor on\", False)";
Debug.ShouldStop(16384);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Energy monitor on")),(Object)(main.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 208;BA.debugLine="lblMonitorOn.Text = \"Monitor ON\"";
Debug.ShouldStop(32768);
main.mostCurrent._lblmonitoron.runMethod(true,"setText",BA.ObjectToCharSequence("Monitor ON"));
 BA.debugLineNum = 209;BA.debugLine="btnMonitorOn.Text = \"OFF\"";
Debug.ShouldStop(65536);
main.mostCurrent._btnmonitoron.runMethod(true,"setText",BA.ObjectToCharSequence("OFF"));
 BA.debugLineNum = 210;BA.debugLine="txtTelefonos.Enabled = False";
Debug.ShouldStop(131072);
main.mostCurrent._txttelefonos.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 211;BA.debugLine="txtMinConnect.Enabled = False";
Debug.ShouldStop(262144);
main.mostCurrent._txtminconnect.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 212;BA.debugLine="txtMinDisconnect.Enabled = False";
Debug.ShouldStop(524288);
main.mostCurrent._txtmindisconnect.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 }else {
 BA.debugLineNum = 214;BA.debugLine="monitorOn = False";
Debug.ShouldStop(2097152);
main._monitoron = main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 215;BA.debugLine="ToastMessageShow(\"Energy monitor off\", False)";
Debug.ShouldStop(4194304);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Energy monitor off")),(Object)(main.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 216;BA.debugLine="lblMonitorOn.Text = \"Monitor OFF\"";
Debug.ShouldStop(8388608);
main.mostCurrent._lblmonitoron.runMethod(true,"setText",BA.ObjectToCharSequence("Monitor OFF"));
 BA.debugLineNum = 217;BA.debugLine="btnMonitorOn.Text = \"ON\"";
Debug.ShouldStop(16777216);
main.mostCurrent._btnmonitoron.runMethod(true,"setText",BA.ObjectToCharSequence("ON"));
 BA.debugLineNum = 218;BA.debugLine="lblTimer.Text = \"\"";
Debug.ShouldStop(33554432);
main.mostCurrent._lbltimer.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 219;BA.debugLine="pnlFondo.Color = Colors.RGB(72,72,72)";
Debug.ShouldStop(67108864);
main.mostCurrent._pnlfondo.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 72)),(Object)(BA.numberCast(int.class, 72)),(Object)(BA.numberCast(int.class, 72))));
 BA.debugLineNum = 220;BA.debugLine="timerDisplay.Enabled = False";
Debug.ShouldStop(134217728);
main._timerdisplay.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 221;BA.debugLine="timerConnect.Enabled = False";
Debug.ShouldStop(268435456);
main._timerconnect.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 222;BA.debugLine="timerDisconnect. Enabled = False";
Debug.ShouldStop(536870912);
main._timerdisconnect.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 223;BA.debugLine="txtTelefonos.Enabled = True";
Debug.ShouldStop(1073741824);
main.mostCurrent._txttelefonos.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 224;BA.debugLine="txtMinConnect.Enabled = True";
Debug.ShouldStop(-2147483648);
main.mostCurrent._txtminconnect.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 225;BA.debugLine="txtMinDisconnect.Enabled = True";
Debug.ShouldStop(1);
main.mostCurrent._txtmindisconnect.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 };
 BA.debugLineNum = 228;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnsaveconfig_click() throws Exception{
try {
		Debug.PushSubsStack("btnSaveConfig_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,95);
if (RapidSub.canDelegate("btnsaveconfig_click")) { return ilpla.monitor.main.remoteMe.runUserSub(false, "main","btnsaveconfig_click");}
RemoteObject _map1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 95;BA.debugLine="Sub btnSaveConfig_Click 'Save config button";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 96;BA.debugLine="Dim Map1 As Map";
Debug.ShouldStop(-2147483648);
_map1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("Map1", _map1);
 BA.debugLineNum = 97;BA.debugLine="Map1.Initialize";
Debug.ShouldStop(1);
_map1.runVoidMethod ("Initialize");
 BA.debugLineNum = 98;BA.debugLine="Map1.Put(\"telefonos\", txtTelefonos.Text)";
Debug.ShouldStop(2);
_map1.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("telefonos"))),(Object)((main.mostCurrent._txttelefonos.runMethod(true,"getText"))));
 BA.debugLineNum = 99;BA.debugLine="Map1.Put(\"minDisconnect\",txtMinDisconnect.Text)";
Debug.ShouldStop(4);
_map1.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("minDisconnect"))),(Object)((main.mostCurrent._txtmindisconnect.runMethod(true,"getText"))));
 BA.debugLineNum = 100;BA.debugLine="Map1.Put(\"minConnect\", txtMinConnect.Text)";
Debug.ShouldStop(8);
_map1.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("minConnect"))),(Object)((main.mostCurrent._txtminconnect.runMethod(true,"getText"))));
 BA.debugLineNum = 101;BA.debugLine="File.WriteMap(File.DirInternal, \"settings.txt\", M";
Debug.ShouldStop(16);
main.mostCurrent.__c.getField(false,"File").runVoidMethod ("WriteMap",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString("settings.txt")),(Object)(_map1));
 BA.debugLineNum = 102;BA.debugLine="minConnect = txtMinConnect.Text";
Debug.ShouldStop(32);
main._minconnect = BA.numberCast(int.class, main.mostCurrent._txtminconnect.runMethod(true,"getText"));
 BA.debugLineNum = 103;BA.debugLine="minDisconnect = txtMinDisconnect.Text";
Debug.ShouldStop(64);
main._mindisconnect = BA.numberCast(int.class, main.mostCurrent._txtmindisconnect.runMethod(true,"getText"));
 BA.debugLineNum = 104;BA.debugLine="telefonos = txtTelefonos.Text";
Debug.ShouldStop(128);
main._telefonos = main.mostCurrent._txttelefonos.runMethod(true,"getText");
 BA.debugLineNum = 105;BA.debugLine="ToastMessageShow(\"Config saved!\", False)";
Debug.ShouldStop(256);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Config saved!")),(Object)(main.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 106;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _cambiarcolor() throws Exception{
try {
		Debug.PushSubsStack("cambiarColor (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,192);
if (RapidSub.canDelegate("cambiarcolor")) { return ilpla.monitor.main.remoteMe.runUserSub(false, "main","cambiarcolor");}
 BA.debugLineNum = 192;BA.debugLine="Sub cambiarColor 'Changes background colors";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 193;BA.debugLine="If hayEnergia = True Then";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean("=",main._hayenergia,main.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 194;BA.debugLine="pnlFondo.Color = Colors.RGB(0,114,0) 'Green (pow";
Debug.ShouldStop(2);
main.mostCurrent._pnlfondo.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 114)),(Object)(BA.numberCast(int.class, 0))));
 }else {
 BA.debugLineNum = 196;BA.debugLine="pnlFondo.Color = Colors.RGB(114,0,0) 'Red (power";
Debug.ShouldStop(8);
main.mostCurrent._pnlfondo.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 114)),(Object)(BA.numberCast(int.class, 0)),(Object)(BA.numberCast(int.class, 0))));
 };
 BA.debugLineNum = 198;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _cargarconfigtxt() throws Exception{
try {
		Debug.PushSubsStack("CargarConfigTxt (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,107);
if (RapidSub.canDelegate("cargarconfigtxt")) { return ilpla.monitor.main.remoteMe.runUserSub(false, "main","cargarconfigtxt");}
 BA.debugLineNum = 107;BA.debugLine="Sub CargarConfigTxt 'Load config";
Debug.ShouldStop(1024);
 BA.debugLineNum = 108;BA.debugLine="txtMinConnect.Text = minConnect";
Debug.ShouldStop(2048);
main.mostCurrent._txtminconnect.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(main._minconnect));
 BA.debugLineNum = 109;BA.debugLine="txtMinDisconnect.Text = minDisconnect";
Debug.ShouldStop(4096);
main.mostCurrent._txtmindisconnect.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(main._mindisconnect));
 BA.debugLineNum = 110;BA.debugLine="txtTelefonos.Text = telefonos";
Debug.ShouldStop(8192);
main.mostCurrent._txttelefonos.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(main._telefonos));
 BA.debugLineNum = 111;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _conexion() throws Exception{
try {
		Debug.PushSubsStack("Conexion (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,147);
if (RapidSub.canDelegate("conexion")) { return ilpla.monitor.main.remoteMe.runUserSub(false, "main","conexion");}
 BA.debugLineNum = 147;BA.debugLine="Sub Conexion ' POWER COMES BACK";
Debug.ShouldStop(262144);
 BA.debugLineNum = 149;BA.debugLine="timerDisconnect.Enabled = False";
Debug.ShouldStop(1048576);
main._timerdisconnect.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 150;BA.debugLine="timerDisplay.Enabled = False";
Debug.ShouldStop(2097152);
main._timerdisplay.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 153;BA.debugLine="hayEnergia = True";
Debug.ShouldStop(16777216);
main._hayenergia = main.mostCurrent.__c.getField(true,"True");
 BA.debugLineNum = 154;BA.debugLine="cambiarColor";
Debug.ShouldStop(33554432);
_cambiarcolor();
 BA.debugLineNum = 157;BA.debugLine="If lblTimer.Text = \"0.0\" Or lblTimer.Text = \"\" Th";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("=",main.mostCurrent._lbltimer.runMethod(true,"getText"),BA.ObjectToString("0.0")) || RemoteObject.solveBoolean("=",main.mostCurrent._lbltimer.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 159;BA.debugLine="DisplayTimer(minConnect)";
Debug.ShouldStop(1073741824);
_displaytimer(main._minconnect);
 BA.debugLineNum = 160;BA.debugLine="timerConnect.Initialize(\"timerConnect\", minConne";
Debug.ShouldStop(-2147483648);
main._timerconnect.runVoidMethod ("Initialize",main.processBA,(Object)(BA.ObjectToString("timerConnect")),(Object)(BA.numberCast(long.class, main._minconnect)));
 BA.debugLineNum = 161;BA.debugLine="timerConnect.Enabled = True";
Debug.ShouldStop(1);
main._timerconnect.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 }else {
 BA.debugLineNum = 163;BA.debugLine="lblTimer.Text = \"\"";
Debug.ShouldStop(4);
main.mostCurrent._lbltimer.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 };
 BA.debugLineNum = 166;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _desconexion() throws Exception{
try {
		Debug.PushSubsStack("Desconexion (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,116);
if (RapidSub.canDelegate("desconexion")) { return ilpla.monitor.main.remoteMe.runUserSub(false, "main","desconexion");}
 BA.debugLineNum = 116;BA.debugLine="Sub Desconexion ' POWER GOES OUT";
Debug.ShouldStop(524288);
 BA.debugLineNum = 118;BA.debugLine="timerConnect.Enabled = False";
Debug.ShouldStop(2097152);
main._timerconnect.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 119;BA.debugLine="timerDisplay.Enabled = False";
Debug.ShouldStop(4194304);
main._timerdisplay.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 122;BA.debugLine="hayEnergia = False";
Debug.ShouldStop(33554432);
main._hayenergia = main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 123;BA.debugLine="cambiarColor";
Debug.ShouldStop(67108864);
_cambiarcolor();
 BA.debugLineNum = 126;BA.debugLine="If lblTimer.Text = \"0.0\" Or lblTimer.Text = \"\" Th";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("=",main.mostCurrent._lbltimer.runMethod(true,"getText"),BA.ObjectToString("0.0")) || RemoteObject.solveBoolean("=",main.mostCurrent._lbltimer.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 128;BA.debugLine="DisplayTimer(minDisconnect)";
Debug.ShouldStop(-2147483648);
_displaytimer(main._mindisconnect);
 BA.debugLineNum = 129;BA.debugLine="timerDisconnect.Initialize(\"timerDisconnect\", mi";
Debug.ShouldStop(1);
main._timerdisconnect.runVoidMethod ("Initialize",main.processBA,(Object)(BA.ObjectToString("timerDisconnect")),(Object)(BA.numberCast(long.class, main._mindisconnect)));
 BA.debugLineNum = 130;BA.debugLine="timerDisconnect.Enabled = True";
Debug.ShouldStop(2);
main._timerdisconnect.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 }else {
 BA.debugLineNum = 132;BA.debugLine="lblTimer.Text = \"\"";
Debug.ShouldStop(8);
main.mostCurrent._lbltimer.runMethod(true,"setText",BA.ObjectToCharSequence(""));
 };
 BA.debugLineNum = 134;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _displaytimer(RemoteObject _tiempototal) throws Exception{
try {
		Debug.PushSubsStack("DisplayTimer (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,180);
if (RapidSub.canDelegate("displaytimer")) { return ilpla.monitor.main.remoteMe.runUserSub(false, "main","displaytimer", _tiempototal);}
Debug.locals.put("tiempoTotal", _tiempototal);
 BA.debugLineNum = 180;BA.debugLine="Sub DisplayTimer (tiempoTotal As Int) 'Display tim";
Debug.ShouldStop(524288);
 BA.debugLineNum = 181;BA.debugLine="lblTimer.Text = tiempoTotal / 1000";
Debug.ShouldStop(1048576);
main.mostCurrent._lbltimer.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.solve(new RemoteObject[] {_tiempototal,RemoteObject.createImmutable(1000)}, "/",0, 0)));
 BA.debugLineNum = 182;BA.debugLine="timerDisplay.Initialize(\"timerDisplay\", 1000)";
Debug.ShouldStop(2097152);
main._timerdisplay.runVoidMethod ("Initialize",main.processBA,(Object)(BA.ObjectToString("timerDisplay")),(Object)(BA.numberCast(long.class, 1000)));
 BA.debugLineNum = 183;BA.debugLine="timerDisplay.Enabled = True";
Debug.ShouldStop(4194304);
main._timerdisplay.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 184;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 44;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 47;BA.debugLine="Private pnlFondo As Panel";
main.mostCurrent._pnlfondo = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 48;BA.debugLine="Private btnMonitorOn As Button";
main.mostCurrent._btnmonitoron = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 49;BA.debugLine="Private lblTimer As Label";
main.mostCurrent._lbltimer = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 50;BA.debugLine="Private lblMonitorOn As Label";
main.mostCurrent._lblmonitoron = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 51;BA.debugLine="Private btnSaveConfig As Button";
main.mostCurrent._btnsaveconfig = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 52;BA.debugLine="Private txtMinDisconnect As EditText";
main.mostCurrent._txtmindisconnect = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 53;BA.debugLine="Private txtMinConnect As EditText";
main.mostCurrent._txtminconnect = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 54;BA.debugLine="Private txtTelefonos As EditText";
main.mostCurrent._txttelefonos = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
tracker_energia_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("ilpla.monitor.main");
starter.myClass = BA.getDeviceClass ("ilpla.monitor.starter");
tracker_energia.myClass = BA.getDeviceClass ("ilpla.monitor.tracker_energia");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 25;BA.debugLine="Dim telefonos As String 'phone numbers";
main._telefonos = RemoteObject.createImmutable("");
 //BA.debugLineNum = 26;BA.debugLine="Dim minConnect As Int = 0 'minimum connected time";
main._minconnect = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 27;BA.debugLine="Dim minDisconnect As Int = 0 'minimum disconnecte";
main._mindisconnect = BA.numberCast(int.class, 0);
 //BA.debugLineNum = 30;BA.debugLine="Dim monitorOn As Boolean";
main._monitoron = RemoteObject.createImmutable(false);
 //BA.debugLineNum = 31;BA.debugLine="Dim avisoEnviadoOn As Boolean";
main._avisoenviadoon = RemoteObject.createImmutable(false);
 //BA.debugLineNum = 32;BA.debugLine="Dim avisoEnviadoOff As Boolean";
main._avisoenviadooff = RemoteObject.createImmutable(false);
 //BA.debugLineNum = 33;BA.debugLine="Dim hayEnergia As Boolean";
main._hayenergia = RemoteObject.createImmutable(false);
 //BA.debugLineNum = 36;BA.debugLine="Dim timerConnect As Timer";
main._timerconnect = RemoteObject.createNew ("anywheresoftware.b4a.objects.Timer");
 //BA.debugLineNum = 37;BA.debugLine="Dim timerDisconnect As Timer";
main._timerdisconnect = RemoteObject.createNew ("anywheresoftware.b4a.objects.Timer");
 //BA.debugLineNum = 38;BA.debugLine="Dim timerDisplay As Timer";
main._timerdisplay = RemoteObject.createNew ("anywheresoftware.b4a.objects.Timer");
 //BA.debugLineNum = 41;BA.debugLine="Dim p As PhoneSms";
main._p = RemoteObject.createNew ("anywheresoftware.b4a.phone.Phone.PhoneSms");
 //BA.debugLineNum = 42;BA.debugLine="Dim rp As RuntimePermissions";
main._rp = RemoteObject.createNew ("anywheresoftware.b4a.objects.RuntimePermissions");
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _timerconnect_tick() throws Exception{
try {
		Debug.PushSubsStack("timerConnect_Tick (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,167);
if (RapidSub.canDelegate("timerconnect_tick")) { return ilpla.monitor.main.remoteMe.runUserSub(false, "main","timerconnect_tick");}
RemoteObject _numerostel = null;
int _i = 0;
 BA.debugLineNum = 167;BA.debugLine="Sub timerConnect_Tick 'Connection timer tick";
Debug.ShouldStop(64);
 BA.debugLineNum = 169;BA.debugLine="If hayEnergia = True Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("=",main._hayenergia,main.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 170;BA.debugLine="ToastMessageShow(\"Energy back, sending SMS...\",";
Debug.ShouldStop(512);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Energy back, sending SMS...")),(Object)(main.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 172;BA.debugLine="Dim numerosTel() As String = Regex.Split(\",\", te";
Debug.ShouldStop(2048);
_numerostel = main.mostCurrent.__c.getField(false,"Regex").runMethod(false,"Split",(Object)(BA.ObjectToString(",")),(Object)(main._telefonos));Debug.locals.put("numerosTel", _numerostel);Debug.locals.put("numerosTel", _numerostel);
 BA.debugLineNum = 173;BA.debugLine="For i = 0 To numerosTel.Length - 1";
Debug.ShouldStop(4096);
{
final int step4 = 1;
final int limit4 = RemoteObject.solve(new RemoteObject[] {_numerostel.getField(true,"length"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 174;BA.debugLine="p.Send(numerosTel(i),\"Energy restored!\")";
Debug.ShouldStop(8192);
main._p.runVoidMethod ("Send",(Object)(_numerostel.getArrayElement(true,BA.numberCast(int.class, _i))),(Object)(RemoteObject.createImmutable("Energy restored!")));
 }
}Debug.locals.put("i", _i);
;
 };
 BA.debugLineNum = 178;BA.debugLine="timerConnect.Enabled = False";
Debug.ShouldStop(131072);
main._timerconnect.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 179;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _timerdisconnect_tick() throws Exception{
try {
		Debug.PushSubsStack("timerDisconnect_Tick (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,135);
if (RapidSub.canDelegate("timerdisconnect_tick")) { return ilpla.monitor.main.remoteMe.runUserSub(false, "main","timerdisconnect_tick");}
RemoteObject _numerostel = null;
int _i = 0;
 BA.debugLineNum = 135;BA.debugLine="Sub timerDisconnect_Tick ' Disconnection timer tic";
Debug.ShouldStop(64);
 BA.debugLineNum = 137;BA.debugLine="ToastMessageShow(\"Sending SMS for power off...\",";
Debug.ShouldStop(256);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Sending SMS for power off...")),(Object)(main.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 138;BA.debugLine="Dim numerosTel() As String = Regex.Split(\",\", tel";
Debug.ShouldStop(512);
_numerostel = main.mostCurrent.__c.getField(false,"Regex").runMethod(false,"Split",(Object)(BA.ObjectToString(",")),(Object)(main._telefonos));Debug.locals.put("numerosTel", _numerostel);Debug.locals.put("numerosTel", _numerostel);
 BA.debugLineNum = 139;BA.debugLine="For i = 0 To numerosTel.Length - 1";
Debug.ShouldStop(1024);
{
final int step3 = 1;
final int limit3 = RemoteObject.solve(new RemoteObject[] {_numerostel.getField(true,"length"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 140;BA.debugLine="p.Send(numerosTel(i),\"Power is out!\")";
Debug.ShouldStop(2048);
main._p.runVoidMethod ("Send",(Object)(_numerostel.getArrayElement(true,BA.numberCast(int.class, _i))),(Object)(RemoteObject.createImmutable("Power is out!")));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 143;BA.debugLine="timerDisconnect.Enabled = False";
Debug.ShouldStop(16384);
main._timerdisconnect.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 144;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _timerdisplay_tick() throws Exception{
try {
		Debug.PushSubsStack("timerDisplay_Tick (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,185);
if (RapidSub.canDelegate("timerdisplay_tick")) { return ilpla.monitor.main.remoteMe.runUserSub(false, "main","timerdisplay_tick");}
 BA.debugLineNum = 185;BA.debugLine="Sub timerDisplay_Tick 'Timer ticks";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 186;BA.debugLine="If lblTimer.Text > 0 Then";
Debug.ShouldStop(33554432);
if (RemoteObject.solveBoolean(">",BA.numberCast(double.class, main.mostCurrent._lbltimer.runMethod(true,"getText")),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 187;BA.debugLine="lblTimer.Text = lblTimer.Text - 1";
Debug.ShouldStop(67108864);
main.mostCurrent._lbltimer.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.solve(new RemoteObject[] {BA.numberCast(double.class, main.mostCurrent._lbltimer.runMethod(true,"getText")),RemoteObject.createImmutable(1)}, "-",1, 0)));
 }else {
 BA.debugLineNum = 189;BA.debugLine="timerDisplay.Enabled = False";
Debug.ShouldStop(268435456);
main._timerdisplay.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 };
 BA.debugLineNum = 191;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}