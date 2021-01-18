B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=9
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: True
	
#End Region

Sub Process_Globals
	Private lock As PhoneWakeState
	Dim PhoneEvent As PhoneEvents
End Sub

Sub Service_Create
	Service.AutomaticForegroundMode = Service.AUTOMATIC_FOREGROUND_NEVER 
	lock.PartialLock
End Sub

Sub Service_Start (StartingIntent As Intent)
	Service.StartForeground(1, CreateNotification("..."))
	PhoneEvent.Initialize("PhoneEvent")
End Sub

Sub Service_Destroy
	lock.ReleasePartialLock
End Sub

Sub CreateNotification (Body As String) As Notification
	Dim notification As Notification
	notification.Initialize2(notification.IMPORTANCE_LOW)
	notification.Icon = "icon"
	notification.SetInfo("Checking power", Body, Main)
	Return notification
End Sub

Sub PhoneEvent_BatteryChanged( Level As Int, Scale As Int, Plugged As Boolean, Intent As Intent )
	'First gets the current status
	Dim estadoAnterior As Boolean
	If Main.hayEnergia = True Then
		estadoAnterior = True
	End If
	
	If Plugged = False Then
		'Changes color
		Main.hayEnergia = False
		If Main.monitorOn = True Then
			'If the power goes out
			If estadoAnterior = True Then
				ToastMessageShow("Power out! Starting SMS timer", True)
				CallSubDelayed(Main, "Desconexion")
			End If
			
		End If
	Else If Plugged = True Then
		'Changes color
		Main.hayEnergia = True
		
		If Main.monitorOn = True Then
			'If the power comes back on
			If estadoAnterior = False Then
				ToastMessageShow("Power back! Starting SMS timer", True)
				CallSubDelayed(Main, "Conexion")
			End If
		End If
	End If
End Sub