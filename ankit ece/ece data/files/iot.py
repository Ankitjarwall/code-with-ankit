import BlynkLib
import subprocess

BLYNK_AUTH = 'rQS89uTbaBGegLDjyjL2xAFVevAHLn-w'  # Replace with your Blynk Auth Token

# Initialize Blynk
blynk = BlynkLib.Blynk(BLYNK_AUTH)

@blynk.on("V3")
def v3_button_handler(value):
    if int(value[0]) == 1:
        print("Starting youTube...")
        chromium_command = [
        "chromium-browser",
        "--app=https://www.youtube.com",
        "--start-fullscreen",
        "--noerrdialogs",
        "--disable-infobars",
        ]
        subprocess.Popen(["lxterminal", "--command"] + chromium_command)
    else:
        print("exit")
        chromium_command = ["killall chromium-browser",]
        subprocess.Popen(["lxterminal", "--command"] + chromium_command)

@blynk.on("V4")
def v4_button_handler(value):
    if int(value[0]) == 1:
        print("Starting Amazon Prime Videos...")
        chromium_command = [
        "chromium-browser",
        "--app=https://www.primevideo.com",
        "--start-fullscreen",
        "--noerrdialogs",
        "--disable-infobars",
        ]
        subprocess.Popen(["lxterminal", "--command"] + chromium_command)
    else:
        print("exit")
        chromium_command = ["killall chromium-browser",]
        subprocess.Popen(["lxterminal", "--command"] + chromium_command)

while True:
    blynk.run()