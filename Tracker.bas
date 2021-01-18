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
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Private lock As PhoneWakeState
	Dim PhoneEvent As PhoneEvents
End Sub

Sub Service_Create
	Service.AutomaticForegroundMode = Service.AUTOMATIC_FOREGROUND_NEVER 'we are handling it ourselves
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
	notification.SetInfo("Tracking location", Body, Main)
	Return notification
End Sub

Sub PhoneEvent_BatteryChanged( Level As Int, Scale As Int, Plugged As Boolean, Intent As Intent )
	'Establece el estado pre-cambio
	Dim estadoAnterior As Boolean
	If Main.hayEnergia = True Then
		estadoAnterior = True
	End If
	
	If Plugged = False Then
		'Cambia el color
		Main.hayEnergia = False
		If Main.monitorOn = True Then
			'Si es distinto al estado anterior, avisa
			If estadoAnterior = True Then
				ToastMessageShow("SE DESCONECTO", True)
				CallSub(Main, "Desconexion")
			End If
			
		End If
	Else If Plugged = True Then
		'Cambia el color
		Main.hayEnergia = True
		
		If Main.monitorOn = True Then
			If estadoAnterior = False Then
				ToastMessageShow("SE CONECTO", True)
				CallSub(Main, "Conexion")
			End If
		End If
	End If
End Sub