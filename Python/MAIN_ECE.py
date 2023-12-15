import BlynkLib
import subprocess
from time import sleep
import machine
from hcsr04 import HCSR04
from machine import UART, Pin
from servo import Servo
import network
import RPi.GPIO as GPIO


ssid = "Samsung"
password = "ananya@lpu"

sta_if = network.WLAN(network.STA_IF)
if not sta_if.isconnected():
    print('connecting to network...')
    sta_if.active(True)
    sta_if.connect(ssid, password)
    while not sta_if.isconnected():
        pass
print('network config:', sta_if.ifconfig())

BLYNK_AUTH = 'rQS89uTbaBGegLDjyjL2xAFVevAHLn-w'


# Initialize Blynk
blynk = BlynkLib.Blynk(BLYNK_AUTH)


@blynk.on("V3")
def v3_button_handler(value):
    if int(value[0]) == 1:
        print("Starting youTube...")
        chromium_command = [
            "chromium-browser",
            "--app=https://www.youtube.com",
            "--start-maximized",
            "--start-full-screen",
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
            "--start-maximized",
            "--start-fullscreen",
            "--noerrdialogs",
            "--disable-infobars",
        ]
        subprocess.Popen(["lxterminal", "--command"] + chromium_command)
    else:
        print("exit")
        chromium_command = ["killall chromium-browser",]
        subprocess.Popen(["lxterminal", "--command"] + chromium_command)


servo_pin = machine.Pin(14)
my_servo = Servo(servo_pin)


@blynk.on("V0")
def v0_write_handler(value):
    if int(value[0]) is not 0:
        print('Gate Open...')
        my_servo.write_angle(90)
    else:
        print('Gate Close...')
        my_servo.write_angle(0)


led = Pin(27, Pin.OUT)
# Led control through V0 virtual pin


@blynk.on("V1")
def v1_write_handler(value):
    if int(value[0]) is not 0:
        print('Blub On...')
        led.value(1)
    else:
        print('Blub Off...')
        led.value(0)

# function to sync the data from virtual pins


@blynk.on("connected")
def blynk_connected():
    print("Esp32 Connected to New Blynk")


uart = UART(2, baudrate=115200)
sensor = HCSR04(trigger_pin=12, echo_pin=13, echo_timeout_us=1000000)

device_active = 0

while True:
    blynk.run()
    distance = sensor.distance_cm()
    if (distance <= 20):
        blynk.virtual_write(2, int(distance))
        print("Object distance: " + str(distance))
    sleep(2)

    count = count+2
    if count == 60:
        device_active = device_active+1
        blynk.virtual_write(5, device_active)
        count = 0
