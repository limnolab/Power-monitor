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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "ilpla.monitor", "ilpla.monitor.main");
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



public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}

private static BA killProgramHelper(BA ba) {
    if (ba == null)
        return null;
    anywheresoftware.b4a.BA.SharedProcessBA sharedProcessBA = ba.sharedProcessBA;
    if (sharedProcessBA == null || sharedProcessBA.activityBA == null)
        return null;
    return sharedProcessBA.activityBA.get();
}
public static void killProgram() {
     {
            Activity __a = null;
            if (main.previousOne != null) {
				__a = main.previousOne.get();
			}
            else {
                BA ba = killProgramHelper(main.mostCurrent == null ? null : main.mostCurrent.processBA);
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, starter.class));
BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, tracker_energia.class));
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
public static void  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}); return;}
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
RDebugUtils.currentModule="main";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="Activity.LoadLayout(\"MainLayout\")";
parent.mostCurrent._activity.LoadLayout("MainLayout",mostCurrent.activityBA);
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="If FirstTime Then";
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
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="For Each Permission As String In Array(rp.PERMIS";
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
RDebugUtils.currentLine=131077;
 //BA.debugLineNum = 131077;BA.debugLine="Sleep(100)";
anywheresoftware.b4a.keywords.Common.Sleep(mostCurrent.activityBA,new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "main", "activity_create"),(int) (100));
this.state = 18;
return;
case 18:
//C
this.state = 7;
;
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="rp.CheckAndRequest(Permission)";
parent._rp.CheckAndRequest(processBA,_permission);
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="Wait For Activity_PermissionResult (Permission";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "main", "activity_create"), null);
this.state = 19;
return;
case 19:
//C
this.state = 7;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
RDebugUtils.currentLine=131080;
 //BA.debugLineNum = 131080;BA.debugLine="If Result = False Then";
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
RDebugUtils.currentLine=131081;
 //BA.debugLineNum = 131081;BA.debugLine="ToastMessageShow(\"No permissions set\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("No permissions set"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=131082;
 //BA.debugLineNum = 131082;BA.debugLine="Log(\"No Permission: \" & Permission)";
anywheresoftware.b4a.keywords.Common.LogImpl("5131082","No Permission: "+_permission,0);
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="ExitApplication";
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
RDebugUtils.currentLine=131087;
 //BA.debugLineNum = 131087;BA.debugLine="Log(\"All permissions OK\")";
anywheresoftware.b4a.keywords.Common.LogImpl("5131087","All permissions OK",0);
 if (true) break;
;
RDebugUtils.currentLine=131089;
 //BA.debugLineNum = 131089;BA.debugLine="If FirstTime Then";

case 12:
//if
this.state = 15;
if (_firsttime) { 
this.state = 14;
}if (true) break;

case 14:
//C
this.state = 15;
RDebugUtils.currentLine=131090;
 //BA.debugLineNum = 131090;BA.debugLine="monitorOn = False";
parent._monitoron = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=131091;
 //BA.debugLineNum = 131091;BA.debugLine="avisoEnviadoOn = False";
parent._avisoenviadoon = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=131092;
 //BA.debugLineNum = 131092;BA.debugLine="avisoEnviadoOff = False";
parent._avisoenviadooff = anywheresoftware.b4a.keywords.Common.False;
 if (true) break;

case 15:
//C
this.state = -1;
;
RDebugUtils.currentLine=131094;
 //BA.debugLineNum = 131094;BA.debugLine="CargarConfigTxt";
_cargarconfigtxt();
RDebugUtils.currentLine=131096;
 //BA.debugLineNum = 131096;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _cargarconfigtxt() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cargarconfigtxt", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cargarconfigtxt", null));}
RDebugUtils.currentLine=393216;
 //BA.debugLineNum = 393216;BA.debugLine="Sub CargarConfigTxt 'Load config";
RDebugUtils.currentLine=393217;
 //BA.debugLineNum = 393217;BA.debugLine="txtMinConnect.Text = minConnect";
mostCurrent._txtminconnect.setText(BA.ObjectToCharSequence(_minconnect));
RDebugUtils.currentLine=393218;
 //BA.debugLineNum = 393218;BA.debugLine="txtMinDisconnect.Text = minDisconnect";
mostCurrent._txtmindisconnect.setText(BA.ObjectToCharSequence(_mindisconnect));
RDebugUtils.currentLine=393219;
 //BA.debugLineNum = 393219;BA.debugLine="txtTelefonos.Text = telefonos";
