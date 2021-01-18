# Power-monitor
B4a code for power monitor that sends an SMS when the power goes off

App built in B4A. It notifies via SMS to the phone numbers configured when the phone disconnects from the outlet (power goes off) after a configurable number of seconds.
It then notifies them again when the power comes back on, after a configurable number of seconds.

Instructions

When the monitor is turned ON, it will check if the device is plugged or not
If the device gets unplugged, it will wait the milliseconds set in the config [Wait time - No ower detected (ms)] before sending an SMS to the phone numbers defined.
Then, when the power comes back, it will wait the milliseconds set in the config [Wait time - Power detected (ms)] before sending an SMS
The settings are saved to an internal .txt file... because it's simple!
Important: To change the settings, the monitor must be turned OFF


Note: There is also a version of the APK in Spanish