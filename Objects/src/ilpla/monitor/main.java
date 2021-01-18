package ilpla.monitor;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "ilpla.monitor", "ilpla.monitor.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "ilpla.monitor", "ilpla.monitor.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "ilpla.monitor.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static String _telefonos = "";
public static int _minconnect = 0;
public static int _mindisconnect = 0;
public static boolean _monitoron = false;
public static boolean _avisoenviadoon = false;
public static boolean _avisoenviadooff = false;
public static boolean _hayenergia = false;
public static anywheresoftware.b4a.objects.Timer _timerconnect = null;
public static anywheresoftware.b4a.objects.Timer _timerdisconnect = null;
public static anywheresoftware.b4a.objects.Timer _timerdisplay = null;
public static anywheresoftware.b4a.phone.Phone.PhoneSms _p = null;
public static anywheresoftware.b4a.objects.RuntimePermissions _rp = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlfondo = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnmonitoron = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbltimer = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblmonitoron = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnsaveconfig = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtmindisconnect = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtminconnect = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txttelefonos = null;
public ilpla.monitor.starter _starter = null;
public ilpla.monitor.tracker_energia _tracker_energia = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static void  _activity_create(boolean _firsttime) throws Exception{
ResumableSub_Activity_Create rsub = new ResumableSub_Activity_Create(null,_firsttime);
rsub.resume(processBA, null);
}
public static class ResumableSub_Activity_Create extends BA.ResumableSub {
public ResumableSub_Activity_Create(ilpla.monitor.main parent,boolean _firsttime) {
this.parent = parent;
this._firsttime = _firsttime;
}
ilpla.monitor.main parent;
boolean _firsttime;
String _permission = "";
boolean _result = false;
Object[] group3;
int index3;
int groupLen3;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 58;BA.debugLine="Activity.LoadLayout(\"MainLayout\")";
parent.mostCurrent._activity.LoadLayout("MainLayout",mostCurrent.activityBA);
 //BA.debugLineNum = 60;BA.debugLine="If FirstTime Then";
if (true) break;

case 1:
//if
this.state = 12;
if (_firsttime) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 //BA.debugLineNum = 61;BA.debugLine="For Each Permission As String In Array(rp.PERMIS";
if (true) break;

case 4:
//for
this.state = 11;
group3 = new Object[]{(Object)(parent._rp.PERMISSION_READ_PHONE_STATE),(Object)(parent._rp.PERMISSION_SEND_SMS)};
index3 = 0;
groupLen3 = group3.length;
this.state = 16;
if (true) break;

case 16:
//C
this.state = 11;
if (index3 < groupLen3) {
this.state = 6;
_permission = BA.ObjectToString(group3[index3]);}
if (true) break;

case 17:
//C
this.state = 16;
index3++;
if (true) break;

case 6:
//C
this.state = 7;
 //BA.debugLineNum = 62;BA.debugLine="Sleep(100)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,this,(int) (100));
this.state = 18;
return;
case 18:
//C
this.state = 7;
;
 //BA.debugLineNum = 63;BA.debugLine="rp.CheckAndRequest(Permission)";
parent._rp.CheckAndRequest(processBA,_permission);
 //BA.debugLineNum = 64;BA.debugLine="Wait For Activity_PermissionResult (Permission";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, this, null);
this.state = 19;
return;
case 19:
//C
this.state = 7;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
 //BA.debugLineNum = 65;BA.debugLine="If Result = False Then";
if (true) break;

case 7:
//if
this.state = 10;
if (_result==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 9;
}if (true) break;

case 9:
//C
this.state = 10;
 //BA.debugLineNum = 66;BA.debugLine="ToastMessageShow(\"No permissions set\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("No permissions set"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 67;BA.debugLine="Log(\"No Permission: \" & Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("4131082","No Permission: "+_permission,0);
 //BA.debugLineNum = 68;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
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
;
 //BA.debugLineNum = 72;BA.debugLine="Log(\"All permissions OK\")";
anywheresoftware.b4a.keywords.Common.LogImpl("4131087","All permissions OK",0);
 if (true) break;
;
 //BA.debugLineNum = 74;BA.debugLine="If FirstTime Then";

case 12:
//if
this.state = 15;
if (_firsttime) { 
this.state = 14;
}if (true) break;

case 14:
//C
this.state = 15;
 //BA.debugLineNum = 75;BA.debugLine="monitorOn = False";
parent._monitoron = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 76;BA.debugLine="avisoEnviadoOn = False";
parent._avisoenviadoon = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 77;BA.debugLine="avisoEnviadoOff = False";
parent._avisoenviadooff = anywheresoftware.b4a.keywords.Common.False;
 if (true) break;

case 15:
//C
this.state = -1;
;
 //BA.debugLineNum = 79;BA.debugLine="CargarConfigTxt";
_cargarconfigtxt();
 //BA.debugLineNum = 81;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _activity_permissionresult(String _permission,boolean _result) throws Exception{
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 87;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 82;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 84;BA.debugLine="StartService(Tracker_Energia)";
anywheresoftware.b4a.keywords.Common.StartService(processBA,(Object)(mostCurrent._tracker_energia.getObject()));
 //BA.debugLineNum = 86;BA.debugLine="End Sub";
return "";
}
public static void  _btnexit_click() throws Exception{
ResumableSub_btnExit_Click rsub = new ResumableSub_btnExit_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_btnExit_Click extends BA.ResumableSub {
public ResumableSub_btnExit_Click(ilpla.monitor.main parent) {
this.parent = parent;
}
ilpla.monitor.main parent;
int _result = 0;

@Override
public void resume(BA ba, Object[] result) throws Exception{

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 239;BA.debugLine="Msgbox2Async(\"Sure you want to permanently close";
anywheresoftware.b4a.keywords.Common.Msgbox2Async(BA.ObjectToCharSequence("Sure you want to permanently close the energy monitor?"),BA.ObjectToCharSequence("Exit forever?"),"Yes!","No, leave the monitor running","",(anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.drawable.CanvasWrapper.BitmapWrapper(), (android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null)),processBA,anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 240;BA.debugLine="Wait For Msgbox_Result(Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 7;
return;
case 7:
//C
this.state = 1;
_result = (Integer) result[0];
;
 //BA.debugLineNum = 242;BA.debugLine="If Result =  DialogResponse.POSITIVE Then";
if (true) break;

case 1:
//if
this.state = 6;
if (_result==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 //BA.debugLineNum = 243;BA.debugLine="Activity.Finish";
parent.mostCurrent._activity.Finish();
 //BA.debugLineNum = 244;BA.debugLine="ExitApplication";
anywheresoftware.b4a.keywords.Common.ExitApplication();
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 246;BA.debugLine="Return";
if (true) return ;
 if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 251;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _msgbox_result(int _result) throws Exception{
}
public static String  _btnmonitoron_click() throws Exception{
 //BA.debugLineNum = 201;BA.debugLine="Sub btnMonitorOn_Click 'BUTTON TURNS POWER MONITOR";
 //BA.debugLineNum = 202;BA.debugLine="If txtTelefonos.Text = \"\" Then";
if ((mostCurrent._txttelefonos.getText()).equals("")) { 
 //BA.debugLineNum = 203;BA.debugLine="ToastMessageShow(\"No phone numbers configured...";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("No phone numbers configured... cancelling monitor"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 204;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 206;BA.debugLine="btnSaveConfig_Click";
_btnsaveconfig_click();
 //BA.debugLineNum = 208;BA.debugLine="If monitorOn = False Then";
if (_monitoron==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 209;BA.debugLine="cambiarColor 'Changes color";
_cambiarcolor();
 //BA.debugLineNum = 210;BA.debugLine="monitorOn = True";
_monitoron = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 211;BA.debugLine="ToastMessageShow(\"Energy monitor on\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Energy monitor on"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 212;BA.debugLine="lblMonitorOn.Text = \"Monitor ON\"";
mostCurrent._lblmonitoron.setText(BA.ObjectToCharSequence("Monitor ON"));
 //BA.debugLineNum = 213;BA.debugLine="btnMonitorOn.Text = \"OFF\"";
mostCurrent._btnmonitoron.setText(BA.ObjectToCharSequence("OFF"));
 //BA.debugLineNum = 214;BA.debugLine="txtTelefonos.Enabled = False";
mostCurrent._txttelefonos.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 215;BA.debugLine="txtMinConnect.Enabled = False";
mostCurrent._txtminconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 216;BA.debugLine="txtMinDisconnect.Enabled = False";
mostCurrent._txtmindisconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 217;BA.debugLine="btnSaveConfig.Enabled = False";
mostCurrent._btnsaveconfig.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 219;BA.debugLine="monitorOn = False";
_monitoron = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 220;BA.debugLine="ToastMessageShow(\"Energy monitor off\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Energy monitor off"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 221;BA.debugLine="lblMonitorOn.Text = \"Monitor OFF\"";
mostCurrent._lblmonitoron.setText(BA.ObjectToCharSequence("Monitor OFF"));
 //BA.debugLineNum = 222;BA.debugLine="btnMonitorOn.Text = \"ON\"";
mostCurrent._btnmonitoron.setText(BA.ObjectToCharSequence("ON"));
 //BA.debugLineNum = 223;BA.debugLine="lblTimer.Text = \"\"";
mostCurrent._lbltimer.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 224;BA.debugLine="pnlFondo.Color = Colors.RGB(72,72,72)";
mostCurrent._pnlfondo.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (72),(int) (72),(int) (72)));
 //BA.debugLineNum = 225;BA.debugLine="timerDisplay.Enabled = False";
_timerdisplay.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 226;BA.debugLine="timerConnect.Enabled = False";
_timerconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 227;BA.debugLine="timerDisconnect. Enabled = False";
_timerdisconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 228;BA.debugLine="txtTelefonos.Enabled = True";
mostCurrent._txttelefonos.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 229;BA.debugLine="txtMinConnect.Enabled = True";
mostCurrent._txtminconnect.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 230;BA.debugLine="txtMinDisconnect.Enabled = True";
mostCurrent._txtmindisconnect.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 231;BA.debugLine="btnSaveConfig.Enabled = True";
mostCurrent._btnsaveconfig.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 234;BA.debugLine="End Sub";
return "";
}
public static String  _btnsaveconfig_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _map1 = null;
 //BA.debugLineNum = 93;BA.debugLine="Sub btnSaveConfig_Click 'Save config button";
 //BA.debugLineNum = 94;BA.debugLine="Dim Map1 As Map";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 95;BA.debugLine="Map1.Initialize";
_map1.Initialize();
 //BA.debugLineNum = 96;BA.debugLine="Map1.Put(\"telefonos\", txtTelefonos.Text)";
_map1.Put((Object)("telefonos"),(Object)(mostCurrent._txttelefonos.getText()));
 //BA.debugLineNum = 97;BA.debugLine="Map1.Put(\"minDisconnect\",txtMinDisconnect.Text)";
_map1.Put((Object)("minDisconnect"),(Object)(mostCurrent._txtmindisconnect.getText()));
 //BA.debugLineNum = 98;BA.debugLine="Map1.Put(\"minConnect\", txtMinConnect.Text)";
_map1.Put((Object)("minConnect"),(Object)(mostCurrent._txtminconnect.getText()));
 //BA.debugLineNum = 99;BA.debugLine="File.WriteMap(File.DirInternal, \"settings.txt\", M";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"settings.txt",_map1);
 //BA.debugLineNum = 100;BA.debugLine="minConnect = txtMinConnect.Text * 1000";
_minconnect = (int) ((double)(Double.parseDouble(mostCurrent._txtminconnect.getText()))*1000);
 //BA.debugLineNum = 101;BA.debugLine="minDisconnect = txtMinDisconnect.Text *1000";
_mindisconnect = (int) ((double)(Double.parseDouble(mostCurrent._txtmindisconnect.getText()))*1000);
 //BA.debugLineNum = 102;BA.debugLine="telefonos = txtTelefonos.Text";
_telefonos = mostCurrent._txttelefonos.getText();
 //BA.debugLineNum = 103;BA.debugLine="ToastMessageShow(\"Config saved!\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Config saved!"),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 104;BA.debugLine="End Sub";
return "";
}
public static String  _cambiarcolor() throws Exception{
 //BA.debugLineNum = 190;BA.debugLine="Sub cambiarColor 'Changes background colors";
 //BA.debugLineNum = 191;BA.debugLine="If hayEnergia = True Then";
if (_hayenergia==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 192;BA.debugLine="pnlFondo.Color = Colors.RGB(0,114,0) 'Green (pow";
mostCurrent._pnlfondo.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (114),(int) (0)));
 }else {
 //BA.debugLineNum = 194;BA.debugLine="pnlFondo.Color = Colors.RGB(114,0,0) 'Red (power";
mostCurrent._pnlfondo.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (114),(int) (0),(int) (0)));
 };
 //BA.debugLineNum = 196;BA.debugLine="End Sub";
return "";
}
public static String  _cargarconfigtxt() throws Exception{
 //BA.debugLineNum = 105;BA.debugLine="Sub CargarConfigTxt 'Load config";
 //BA.debugLineNum = 106;BA.debugLine="txtMinConnect.Text = minConnect / 1000";
mostCurrent._txtminconnect.setText(BA.ObjectToCharSequence(_minconnect/(double)1000));
 //BA.debugLineNum = 107;BA.debugLine="txtMinDisconnect.Text = minDisconnect / 1000";
mostCurrent._txtmindisconnect.setText(BA.ObjectToCharSequence(_mindisconnect/(double)1000));
 //BA.debugLineNum = 108;BA.debugLine="txtTelefonos.Text = telefonos";
mostCurrent._txttelefonos.setText(BA.ObjectToCharSequence(_telefonos));
 //BA.debugLineNum = 109;BA.debugLine="End Sub";
return "";
}
public static String  _conexion() throws Exception{
 //BA.debugLineNum = 145;BA.debugLine="Sub Conexion ' POWER COMES BACK";
 //BA.debugLineNum = 147;BA.debugLine="timerDisconnect.Enabled = False";
_timerdisconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 148;BA.debugLine="timerDisplay.Enabled = False";
_timerdisplay.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 151;BA.debugLine="hayEnergia = True";
_hayenergia = anywheresoftware.b4a.keywords.Common.True;
 //BA.debugLineNum = 152;BA.debugLine="cambiarColor";
_cambiarcolor();
 //BA.debugLineNum = 155;BA.debugLine="If lblTimer.Text = \"0.0\" Or lblTimer.Text = \"\" Th";
if ((mostCurrent._lbltimer.getText()).equals("0.0") || (mostCurrent._lbltimer.getText()).equals("")) { 
 //BA.debugLineNum = 157;BA.debugLine="DisplayTimer(minConnect / 1000)";
_displaytimer((int) (_minconnect/(double)1000));
 //BA.debugLineNum = 158;BA.debugLine="timerConnect.Initialize(\"timerConnect\", minConne";
_timerconnect.Initialize(processBA,"timerConnect",(long) (_minconnect));
 //BA.debugLineNum = 159;BA.debugLine="timerConnect.Enabled = True";
_timerconnect.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 161;BA.debugLine="lblTimer.Text = \"\"";
mostCurrent._lbltimer.setText(BA.ObjectToCharSequence(""));
 };
 //BA.debugLineNum = 164;BA.debugLine="End Sub";
return "";
}
public static String  _desconexion() throws Exception{
 //BA.debugLineNum = 114;BA.debugLine="Sub Desconexion ' POWER GOES OUT";
 //BA.debugLineNum = 116;BA.debugLine="timerConnect.Enabled = False";
_timerconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 117;BA.debugLine="timerDisplay.Enabled = False";
_timerdisplay.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 120;BA.debugLine="hayEnergia = False";
_hayenergia = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 121;BA.debugLine="cambiarColor";
_cambiarcolor();
 //BA.debugLineNum = 124;BA.debugLine="If lblTimer.Text = \"0.0\" Or lblTimer.Text = \"\" Th";
if ((mostCurrent._lbltimer.getText()).equals("0.0") || (mostCurrent._lbltimer.getText()).equals("")) { 
 //BA.debugLineNum = 126;BA.debugLine="DisplayTimer(minDisconnect / 1000)";
_displaytimer((int) (_mindisconnect/(double)1000));
 //BA.debugLineNum = 127;BA.debugLine="timerDisconnect.Initialize(\"timerDisconnect\", mi";
_timerdisconnect.Initialize(processBA,"timerDisconnect",(long) (_mindisconnect));
 //BA.debugLineNum = 128;BA.debugLine="timerDisconnect.Enabled = True";
_timerdisconnect.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 130;BA.debugLine="lblTimer.Text = \"\"";
mostCurrent._lbltimer.setText(BA.ObjectToCharSequence(""));
 };
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public static String  _displaytimer(int _tiempototal) throws Exception{
 //BA.debugLineNum = 178;BA.debugLine="Sub DisplayTimer (tiempoTotal As Int) 'Display tim";
 //BA.debugLineNum = 179;BA.debugLine="lblTimer.Text = tiempoTotal";
mostCurrent._lbltimer.setText(BA.ObjectToCharSequence(_tiempototal));
 //BA.debugLineNum = 180;BA.debugLine="timerDisplay.Initialize(\"timerDisplay\", 1000)";
_timerdisplay.Initialize(processBA,"timerDisplay",(long) (1000));
 //BA.debugLineNum = 181;BA.debugLine="timerDisplay.Enabled = True";
_timerdisplay.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 44;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 47;BA.debugLine="Private pnlFondo As Panel";
mostCurrent._pnlfondo = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private btnMonitorOn As Button";
mostCurrent._btnmonitoron = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private lblTimer As Label";
mostCurrent._lbltimer = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private lblMonitorOn As Label";
mostCurrent._lblmonitoron = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private btnSaveConfig As Button";
mostCurrent._btnsaveconfig = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 52;BA.debugLine="Private txtMinDisconnect As EditText";
mostCurrent._txtmindisconnect = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 53;BA.debugLine="Private txtMinConnect As EditText";
mostCurrent._txtminconnect = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 54;BA.debugLine="Private txtTelefonos As EditText";
mostCurrent._txttelefonos = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
starter._process_globals();
tracker_energia._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 23;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 25;BA.debugLine="Dim telefonos As String 'phone numbers";
_telefonos = "";
 //BA.debugLineNum = 26;BA.debugLine="Dim minConnect As Int = 0 'minimum connected time";
_minconnect = (int) (0);
 //BA.debugLineNum = 27;BA.debugLine="Dim minDisconnect As Int = 0 'minimum disconnecte";
_mindisconnect = (int) (0);
 //BA.debugLineNum = 30;BA.debugLine="Dim monitorOn As Boolean";
_monitoron = false;
 //BA.debugLineNum = 31;BA.debugLine="Dim avisoEnviadoOn As Boolean";
_avisoenviadoon = false;
 //BA.debugLineNum = 32;BA.debugLine="Dim avisoEnviadoOff As Boolean";
_avisoenviadooff = false;
 //BA.debugLineNum = 33;BA.debugLine="Dim hayEnergia As Boolean";
_hayenergia = false;
 //BA.debugLineNum = 36;BA.debugLine="Dim timerConnect As Timer";
_timerconnect = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 37;BA.debugLine="Dim timerDisconnect As Timer";
_timerdisconnect = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 38;BA.debugLine="Dim timerDisplay As Timer";
_timerdisplay = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 41;BA.debugLine="Dim p As PhoneSms";
_p = new anywheresoftware.b4a.phone.Phone.PhoneSms();
 //BA.debugLineNum = 42;BA.debugLine="Dim rp As RuntimePermissions";
_rp = new anywheresoftware.b4a.objects.RuntimePermissions();
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return "";
}
public static String  _timerconnect_tick() throws Exception{
String[] _numerostel = null;
int _i = 0;
 //BA.debugLineNum = 165;BA.debugLine="Sub timerConnect_Tick 'Connection timer tick";
 //BA.debugLineNum = 167;BA.debugLine="If hayEnergia = True Then";
if (_hayenergia==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 168;BA.debugLine="ToastMessageShow(\"Energy back, sending SMS...\",";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Energy back, sending SMS..."),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 170;BA.debugLine="Dim numerosTel() As String = Regex.Split(\",\", te";
_numerostel = anywheresoftware.b4a.keywords.Common.Regex.Split(",",_telefonos);
 //BA.debugLineNum = 171;BA.debugLine="For i = 0 To numerosTel.Length - 1";
{
final int step4 = 1;
final int limit4 = (int) (_numerostel.length-1);
_i = (int) (0) ;
for (;_i <= limit4 ;_i = _i + step4 ) {
 //BA.debugLineNum = 172;BA.debugLine="p.Send(numerosTel(i),\"Energy restored!\")";
_p.Send(_numerostel[_i],"Energy restored!");
 }
};
 };
 //BA.debugLineNum = 176;BA.debugLine="timerConnect.Enabled = False";
_timerconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 177;BA.debugLine="End Sub";
return "";
}
public static String  _timerdisconnect_tick() throws Exception{
String[] _numerostel = null;
int _i = 0;
 //BA.debugLineNum = 133;BA.debugLine="Sub timerDisconnect_Tick ' Disconnection timer tic";
 //BA.debugLineNum = 135;BA.debugLine="ToastMessageShow(\"Sending SMS for power off...\",";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Sending SMS for power off..."),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 136;BA.debugLine="Dim numerosTel() As String = Regex.Split(\",\", tel";
_numerostel = anywheresoftware.b4a.keywords.Common.Regex.Split(",",_telefonos);
 //BA.debugLineNum = 137;BA.debugLine="For i = 0 To numerosTel.Length - 1";
{
final int step3 = 1;
final int limit3 = (int) (_numerostel.length-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 138;BA.debugLine="p.Send(numerosTel(i),\"Power is out!\")";
_p.Send(_numerostel[_i],"Power is out!");
 }
};
 //BA.debugLineNum = 141;BA.debugLine="timerDisconnect.Enabled = False";
_timerdisconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 142;BA.debugLine="End Sub";
return "";
}
public static String  _timerdisplay_tick() throws Exception{
 //BA.debugLineNum = 183;BA.debugLine="Sub timerDisplay_Tick 'Timer ticks";
 //BA.debugLineNum = 184;BA.debugLine="If lblTimer.Text > 0 Then";
if ((double)(Double.parseDouble(mostCurrent._lbltimer.getText()))>0) { 
 //BA.debugLineNum = 185;BA.debugLine="lblTimer.Text = lblTimer.Text - 1";
mostCurrent._lbltimer.setText(BA.ObjectToCharSequence((double)(Double.parseDouble(mostCurrent._lbltimer.getText()))-1));
 }else {
 //BA.debugLineNum = 187;BA.debugLine="timerDisplay.Enabled = False";
_timerdisplay.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 189;BA.debugLine="End Sub";
return "";
}
}