mostCurrent._txttelefonos.setText(BA.ObjectToCharSequence(_telefonos));
RDebugUtils.currentLine=393220;
 //BA.debugLineNum = 393220;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="End Sub";
return "";
}
public static void  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {Debug.delegate(mostCurrent.activityBA, "activity_resume", null); return;}
ResumableSub_Activity_Resume rsub = new ResumableSub_Activity_Resume(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_Activity_Resume extends BA.ResumableSub {
public ResumableSub_Activity_Resume(ilpla.monitor.main parent) {
this.parent = parent;
}
ilpla.monitor.main parent;
String _permission = "";
boolean _result = false;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="main";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=196609;
 //BA.debugLineNum = 196609;BA.debugLine="Wait For Activity_PermissionResult (Permission As";
anywheresoftware.b4a.keywords.Common.WaitFor("activity_permissionresult", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "main", "activity_resume"), null);
this.state = 5;
return;
case 5:
//C
this.state = 1;
_permission = (String) result[0];
_result = (Boolean) result[1];
;
RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="If Result Then";
if (true) break;

case 1:
//if
this.state = 4;
if (_result) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
RDebugUtils.currentLine=196612;
 //BA.debugLineNum = 196612;BA.debugLine="StartService(Tracker_Energia)";
anywheresoftware.b4a.keywords.Common.StartService(processBA,(Object)(parent.mostCurrent._tracker_energia.getObject()));
 if (true) break;

case 4:
//C
this.state = -1;
;
RDebugUtils.currentLine=196614;
 //BA.debugLineNum = 196614;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _btnmonitoron_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnmonitoron_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnmonitoron_click", null));}
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Sub btnMonitorOn_Click 'BUTTON TURNS POWER MONITOR";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="If monitorOn = False Then";
if (_monitoron==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="cambiarColor 'Changes color";
_cambiarcolor();
RDebugUtils.currentLine=917507;
 //BA.debugLineNum = 917507;BA.debugLine="monitorOn = True";
_monitoron = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=917508;
 //BA.debugLineNum = 917508;BA.debugLine="ToastMessageShow(\"Energy monitor on\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Energy monitor on"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917509;
 //BA.debugLineNum = 917509;BA.debugLine="lblMonitorOn.Text = \"Monitor ON\"";
mostCurrent._lblmonitoron.setText(BA.ObjectToCharSequence("Monitor ON"));
RDebugUtils.currentLine=917510;
 //BA.debugLineNum = 917510;BA.debugLine="btnMonitorOn.Text = \"OFF\"";
mostCurrent._btnmonitoron.setText(BA.ObjectToCharSequence("OFF"));
RDebugUtils.currentLine=917511;
 //BA.debugLineNum = 917511;BA.debugLine="txtTelefonos.Enabled = False";
mostCurrent._txttelefonos.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917512;
 //BA.debugLineNum = 917512;BA.debugLine="txtMinConnect.Enabled = False";
mostCurrent._txtminconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917513;
 //BA.debugLineNum = 917513;BA.debugLine="txtMinDisconnect.Enabled = False";
mostCurrent._txtmindisconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 }else {
RDebugUtils.currentLine=917515;
 //BA.debugLineNum = 917515;BA.debugLine="monitorOn = False";
_monitoron = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=917516;
 //BA.debugLineNum = 917516;BA.debugLine="ToastMessageShow(\"Energy monitor off\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Energy monitor off"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917517;
 //BA.debugLineNum = 917517;BA.debugLine="lblMonitorOn.Text = \"Monitor OFF\"";
mostCurrent._lblmonitoron.setText(BA.ObjectToCharSequence("Monitor OFF"));
RDebugUtils.currentLine=917518;
 //BA.debugLineNum = 917518;BA.debugLine="btnMonitorOn.Text = \"ON\"";
mostCurrent._btnmonitoron.setText(BA.ObjectToCharSequence("ON"));
RDebugUtils.currentLine=917519;
 //BA.debugLineNum = 917519;BA.debugLine="lblTimer.Text = \"\"";
mostCurrent._lbltimer.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=917520;
 //BA.debugLineNum = 917520;BA.debugLine="pnlFondo.Color = Colors.RGB(72,72,72)";
mostCurrent._pnlfondo.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (72),(int) (72),(int) (72)));
RDebugUtils.currentLine=917521;
 //BA.debugLineNum = 917521;BA.debugLine="timerDisplay.Enabled = False";
