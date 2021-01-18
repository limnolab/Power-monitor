B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=8.8
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
	#ExcludeFromLibrary: True
	'Autor: Joaquín Cochero - jcochero@ilpla.edu.ar
#End Region

Sub Process_Globals
End Sub

Sub Service_Create
	

End Sub

Sub Service_Start (StartingIntent As Intent)
	CargarConfig

End Sub

Sub Service_TaskRemoved
End Sub

Sub Application_Error (Error As Exception, StackTrace As String) As Boolean
	Return True
End Sub

Sub Service_Destroy

End Sub

Sub CargarConfig
	If File.Exists(File.DirInternal, "settings.txt") = True Then
		Dim Map1 As Map
		Map1 = File.ReadMap(File.DirInternal, "settings.txt")
		Main.telefonos = Map1.Get("telefonos")
		Main.minDisconnect = Map1.Get("minDisconnect") * 1000 'converts seconds to milliseconds
		Main.minConnect = Map1.Get("minConnect") * 1000 'converts seconds to milliseconds
		
		If Main.telefonos = "" Then
			Main.telefonos = ""
		End If
		If Main.minConnect = 0 Then
			Main.minConnect = 600
		End If
		If Main.minDisconnect = 0 Then
			Main.minDisconnect = 600
		End If
	Else
		'Default values (10 minutes)
		Main.minDisconnect = 600
		Main.minConnect = 600
	End If
End Sub
