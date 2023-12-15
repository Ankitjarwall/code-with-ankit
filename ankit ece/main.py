import BlynkLib
import time
from time import sleep
import machine
from hcsr04 import HCSR04
from machine import UART, Pin
from servo import Servo
import network

ssid="FTTH"
password="d2101@lpu"

sta_if = network.WLAN(network.STA_IF)
if not sta_if.isconnected():
    print('connecting to network...')
    sta_if.active(True)
    sta_if.connect(ssid, password)
    while not sta_if.isconnected():
        pass
print('network config:', sta_if.ifconfig())

BLYNK_AUTH_TOKEN = 'rQS89uTbaBGegLDjyjL2xAFVevAHLn-w'

led=Pin(27,Pin.OUT)
# Initialize Blynk
blynk = BlynkLib.Blynk(BLYNK_AUTH_TOKEN)

# Led control through V0 virtual pin
servo_pin = machine.Pin(14)
my_servo = Servo(servo_pin)

@blynk.on("V0")
def v0_write_handler(value):
#    global led_switch
    if int(value[0]) is not 0:
        print('Gate Open...')
        my_servo.write_angle(90)
    else:
        print('Gate Close...')
        my_servo.write_angle(0)

@blynk.on("V1")
def v1_write_handler(value):
#    global led_switch
    if int(value[0]) is not 0:
        print('Blub On...')
        led.value(1)
    else:
        print('Blub Off...')
        led.value(0)

#function to sync the data from virtual pins
@blynk.on("connected")
def blynk_connected():
    print("Esp32 Connected to New Blynk") 


uart = UART(2, baudrate=115200)
sensor = HCSR04(trigger_pin=12, echo_pin=13, echo_timeout_us=1000000)

while True:
    blynk.run()
    distance = sensor.distance_cm()
    if(distance<=20):
        blynk.virtual_write(2,int(distance))
        print("Object distance: "+ str(distance))
    sleep(0.4)