_timerdisplay.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917522;
 //BA.debugLineNum = 917522;BA.debugLine="timerConnect.Enabled = False";
_timerconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917523;
 //BA.debugLineNum = 917523;BA.debugLine="timerDisconnect. Enabled = False";
_timerdisconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=917524;
 //BA.debugLineNum = 917524;BA.debugLine="txtTelefonos.Enabled = True";
mostCurrent._txttelefonos.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=917525;
 //BA.debugLineNum = 917525;BA.debugLine="txtMinConnect.Enabled = True";
mostCurrent._txtminconnect.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=917526;
 //BA.debugLineNum = 917526;BA.debugLine="txtMinDisconnect.Enabled = True";
mostCurrent._txtmindisconnect.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=917529;
 //BA.debugLineNum = 917529;BA.debugLine="End Sub";
return "";
}
public static String  _cambiarcolor() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "cambiarcolor", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "cambiarcolor", null));}
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Sub cambiarColor 'Changes background colors";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="If hayEnergia = True Then";
if (_hayenergia==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="pnlFondo.Color = Colors.RGB(0,114,0) 'Green (pow";
mostCurrent._pnlfondo.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (0),(int) (114),(int) (0)));
 }else {
RDebugUtils.currentLine=851972;
 //BA.debugLineNum = 851972;BA.debugLine="pnlFondo.Color = Colors.RGB(114,0,0) 'Red (power";
mostCurrent._pnlfondo.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (114),(int) (0),(int) (0)));
 };
RDebugUtils.currentLine=851974;
 //BA.debugLineNum = 851974;BA.debugLine="End Sub";
return "";
}
public static String  _btnsaveconfig_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnsaveconfig_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnsaveconfig_click", null));}
anywheresoftware.b4a.objects.collections.Map _map1 = null;
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Sub btnSaveConfig_Click 'Save config button";
RDebugUtils.currentLine=327681;
 //BA.debugLineNum = 327681;BA.debugLine="Dim Map1 As Map";
_map1 = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="Map1.Initialize";
_map1.Initialize();
RDebugUtils.currentLine=327683;
 //BA.debugLineNum = 327683;BA.debugLine="Map1.Put(\"telefonos\", txtTelefonos.Text)";
_map1.Put((Object)("telefonos"),(Object)(mostCurrent._txttelefonos.getText()));
RDebugUtils.currentLine=327684;
 //BA.debugLineNum = 327684;BA.debugLine="Map1.Put(\"minDisconnect\",txtMinDisconnect.Text)";
_map1.Put((Object)("minDisconnect"),(Object)(mostCurrent._txtmindisconnect.getText()));
RDebugUtils.currentLine=327685;
 //BA.debugLineNum = 327685;BA.debugLine="Map1.Put(\"minConnect\", txtMinConnect.Text)";
_map1.Put((Object)("minConnect"),(Object)(mostCurrent._txtminconnect.getText()));
RDebugUtils.currentLine=327686;
 //BA.debugLineNum = 327686;BA.debugLine="File.WriteMap(File.DirInternal, \"settings.txt\", M";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"settings.txt",_map1);
RDebugUtils.currentLine=327687;
 //BA.debugLineNum = 327687;BA.debugLine="minConnect = txtMinConnect.Text";
_minconnect = (int)(Double.parseDouble(mostCurrent._txtminconnect.getText()));
RDebugUtils.currentLine=327688;
 //BA.debugLineNum = 327688;BA.debugLine="minDisconnect = txtMinDisconnect.Text";
_mindisconnect = (int)(Double.parseDouble(mostCurrent._txtmindisconnect.getText()));
RDebugUtils.currentLine=327689;
 //BA.debugLineNum = 327689;BA.debugLine="telefonos = txtTelefonos.Text";
_telefonos = mostCurrent._txttelefonos.getText();
RDebugUtils.currentLine=327690;
 //BA.debugLineNum = 327690;BA.debugLine="ToastMessageShow(\"Config saved!\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Config saved!"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=327691;
 //BA.debugLineNum = 327691;BA.debugLine="End Sub";
return "";
}
public static String  _conexion() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "conexion", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "conexion", null));}
RDebugUtils.currentLine=589824;
 //BA.debugLineNum = 589824;BA.debugLine="Sub Conexion ' POWER COMES BACK";
RDebugUtils.currentLine=589826;
 //BA.debugLineNum = 589826;BA.debugLine="timerDisconnect.Enabled = False";
_timerdisconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=589827;
 //BA.debugLineNum = 589827;BA.debugLine="timerDisplay.Enabled = False";
_timerdisplay.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=589830;
 //BA.debugLineNum = 589830;BA.debugLine="hayEnergia = True";
_hayenergia = anywheresoftware.b4a.keywords.Common.True;
RDebugUtils.currentLine=589831;
 //BA.debugLineNum = 589831;BA.debugLine="cambiarColor";
_cambiarcolor();
RDebugUtils.currentLine=589834;
 //BA.debugLineNum = 589834;BA.debugLine="If lblTimer.Text = \"0.0\" Or lblTimer.Text = \"\" Th";
if ((mostCurrent._lbltimer.getText()).equals("0.0") || (mostCurrent._lbltimer.getText()).equals("")) { 
RDebugUtils.currentLine=589836;
 //BA.debugLineNum = 589836;BA.debugLine="DisplayTimer(minConnect)";
_displaytimer(_minconnect);
RDebugUtils.currentLine=589837;
 //BA.debugLineNum = 589837;BA.debugLine="timerConnect.Initialize(\"timerConnect\", minConne";
_timerconnect.Initialize(processBA,"timerConnect",(long) (_minconnect));
RDebugUtils.currentLine=589838;
 //BA.debugLineNum = 589838;BA.debugLine="timerConnect.Enabled = True";
_timerconnect.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
RDebugUtils.currentLine=589840;
 //BA.debugLineNum = 589840;BA.debugLine="lblTimer.Text = \"\"";
mostCurrent._lbltimer.setText(BA.ObjectToCharSequence(""));
 };
RDebugUtils.currentLine=589843;
 //BA.debugLineNum = 589843;BA.debugLine="End Sub";
return "";
}
public static String  _displaytimer(int _tiempototal) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "displaytimer", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "displaytimer", new Object[] {_tiempototal}));}
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Sub DisplayTimer (tiempoTotal As Int) 'Display tim";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="lblTimer.Text = tiempoTotal / 1000";
mostCurrent._lbltimer.setText(BA.ObjectToCharSequence(_tiempototal/(double)1000));
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="timerDisplay.Initialize(\"timerDisplay\", 1000)";
_timerdisplay.Initialize(processBA,"timerDisplay",(long) (1000));
RDebugUtils.currentLine=720899;
 //BA.debugLineNum = 720899;BA.debugLine="timerDisplay.Enabled = True";
_timerdisplay.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=720900;
 //BA.debugLineNum = 720900;BA.debugLine="End Sub";
return "";
}
public static String  _desconexion() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "desconexion", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "desconexion", null));}
RDebugUtils.currentLine=458752;
 //BA.debugLineNum = 458752;BA.debugLine="Sub Desconexion ' POWER GOES OUT";
RDebugUtils.currentLine=458754;
 //BA.debugLineNum = 458754;BA.debugLine="timerConnect.Enabled = False";
_timerconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=458755;
 //BA.debugLineNum = 458755;BA.debugLine="timerDisplay.Enabled = False";
_timerdisplay.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=458758;
 //BA.debugLineNum = 458758;BA.debugLine="hayEnergia = False";
_hayenergia = anywheresoftware.b4a.keywords.Common.False;
RDebugUtils.currentLine=458759;
 //BA.debugLineNum = 458759;BA.debugLine="cambiarColor";
_cambiarcolor();
RDebugUtils.currentLine=458762;
 //BA.debugLineNum = 458762;BA.debugLine="If lblTimer.Text = \"0.0\" Or lblTimer.Text = \"\" Th";
if ((mostCurrent._lbltimer.getText()).equals("0.0") || (mostCurrent._lbltimer.getText()).equals("")) { 
RDebugUtils.currentLine=458764;
 //BA.debugLineNum = 458764;BA.debugLine="DisplayTimer(minDisconnect)";
_displaytimer(_mindisconnect);
RDebugUtils.currentLine=458765;
 //BA.debugLineNum = 458765;BA.debugLine="timerDisconnect.Initialize(\"timerDisconnect\", mi";
_timerdisconnect.Initialize(processBA,"timerDisconnect",(long) (_mindisconnect));
RDebugUtils.currentLine=458766;
 //BA.debugLineNum = 458766;BA.debugLine="timerDisconnect.Enabled = True";
_timerdisconnect.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
RDebugUtils.currentLine=458768;
 //BA.debugLineNum = 458768;BA.debugLine="lblTimer.Text = \"\"";
mostCurrent._lbltimer.setText(BA.ObjectToCharSequence(""));
 };
RDebugUtils.currentLine=458770;
 //BA.debugLineNum = 458770;BA.debugLine="End Sub";
return "";
}
public static String  _timerconnect_tick() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "timerconnect_tick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "timerconnect_tick", null));}
String[] _numerostel = null;
int _i = 0;
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Sub timerConnect_Tick 'Connection timer tick";
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="If hayEnergia = True Then";
if (_hayenergia==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=655363;
 //BA.debugLineNum = 655363;BA.debugLine="ToastMessageShow(\"Energy back, sending SMS...\",";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Energy back, sending SMS..."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=655365;
 //BA.debugLineNum = 655365;BA.debugLine="Dim numerosTel() As String = Regex.Split(\",\", te";
_numerostel = anywheresoftware.b4a.keywords.Common.Regex.Split(",",_telefonos);
RDebugUtils.currentLine=655366;
 //BA.debugLineNum = 655366;BA.debugLine="For i = 0 To numerosTel.Length - 1";
{
final int step4 = 1;
final int limit4 = (int) (_numerostel.length-1);
_i = (int) (0) ;
for (;_i <= limit4 ;_i = _i + step4 ) {
RDebugUtils.currentLine=655367;
 //BA.debugLineNum = 655367;BA.debugLine="p.Send(numerosTel(i),\"Energy restored!\")";
_p.Send(_numerostel[_i],"Energy restored!");
 }
};
 };
RDebugUtils.currentLine=655371;
 //BA.debugLineNum = 655371;BA.debugLine="timerConnect.Enabled = False";
_timerconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=655372;
 //BA.debugLineNum = 655372;BA.debugLine="End Sub";
return "";
}
public static String  _timerdisconnect_tick() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "timerdisconnect_tick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "timerdisconnect_tick", null));}
String[] _numerostel = null;
int _i = 0;
RDebugUtils.currentLine=524288;
 //BA.debugLineNum = 524288;BA.debugLine="Sub timerDisconnect_Tick ' Disconnection timer tic";
RDebugUtils.currentLine=524290;
 //BA.debugLineNum = 524290;BA.debugLine="ToastMessageShow(\"Sending SMS for power off...\",";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Sending SMS for power off..."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=524291;
 //BA.debugLineNum = 524291;BA.debugLine="Dim numerosTel() As String = Regex.Split(\",\", tel";
_numerostel = anywheresoftware.b4a.keywords.Common.Regex.Split(",",_telefonos);
RDebugUtils.currentLine=524292;
 //BA.debugLineNum = 524292;BA.debugLine="For i = 0 To numerosTel.Length - 1";
{
final int step3 = 1;
final int limit3 = (int) (_numerostel.length-1);
_i = (int) (0) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=524293;
 //BA.debugLineNum = 524293;BA.debugLine="p.Send(numerosTel(i),\"Power is out!\")";
_p.Send(_numerostel[_i],"Power is out!");
 }
};
RDebugUtils.currentLine=524296;
 //BA.debugLineNum = 524296;BA.debugLine="timerDisconnect.Enabled = False";
_timerdisconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=524297;
 //BA.debugLineNum = 524297;BA.debugLine="End Sub";
return "";
}
public static String  _timerdisplay_tick() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "timerdisplay_tick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "timerdisplay_tick", null));}
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Sub timerDisplay_Tick 'Timer ticks";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="If lblTimer.Text > 0 Then";
if ((double)(Double.parseDouble(mostCurrent._lbltimer.getText()))>0) { 
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="lblTimer.Text = lblTimer.Text - 1";
mostCurrent._lbltimer.setText(BA.ObjectToCharSequence((double)(Double.parseDouble(mostCurrent._lbltimer.getText()))-1));
 }else {
RDebugUtils.currentLine=786436;
 //BA.debugLineNum = 786436;BA.debugLine="timerDisplay.Enabled = False";
_timerdisplay.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=786438;
 //BA.debugLineNum = 786438;BA.debugLine="End Sub";
return "";
}
}